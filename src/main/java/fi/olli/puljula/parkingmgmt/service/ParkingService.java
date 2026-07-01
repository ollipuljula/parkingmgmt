package fi.olli.puljula.parkingmgmt.service;

import fi.olli.puljula.parkingmgmt.api.model.LotResponse;
import fi.olli.puljula.parkingmgmt.api.model.ExitResponse;
import fi.olli.puljula.parkingmgmt.repository.model.ParkingEvent;

import java.util.List;

public interface ParkingService {

     ParkingEvent parkCar(String registrationNumber, int spaceNumber);

     ExitResponse exitCar(String registrationNumber);

     LotResponse getStatus();

     List<ParkingEvent> getActiveParkingEvents();

     boolean isSpaceOccupied(int spaceNumber);
}
