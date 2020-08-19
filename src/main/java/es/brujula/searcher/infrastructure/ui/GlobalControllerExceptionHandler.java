package es.brujula.searcher.infrastructure.ui;

import es.brujula.searcher.domain.hotel.exception.HotelAlreadyExistsException;
import es.brujula.searcher.domain.hotel.exception.HotelNotFoundException;
import es.brujula.searcher.infrastructure.ui.rest.ApiError;
import io.jsonwebtoken.JwtException;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Locale;

@RestControllerAdvice
public final class GlobalControllerExceptionHandler {

    private static final Logger logger = LogManager.getLogger();

    private MessageSource messageSource;

    public GlobalControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleHotelNotFound(RuntimeException ex, Locale locale) {
        return this.errorMessageAssemble(ex.getMessage(), locale,
                "error.hotel.notfound", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HotelAlreadyExistsException.class)
    public ResponseEntity<Object> handleHotelAlreadyExists(RuntimeException ex, Locale locale) {
        return this.errorMessageAssemble(ex.getMessage(), locale,
                "error.hotel.already.exist", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleInvalidParams(RuntimeException ex, Locale locale) {
        return this.errorMessageAssemble(ex.getMessage(), locale,
                "error.invalid.params", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<Object> handleJwtErrors(RuntimeException ex, Locale locale) {
        return this.errorMessageAssemble(ex.getMessage(), locale,
                "error.jwt", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSqlErrors(RuntimeException ex, Locale locale) {
        return this.errorMessageAssemble(ex.getMessage(), locale,
                "error.db", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> errorMessageAssemble(String messageRuntimeEx, Locale locale,
                                                        String messageSourceProperties, HttpStatus status) {
        logger.error(messageRuntimeEx);
        String message = messageSource.getMessage(messageSourceProperties, new String[]{}, locale);

        ApiError apiError = ApiError.create(status, message, StringUtils.isNotBlank(messageRuntimeEx) ?
                messageRuntimeEx : status.toString());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
