package pl.t_mobile.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import pl.t_mobile.model.ExchangeRatesTable;
import pl.t_mobile.model.Rate;
import pl.t_mobile.request.GetRatesRequest;

import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

@Slf4j
public class RatesSteps {

    private ExchangeRatesTable response;

    @When("Get exchange rates")
    public void getExchangeRates() {
        var request = new GetRatesRequest(Map.of("table", "A"), null,
                "/exchangerates/tables/{table}");
        response = request.sendRequest();
        log.info("Get exchange rates status code: {}", request.getStatusCode());
    }

    @Then("View rate for currency code {string}")
    public void viewRateForCurrencyCode(String currencyCode) {
        var result = response.getRatesByCode(currencyCode);
        assertNotNull(result, "No currency with code " + currencyCode);

        log.info("The rate for currency code {}: {}", currencyCode, result);
    }

    @And("View rate for currency named {string}")
    public void viewRateForCurrencyNamed(String currencyName) {
        var result = response.getRatesByCurrency(currencyName);
        assertNotNull(result, "No currency named " + currencyName);

        log.info("The rate for currency named {}: {}", currencyName, result);
    }

    @And("View currencies with rates above {double}")
    public void viewCurrenciesWithRatesAbove(double limitRate) {
        var result = response.getRatesHigherThanValue(limitRate);
        assertFalse(result.isEmpty(), "No currency with higher rate");

        for (Rate rate : result) {
            log.info("The rate {} with higher currency than {}", rate, limitRate);
        }
    }

    @And("View currencies with rates below {double}")
    public void viewCurrenciesWithRatesBelow(double limitRate) {
        var result = response.getRatesLowerThanValue(limitRate);
        assertFalse(result.isEmpty(), "No currency with lower rate");

        for (Rate rate : result) {
            log.info("The rate {} with lower currency than {}", rate.toString(), limitRate);
        }
    }
}
