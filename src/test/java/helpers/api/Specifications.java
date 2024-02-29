package helpers.api;

import helpers.ConfProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    /**
     * requestSpec переменная с установленным базовым адресом
     * и типом контента
     */
    public static RequestSpecification requestSpec =
            new RequestSpecBuilder()
                    .setBaseUri(ConfProperties.getProperty("apiUrl"))
                    .setContentType(ContentType.JSON)
                    .build();

    /**
     * responseSpecOK200 переменная с установленным базовым
     * статусом кода
     */
    public static ResponseSpecification responseSpecOK200 =
            new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .build();
}
