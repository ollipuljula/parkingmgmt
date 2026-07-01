package fi.olli.puljula.parkingmgmt.service;

import java.time.LocalDateTime;

public interface PricingService {

    int calculatePriceInCents(LocalDateTime entryTime, LocalDateTime exitTime);
}
