package pl.t_mobile.request;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import pl.t_mobile.config.ApiConfig;

import java.util.Map;

@Slf4j
public abstract class BaseGetRequest<T, S extends ApiConfig> {
    private final Class<T> clazz;
    protected S configurationService;
    protected Response response;
    protected Map<String, Object> queryParams;
    protected Map<String, Object> pathParams;
    protected String endpoint;

    public BaseGetRequest(Class<T> clazz, S configurationService, Map<String, Object> pathParams, Map<String, Object> queryParams,
                          String endpoint) {
        this.clazz = clazz;
        this.configurationService = configurationService;
        this.pathParams = pathParams;
        this.queryParams = queryParams;
        this.endpoint = endpoint;
    }

    public T sendRequest() {
        configurationService.configure();

        var request = RestAssured.given();
        if (pathParams != null && !pathParams.isEmpty()) {
            request.pathParams(pathParams);
        }
        if (queryParams != null && !queryParams.isEmpty()) {
            request.queryParams(queryParams);
        }

        response = request.when().get(endpoint);
        log.info("Request sent");

        var jsonContent = JsonParser.parseString(response.asString());
        if (jsonContent.isJsonArray()) {
            var jsonArray = jsonContent.getAsJsonArray();
            if (!jsonArray.isEmpty()) {
                return new Gson().fromJson(jsonArray.get(0), clazz);
            }
        }

        return response.as(clazz);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }
}
