package fi.olli.puljula.parkingmgmt.api;

import fi.olli.puljula.parkingmgmt.api.model.LotResponse;
import fi.olli.puljula.parkingmgmt.api.model.EnterRequest;
import fi.olli.puljula.parkingmgmt.api.model.EnterResponse;
import fi.olli.puljula.parkingmgmt.api.model.ExitResponse;
import fi.olli.puljula.parkingmgmt.api.model.ParkingSpaceStatusResponse;
import fi.olli.puljula.parkingmgmt.repository.model.ParkingEvent;
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

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private static final Logger log = LoggerFactory.getLogger(ParkingController.class);

    private ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/lot")
    public LotResponse fetchLotStatus() {
        return LotResponse.from(parkingService.getStatus());
    }

    @GetMapping("/space/{spaceNumber}")
    public boolean isParkingSpaceAvailable(@PathVariable int spaceNumber) {
        return !parkingService.isSpaceOccupied(spaceNumber);
    }

    @GetMapping("/space")
    public List<ParkingSpaceStatusResponse> getParkingSpaces() {
        return parkingService.getActiveParkingEvents()
                .stream()
                .map(ParkingSpaceStatusResponse::from)
                .toList();
    }

    @PostMapping("/event")
    public EnterResponse enter(@RequestBody EnterRequest req) {
        ParkingEvent parkingEvent = parkingService.parkCar(req.getRegistrationNumber(), req.getParkingSpaceNumber());

        return EnterResponse.from(parkingEvent);
    }

    @DeleteMapping("/event/{registrationNumber}")
    public ExitResponse exit(@PathVariable String registrationNumber) {
        ExitResponse exitResponse = parkingService.exitCar(registrationNumber);

        log.info("%.2f €".formatted(exitResponse.getPrice() / 100.0));

        return exitResponse;
    }

}
