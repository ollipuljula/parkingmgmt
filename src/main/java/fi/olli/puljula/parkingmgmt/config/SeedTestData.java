package fi.olli.puljula.parkingmgmt.config;

import fi.olli.puljula.parkingmgmt.api.model.ParkRequest;
import fi.olli.puljula.parkingmgmt.service.ParkingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class SeedTestData implements CommandLineRunner {

    private final ParkingService parkingService;

    public SeedTestData(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @Override
    public void run(String... args) {
        System.out.println("foobar");
        parkingService.parkCar("ABC-123", 1);
        parkingService.parkCar("XYZ-456", 2);
        parkingService.parkCar("GFO-789", 4);
    }
}
