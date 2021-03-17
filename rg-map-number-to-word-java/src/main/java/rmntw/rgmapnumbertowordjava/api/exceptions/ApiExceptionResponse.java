package rmntw.rgmapnumbertowordjava.api.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * This class is used to uniformize the response of any API in pnehelper
 */
@Data
public class ApiExceptionResponse {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime time;
  private String code;
  private String message;
  private Object data;

  private ApiExceptionResponse() {
    time = LocalDateTime.now();
  }

  public ApiExceptionResponse(String code,
      String message) {
    this();
    this.code = code;
    this.message = message;
  }

  public ApiExceptionResponse(String code,
      String message,
      Object data) {
    this(code, message);
    this.data = data;
  }
}
