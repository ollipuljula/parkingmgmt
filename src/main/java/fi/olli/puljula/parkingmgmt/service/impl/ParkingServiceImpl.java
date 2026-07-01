package fi.olli.puljula.parkingmgmt.service.impl;

import fi.olli.puljula.parkingmgmt.api.model.LotResponse;
import fi.olli.puljula.parkingmgmt.api.model.ExitResponse;
import fi.olli.puljula.parkingmgmt.exception.CarNotFoundException;
import fi.olli.puljula.parkingmgmt.exception.CarParkedAlreadyException;
import fi.olli.puljula.parkingmgmt.exception.InvalidParkingSpaceException;
import fi.olli.puljula.parkingmgmt.exception.ParkingLotFullException;
import fi.olli.puljula.parkingmgmt.exception.ParkingSpaceNotAvailableException;
import fi.olli.puljula.parkingmgmt.repository.InMemoryParkingRepository;
import fi.olli.puljula.parkingmgmt.repository.model.ParkingEvent;
import fi.olli.puljula.parkingmgmt.service.ParkingService;
import fi.olli.puljula.parkingmgmt.service.PricingService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private static final int CAPACITY = 5;

    private final InMemoryParkingRepository repository;
    private final PricingService pricingService;

    public ParkingServiceImpl(
            InMemoryParkingRepository repository,
            PricingService pricingService
    ) {
        this.repository = repository;
        this.pricingService = pricingService;
    }

    @Override
    public synchronized ParkingEvent parkCar(String registrationNumber, int spaceNumber) {
        validateSpaceNumber(spaceNumber);

        if (repository.count() >= CAPACITY) {
            throw new ParkingLotFullException("Parking lot is full");
        }

        if (repository.findByRegistrationNumber(registrationNumber).isPresent()) {
            throw new CarParkedAlreadyException("Car '%s' is already parked"
                    .formatted(registrationNumber));
        }

        if (repository.isSpaceOccupied(spaceNumber)) {
            throw new ParkingSpaceNotAvailableException("Parking space %d is already occupied".formatted(spaceNumber));
        }

        ParkingEvent event = new ParkingEvent(
                normalize(registrationNumber),
                spaceNumber,
                LocalDateTime.now()
        );

        return repository.save(event);
    }

    @Override
    public synchronized ExitResponse exitCar(String registrationNumber) {
        ParkingEvent event = repository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new CarNotFoundException("No active parking event found for car '%s'"
                        .formatted(registrationNumber)));

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

        return new ExitResponse(
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
    public boolean isSpaceOccupied(int spaceNumber) {
        validateSpaceNumber(spaceNumber);
        return repository.isSpaceOccupied(spaceNumber);
    }

    private void validateSpaceNumber(int spaceNumber) {
        if (spaceNumber < 1 || spaceNumber > CAPACITY) {
            throw new InvalidParkingSpaceException("Parking space %d does not exist"
                    .formatted(spaceNumber));
        }
    }

    private String normalize(String registrationNumber) {
        return registrationNumber.trim().toUpperCase();
    }
}
