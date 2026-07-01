package fi.olli.puljula.parkingmgmt.service;

import fi.olli.puljula.parkingmgmt.api.model.LotResponse;
import fi.olli.puljula.parkingmgmt.api.model.ParkRequest;
import fi.olli.puljula.parkingmgmt.api.model.UnparkResponse;
import fi.olli.puljula.parkingmgmt.jpa.entity.ParkingEvent;

import java.util.List;

public interface ParkingService {

     ParkingEvent parkCar(String registrationNumber, int spaceNumber);

     UnparkResponse exitCar(String registrationNumber);

     LotResponse getStatus();

     List<ParkingEvent> getActiveParkingEvents();

     boolean isSpaceAvailable(int spaceNumber);
}
