package rmntw.rgmapnumbertowordjava.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rmntw.rgmapnumbertowordjava.api.dto.ConvertResponse;
import rmntw.rgmapnumbertowordjava.config.SwaggerExpose;

import static rmntw.rgmapnumbertowordjava.services.NumberToWordService.convertNumberToWord;

@RestController
@SwaggerExpose
public class RmntwController {
  @GetMapping("/convert/string")
  public String convertString(@RequestParam(value = "myNumber", defaultValue = "0") int number) {
    return String.format("Number to convert %s:%s", number, convertNumberToWord(number));
  }

  @GetMapping("/convert")
  public ConvertResponse convert(@RequestParam(value = "myNumber", defaultValue = "0") int number) {
    // Here I did a shortcut to instanciate directely the ConvertResponse, a ConvertService will be
    // cleaner code to manage specific exception
    return new ConvertResponse(number, convertNumberToWord(number));
  }
}
