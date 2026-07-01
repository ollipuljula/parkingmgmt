package fi.olli.puljula.parkingmgmt.api.exception;

import fi.olli.puljula.parkingmgmt.exception.CarNotFoundException;
import fi.olli.puljula.parkingmgmt.exception.CarParkedAlreadyException;
import fi.olli.puljula.parkingmgmt.exception.InvalidParkingSpaceException;
import fi.olli.puljula.parkingmgmt.exception.ParkingLotFullException;
import fi.olli.puljula.parkingmgmt.exception.ParkingSpaceNotAvailable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ParkingExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ParkingExceptionHandler.class);

    @ExceptionHandler(ParkingLotFullException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleParkingLotFull(ParkingLotFullException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(ParkingSpaceNotAvailable.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleParkingSpaceNotAvailable(ParkingSpaceNotAvailable ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(CarNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleCarNotFound(CarNotFoundException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(CarParkedAlreadyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handlearParkedAlready(CarParkedAlreadyException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(InvalidParkingSpaceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleInvalidParkingSpace(InvalidParkingSpaceException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleUnexpected(Exception ex) {

        log.error("Unexpected exception", ex);

        return new ApiError(ex.getMessage());
    }
}
