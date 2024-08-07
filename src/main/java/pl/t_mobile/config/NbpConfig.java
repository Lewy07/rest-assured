package pl.t_mobile.config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class NbpConfig implements ApiConfig {
    private static final String baseUrl = "https://api.nbp.pl/api";

    @Override
    public void configure() {
        RestAssured.baseURI = baseUrl;
        RestAssured.given().contentType(ContentType.JSON);
    }
}
