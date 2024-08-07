package pl.t_mobile.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRatesTable {
    private String table;
    private String no;
    private String effectiveDate;
    private List<Rate> rates;

    public Rate getRatesByCurrency(String currency) {
        return rates.stream().filter(rate -> rate.getCurrency().equals(currency)).findFirst().orElse(null);
    }

    public Rate getRatesByCode(String code) {
        return rates.stream().filter(rate -> rate.getCode().equals(code)).findFirst().orElse(null);
    }

    public List<Rate> getRatesLowerThanValue(double value) {
        return rates.stream().filter(rate -> rate.getMid() < value).toList();
    }

    public List<Rate> getRatesHigherThanValue(double value) {
        return rates.stream().filter(rate -> rate.getMid() > value).toList();
    }
}
