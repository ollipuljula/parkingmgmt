package fi.olli.puljula.parkingmgmt.service.impl;

import fi.olli.puljula.parkingmgmt.api.model.LotResponse;
import fi.olli.puljula.parkingmgmt.api.model.UnparkResponse;
import fi.olli.puljula.parkingmgmt.exception.CarNotFoundException;
import fi.olli.puljula.parkingmgmt.exception.CarParkedAlreadyException;
import fi.olli.puljula.parkingmgmt.exception.InvalidParkingSpaceException;
import fi.olli.puljula.parkingmgmt.exception.ParkingLotFullException;
import fi.olli.puljula.parkingmgmt.exception.ParkingSpaceNotAvailable;
import fi.olli.puljula.parkingmgmt.jpa.ParkingStore;
import fi.olli.puljula.parkingmgmt.jpa.entity.ParkingEvent;
import fi.olli.puljula.parkingmgmt.service.ParkingService;
import fi.olli.puljula.parkingmgmt.service.PricingService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private static final int CAPACITY = 5;

    private final ParkingStore repository;
    private final PricingService pricingService;

    public ParkingServiceImpl(
            ParkingStore repository,
            PricingService pricingService
    ) {
        this.repository = repository;
        this.pricingService = pricingService;
    }

    @Override
    public synchronized ParkingEvent parkCar(String registrationNumber, int spaceNumber) {
        validateSpaceNumber(spaceNumber);

        if (repository.count() >= CAPACITY) {
            throw new ParkingLotFullException();
        }

        if (repository.findByRegistrationNumber(registrationNumber).isPresent()) {
            throw new CarParkedAlreadyException(registrationNumber);
        }

        if (repository.isSpaceAvailable(spaceNumber)) {
            throw new ParkingSpaceNotAvailable(spaceNumber + "");
        }

        ParkingEvent event = new ParkingEvent(
                normalize(registrationNumber),
                spaceNumber,
                LocalDateTime.now()
        );

        return repository.save(event);
    }

    @Override
    public synchronized UnparkResponse exitCar(String registrationNumber) {
        ParkingEvent event = repository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new CarNotFoundException(registrationNumber));

        LocalDateTime exitTime = LocalDateTime.now();

        long price = pricingService.calculatePriceInCents(
                event.getStartTime(),
                exitTime
        );

        long durationSeconds = Duration.between(
                event.getStartTime(),
                exitTime
        ).toSeconds();

        repository.deleteByRegistrationNumber(event.getRegistrationNumber());

        return new UnparkResponse(
                event.getStartTime(),
                exitTime,
                durationSeconds,
                price,
                event.getRegistrationNumber(),
                event.getSpaceNumber()
        );
    }

    @Override
    public LotResponse getStatus() {
        return new LotResponse(
                CAPACITY,
                CAPACITY - repository.count(),
                repository.count(),
                repository.count() >= CAPACITY
        );
    }

    @Override
    public List<ParkingEvent> getActiveParkingEvents() {
        return repository.findAll();
    }

    @Override
    public boolean isSpaceAvailable(int spaceNumber) {
        validateSpaceNumber(spaceNumber);
        return !repository.isSpaceAvailable(spaceNumber);
    }

    private void validateSpaceNumber(int spaceNumber) {
        if (spaceNumber < 1 || spaceNumber > CAPACITY) {
            throw new InvalidParkingSpaceException(spaceNumber + "");
        }
    }

    private String normalize(String registrationNumber) {
        return registrationNumber.trim().toUpperCase();
    }
}
