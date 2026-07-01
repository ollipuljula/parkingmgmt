package fi.olli.puljula.parkingmgmt.repository;

import fi.olli.puljula.parkingmgmt.repository.model.ParkingEvent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryParkingRepository {
    private final Map<String, ParkingEvent> eventsByRegistrationNumber = new ConcurrentHashMap<>();
    private final Map<Integer, String> registrationNumberBySpaceNumber = new ConcurrentHashMap<>();

    public List<ParkingEvent> findAll() {
        return List.copyOf(eventsByRegistrationNumber.values());
    }

    public Optional<ParkingEvent> findByRegistrationNumber(String registrationNumber) {
        return Optional.ofNullable(eventsByRegistrationNumber.get(normalize(registrationNumber)));
    }

    public boolean isSpaceOccupied(int spaceNumber) {
        return registrationNumberBySpaceNumber.containsKey(spaceNumber);
    }

    public boolean isCarParked(String registrationNumber) {
        return eventsByRegistrationNumber.containsKey(normalize(registrationNumber));
    }

    public ParkingEvent save(ParkingEvent event) {
        String registrationNumber = normalize(event.getRegistrationNumber());

        eventsByRegistrationNumber.put(registrationNumber, event);
        registrationNumberBySpaceNumber.put(event.getSpaceNumber(), registrationNumber);

        return event;
    }

    public Optional<ParkingEvent> deleteByRegistrationNumber(String registrationNumber) {
        String normalizedPlate = normalize(registrationNumber);

        ParkingEvent removed = eventsByRegistrationNumber.remove(normalizedPlate);

        if (removed != null) {
            registrationNumberBySpaceNumber.remove(removed.getSpaceNumber());
        }

        return Optional.ofNullable(removed);
    }

    public int count() {
        return eventsByRegistrationNumber.size();
    }

    private String normalize(String s) {
        return s.trim().toUpperCase();
    }
}
