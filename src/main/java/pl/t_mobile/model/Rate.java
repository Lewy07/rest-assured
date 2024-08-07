package pl.t_mobile.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rate {
    private String currency;
    private String code;
    private double mid;

    @Override
    public String toString() {
        return String.format("Currency: %s, code: %s, Mid: %f",
                currency, code, mid);
    }
}
