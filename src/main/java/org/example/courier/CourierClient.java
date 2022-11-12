package org.example.courier;
import io.restassured.response.ValidatableResponse;
import org.example.Client;

import static io.restassured.RestAssured.given;

public class CourierClient extends Client {

    private static String PATH = "/api/v1/courier/";
    private static String PATH_LOGIN = "/api/v1/courier/login/";
    private static String PATH_DELETE = "/api/v1/courier/";

    public ValidatableResponse create(Courier courier){

        return given()
                .spec(getSpec())//настраивает запрос который будет исполнен
                .body(courier)
                .when()
                .post(PATH)
                .then();

    }
    public ValidatableResponse login(Credentials credentials){
        return given()
                .spec(getSpec())//настраивает запрос который будет исполнен
                .body(credentials)
                .when()
                .post(PATH_LOGIN)
                .then();
    }

    public ValidatableResponse delete(int id){
        return given()
                .spec(getSpec())//настраивает запрос который будет исполнен
                .when()
                .delete(PATH_DELETE +id)
                .then();
    }

}
