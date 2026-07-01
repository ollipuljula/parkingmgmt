package fi.olli.puljula.parkingmgmt.service.impl;

import fi.olli.puljula.parkingmgmt.service.PricingService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PricingServiceImpl implements PricingService {

    public int calculatePriceInCents(LocalDateTime entryTime, LocalDateTime exitTime) {
        long seconds = Duration.between(entryTime, exitTime).toSeconds();

        if (seconds <= 0) {
            return 0;
        }

        long periods = (seconds + 599) / 600; // alkava 10 min, 600s = 10min

        long firstPeriods = Math.min(periods, 18);
        long additionalPeriods = Math.max(0, periods - 18);

        return (int) (firstPeriods * 50 + additionalPeriods * 30);
    }
}
