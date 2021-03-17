package rmntw.rgmapnumbertowordjava;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static rmntw.rgmapnumbertowordjava.services.NumberToWordService.convertNumberToWord;

@SpringBootApplication
@Slf4j
public class RgMapNumberToWordJavaApplication {

  public static void main(String[] args) {
    SpringApplication.run(RgMapNumberToWordJavaApplication.class, args);

    System.out.println("For a friendly usage of the API: http://localhost:8080");
    System.out.println("Or use console (less friendly error management!!)");
    Scanner input = new Scanner(System.in);
    boolean exit = false;
    do {
      try {
        System.out.println("Enter a value (only pos integer)|'exit' to stop server:");
        String value = input.next();
        if (String.valueOf(value).equals("exit")) {
          exit = true;
          break;
        }
        System.out.println("=>" + convertNumberToWord(Integer.parseInt(value)));
      } catch (Exception ex) {
        log.error(ex.toString());
      }
    } while (!exit);
  }
}
