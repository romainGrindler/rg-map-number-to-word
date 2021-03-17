package rmntw.rgmapnumbertowordjava.api.exceptions;

/**
 * This class is used to uniformize any exception manage in pnehelper
 */
public abstract class CustomApiException extends RuntimeException {

  // Specific code of the exception
  private String functionalCode;
  // Field to match exception from partner
  @SuppressWarnings("PMD")
  private Exception rootException;

  public CustomApiException(String functionalCode,
      String message,
      Exception rootException) {
    super(message);
    this.functionalCode = functionalCode;
    this.rootException = rootException;
  }

  public ApiExceptionResponse asExceptionResponse() {
    return new ApiExceptionResponse(this.functionalCode, this.getMessage());
  }
}
