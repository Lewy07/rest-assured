package pl.t_mobile.request;

import pl.t_mobile.config.NbpConfig;
import pl.t_mobile.model.ExchangeRatesTable;

import java.util.Map;

public class GetRatesRequest extends BaseGetRequest<ExchangeRatesTable, NbpConfig> {

    public GetRatesRequest(Map<String, Object> pathParams, Map<String, Object> queryParams, String endpoint) {
        super(ExchangeRatesTable.class, new NbpConfig(), pathParams, queryParams, endpoint);
    }
}
