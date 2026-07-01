package fi.olli.puljula.parkingmgmt.api;

import fi.olli.puljula.parkingmgmt.api.exception.ParkingExceptionHandler;
import fi.olli.puljula.parkingmgmt.api.model.LotResponse;
import fi.olli.puljula.parkingmgmt.api.model.ParkRequest;
import fi.olli.puljula.parkingmgmt.api.model.ParkResponse;
import fi.olli.puljula.parkingmgmt.api.model.UnparkResponse;
import fi.olli.puljula.parkingmgmt.jpa.entity.ParkingEvent;
import fi.olli.puljula.parkingmgmt.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parking")
public class ParkingCtrl {

    private static final Logger log = LoggerFactory.getLogger(ParkingCtrl.class);

    private ParkingService parkingService;

    @Autowired
    public ParkingCtrl(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    // halli
    @GetMapping("/lot")
    public LotResponse fetchLotStatus() {
        return parkingService.getStatus();
    }

    // paikka
    @GetMapping("/space/{spaceNumber}")
    public boolean isParkingSpaceAvailable(@PathVariable int spaceNumber) {
        return parkingService.isSpaceAvailable(spaceNumber);
    }

    // parkkeeraus
    @PostMapping("/event")
    public ParkResponse park(@RequestBody ParkRequest req) {
        ParkingEvent parkingEvent = parkingService.parkCar(req.getRegistrationNumber(), req.getParkingSpaceNumber());

        return ParkResponse.from(parkingEvent);
    }

    @DeleteMapping("/event/{registrationNumber}")
    public UnparkResponse unpark(@PathVariable String registrationNumber) {
        UnparkResponse unparkResponse = parkingService.exitCar(registrationNumber);

        log.info("%.2f €".formatted(unparkResponse.getPrice() / 100.0));

        return unparkResponse;
    }

}
