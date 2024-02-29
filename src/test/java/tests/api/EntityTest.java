package tests.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import helpers.api.Endpoints;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pojo.EntityData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static helpers.api.Specifications.*;
import static io.restassured.RestAssured.*;

public class EntityTest {

    @Test
    @Epic("Check create entity")
    public void createEntity() throws JsonProcessingException {
        Integer entityId;
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData entityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = BaseStep.postCreateWithExtractId(entityData);
        Response response = BaseStep.getResponse(entityId);
        JsonMapper mapper = new JsonMapper();
        Assertions.assertEquals(entityId, mapper.readValue(response.asPrettyString(), EntityData.class).getId());
        BaseStep.deleteEntity(entityId);
    }


    @Test
    @Epic("Check get entity")
    public void getEntity() throws JsonProcessingException {
        Integer entityId;
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = BaseStep.postCreateWithExtractId(postEntityData);
        Response response = BaseStep.getResponse(entityId);
        JsonMapper mapper = new JsonMapper();
        EntityData getEntityData = mapper.readValue(response.asPrettyString(), EntityData.class);
        Assertions.assertEquals(entityId, getEntityData.getId());
        BaseStep.deleteEntity(entityId);
    }

    @Test
    @Epic("Check get ALL entity")
    public void getAllEntity() {
        Integer entityId;
        List<EntityData> entityList = BaseStep.getAllEntity();
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = BaseStep.postCreateWithExtractId(postEntityData);
        List<EntityData> changedEntityList = BaseStep.getAllEntity();
        Assertions.assertNotEquals(entityList.size(), changedEntityList.size());
        BaseStep.deleteEntity(entityId);
    }

    @Test
    @Epic("Check change entity")
    public void patchEntity() throws JsonProcessingException {
        Integer entityId;
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = BaseStep.postCreateWithExtractId(postEntityData);
        JsonMapper mapper = new JsonMapper();
        EntityData postPatchEntityData = new EntityData(null, "Измененная сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        given()
                .log().all()
                .spec(requestSpec)
                .body(postPatchEntityData)
                .when()
                .patch(Endpoints.PATCH.getEndpoint() + entityId)
                .then().log().all()
                .statusCode(204);
        Response patchResponse = BaseStep.getResponse(entityId);
        EntityData getPatchEntity = mapper.readValue(patchResponse.asPrettyString(), EntityData.class);
        Assertions.assertEquals(getPatchEntity.getTitle(), postPatchEntityData.getTitle());
        BaseStep.deleteEntity(entityId);
    }

    @Test
    @Epic("Check delete entity")
    public void deleteEntity() {
        Integer entityId;
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = BaseStep.postCreateWithExtractId(postEntityData);
        BaseStep.deleteEntity(entityId);
        given().spec(requestSpec).when().get(Endpoints.GET.getEndpoint() + entityId).then().log().all().statusCode(500);
    }

}
