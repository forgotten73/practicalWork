package tests.api;

import helpers.api.Endpoints;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.EntityData;

import java.util.List;

import static helpers.api.Specifications.*;
import static io.restassured.RestAssured.given;

public class EntityStep {

    /**
     * Получение ответа
     *
     * @param id идентификатор сущности
     */
    @Step("Get Entity")
    public static Response getResponse(Integer id) {
        return given()
                .spec(requestSpec).log().all()
                .when()
                .get(Endpoints.GET.getEndpoint() + id)
                .then().log().all()
                .spec(responseSpecOK200)
                .extract()
                .response();
    }

    /**
     * Отправка запроса на создание сущности
     *
     * @param req тело создания сущности
     */
    @Step("Post Entity")
    public static Integer postCreateWithExtractId(EntityData req) {
        return given()
                .spec(requestSpec).log().all()
                .body(req)
                .when()
                .post(Endpoints.CREATE.getEndpoint())
                .then().log().all()
                .spec(responseSpecOK200)
                .extract().jsonPath().get();
    }

    /**
     * Получение всех сущностей
     */
    @Step("Get ALL Entity")
    public static List<EntityData> getAllEntity() {
        return given()
                .spec(requestSpec).log().all()
                .when()
                .get(Endpoints.GET_ALL.getEndpoint())
                .then().log().all()
                .spec(responseSpecOK200)
                .extract().body().jsonPath().getList("entity", EntityData.class);
    }

    /**
     * Изменение сущности
     *
     * @param req pojo класс
     * @param id  идентификатор изменяемой сущности
     */
    public static void getPatchEntity(EntityData req, Integer id) {
        given()
                .spec(requestSpec).log().all()
                .body(req)
                .when()
                .patch(Endpoints.PATCH.getEndpoint() + id)
                .then().log().all()
                .statusCode(204);
    }

    /**
     * Удаление сущности по идентификатору
     *
     * @param id тело создания сущности
     */
    @Step("Delete Entity")
    public static void deleteEntity(Integer id) {
        given()
                .when()
                .spec(requestSpec).log().all()
                .delete(Endpoints.DELETE.getEndpoint() + id)
                .then().log().all()
                .statusCode(204);
        given()
                .spec(requestSpec).log().all()
                .when()
                .get(Endpoints.GET.getEndpoint() + id)
                .then().log().all()
                .statusCode(500);
    }
}
