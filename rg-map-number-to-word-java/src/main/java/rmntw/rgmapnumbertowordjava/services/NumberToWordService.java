package rmntw.rgmapnumbertowordjava.services;

import org.springframework.stereotype.Service;

@Service
public class NumberToWordService {
  private static final String[] units = {
    "", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine"
  };
  private static final String[] tens = {
    "", "", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty", " ninety"
  };
  private static final String[] hundreds = {"", " thousand", " million", " billion"};
  private static final String[] specificTens = {
    " ten",
    " eleven",
    " twelve",
    " thirteen",
    " fourteen",
    " fifteen",
    " sixteen",
    " seventeen",
    " eighteen",
    " nineteen"
  };
  private static final String HUNDRED_TERM = "hundred";

  public static String convertNumberToWord(int number) {
    String word = "";
    int index = 0;
    do {
      // take 3 digits at a time
      int threeDigitsNumber = number % 1000;
      if (threeDigitsNumber != 0) {
        String str = convertThreeDigitsNumberToWord(threeDigitsNumber);
        word = str + hundreds[index] + word;
      }
      index++;
      // move left by 3 digits
      number = number / 1000;
    } while (number > 0);
    return word;
  }

  private static String convertThreeDigitsNumberToWord(int number) {
    String word = "";
    int twoFirstDigitsNumber = number % 100;
    // Logic to take word from appropriate array
    // Step1 : Manage the two firsts digits
    if (twoFirstDigitsNumber < 10) {
      word = word + units[twoFirstDigitsNumber];
    } else if (twoFirstDigitsNumber < 20) {
      word = word + specificTens[twoFirstDigitsNumber % 10];
    } else {
      word = tens[twoFirstDigitsNumber / 10] + units[twoFirstDigitsNumber % 10];
    }
    // Step3 : Manage the third digits
    word = (number / 100 > 0) ? units[number / 100] + " " + HUNDRED_TERM + word : word;
    return word;
  }

}
