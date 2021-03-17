package rmntw.rgmapnumbertowordjava.api.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
public class DefaultException extends CustomApiException {

  public DefaultException(String functionnalCode, String message, Exception rootException) {
    super(functionnalCode, message, rootException);
  }

  public static DefaultException concoleDefaultException(Exception e) {
    log.error("handleGlobalException: ", e);
    return new DefaultException("MONGO_001", "Error in concertion by direct console access: " + e.getMessage(), e);
  }

  @ControllerAdvice
  @Order(Ordered.LOWEST_PRECEDENCE)
  public static class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DefaultException.class, Exception.class})
    protected ApiExceptionResponse handleGlobalException(Exception e) {
      log.error("handleGlobalException: ", e);
      return new DefaultException("GE_001", e.getMessage(), e).asExceptionResponse();
    }
  }
}
