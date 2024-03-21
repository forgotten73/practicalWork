package tests.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.qameta.allure.Epic;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import pojo.EntityData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Execution(ExecutionMode.SAME_THREAD)
public class EntityTest {

    private Integer entityId;

    @Test
    @Epic("Check create entity")
    public void createEntity() throws JsonProcessingException {
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData entityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = EntityStep.postCreateWithExtractId(entityData);
        Response response = EntityStep.getResponse(entityId);
        JsonMapper mapper = new JsonMapper();
        Assertions.assertEquals(entityId, mapper.readValue(response.asPrettyString(), EntityData.class).getId());
    }

    @Test
    @Epic("Check get entity")
    public void getEntity() throws JsonProcessingException {
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = EntityStep.postCreateWithExtractId(postEntityData);
        Response response = EntityStep.getResponse(entityId);
        JsonMapper mapper = new JsonMapper();
        EntityData getEntityData = mapper.readValue(response.asPrettyString(), EntityData.class);
        Assertions.assertEquals(entityId, getEntityData.getId());
    }

    @Test
    @Epic("Check get ALL entity")
    public void getAllEntity() {
        List<EntityData> entityList = EntityStep.getAllEntity();
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = EntityStep.postCreateWithExtractId(postEntityData);
        List<EntityData> changedEntityList = EntityStep.getAllEntity();
        Assertions.assertNotEquals(entityList.size(), changedEntityList.size());
    }

    @Test
    @Epic("Check change entity")
    public void patchEntity() throws JsonProcessingException {
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = EntityStep.postCreateWithExtractId(postEntityData);
        JsonMapper mapper = new JsonMapper();
        EntityData postPatchEntityData = new EntityData(null, "Измененная сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        EntityStep.getPatchEntity(postPatchEntityData, entityId);
        Response patchResponse = EntityStep.getResponse(entityId);
        EntityData getPatchEntity = mapper.readValue(patchResponse.asPrettyString(), EntityData.class);
        Assertions.assertEquals(getPatchEntity.getTitle(), postPatchEntityData.getTitle());
    }

    @Test
    @Epic("Check delete entity")
    public void deleteEntity() {
        EntityData.Addition additionData = new EntityData.Addition(null, "Инфо о сущности", 2);
        EntityData postEntityData = new EntityData(null, "Новая сущность", true, additionData, new ArrayList<>(Arrays.asList(1, 2, 3)));
        entityId = EntityStep.postCreateWithExtractId(postEntityData);
    }

    @AfterEach
    public void deleteEntityAfterEach() {
        EntityStep.deleteEntity(entityId);
    }
}
