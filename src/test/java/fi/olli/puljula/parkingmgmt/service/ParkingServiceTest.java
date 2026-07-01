package fi.olli.puljula.parkingmgmt.service;

import fi.olli.puljula.parkingmgmt.exception.ParkingLotFullException;
import fi.olli.puljula.parkingmgmt.exception.ParkingSpaceNotAvailableException;
import fi.olli.puljula.parkingmgmt.repository.InMemoryParkingRepository;
import fi.olli.puljula.parkingmgmt.service.impl.ParkingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingServiceTest {
    @Mock
    private InMemoryParkingRepository repository;

    @Mock
    private PricingService pricingService;

    @InjectMocks
    private ParkingServiceImpl parkingService;

    @Test
    void shouldThrowExceptionWhenParkingLotIsFull() {
        when(repository.count()).thenReturn(5);

        assertThrows(
                ParkingLotFullException.class,
                () -> parkingService.parkCar("ABC-123", 1)
        );
    }

    @Test
    void shouldThrowExceptionWhenParkingSpaceIsOccupied() {

        when(repository.count()).thenReturn(2);
        when(repository.isSpaceOccupied(3)).thenReturn(true);

        assertThrows(
                ParkingSpaceNotAvailableException.class,
                () -> parkingService.parkCar("ABC-123", 3)
        );
    }
}