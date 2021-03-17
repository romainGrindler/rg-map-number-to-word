package rmntw.rgmapnumbertowordjava.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConvertResponse {
    private int number;
    private String word;
}
