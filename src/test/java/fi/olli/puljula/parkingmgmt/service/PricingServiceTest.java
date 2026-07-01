package fi.olli.puljula.parkingmgmt.service;

import fi.olli.puljula.parkingmgmt.service.impl.PricingServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest {
    private final PricingService pricingService = new PricingServiceImpl();

    @Test
    void shouldCharge50CentsForOneSecondParking() {
        LocalDateTime enter = LocalDateTime.of(2026, 7, 1, 22, 0, 0);
        LocalDateTime exit = enter.plusSeconds(1);

        long price = pricingService.calculatePriceInCents(enter, exit);

        assertEquals(50, price);
    }

    @Test
    void shouldCharge50CentsForTenMinutes() {
        LocalDateTime enter = LocalDateTime.of(2026, 7, 1, 22, 0, 0);
        LocalDateTime exit = enter.plusMinutes(10);

        long price = pricingService.calculatePriceInCents(enter, exit);

        assertEquals(50, price);
    }

    @Test
    void shouldChargeOneEuroForTenMinutesAndOneSecond() {
        LocalDateTime enter = LocalDateTime.of(2026, 7, 1, 22, 0, 0);
        LocalDateTime exit = enter.plusMinutes(10).plusSeconds(1);

        long price = pricingService.calculatePriceInCents(enter, exit);

        assertEquals(100, price);
    }

    @Test
    void shouldChargeNineEurosForExactlyThreeHours() {
        LocalDateTime enter = LocalDateTime.of(2026, 7, 1, 22, 0, 0);
        LocalDateTime exit = enter.plusHours(3);

        long price = pricingService.calculatePriceInCents(enter, exit);

        assertEquals(900, price);
    }

    @Test
    void shouldChargeNineEurosThirtyCentsForThreeHoursAndOneSecond() {
        LocalDateTime enter = LocalDateTime.of(2026, 7, 1, 22, 0, 0);
        LocalDateTime exit = enter.plusHours(3).plusSeconds(1);

        long price = pricingService.calculatePriceInCents(enter, exit);

        assertEquals(930, price);
    }
}