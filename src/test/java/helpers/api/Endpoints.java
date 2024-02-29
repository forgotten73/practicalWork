package helpers.api;

import pojo.EntityData;

public enum Endpoints {
    /**
     * Конечные точки в запросе:
     * CREATE создание сущности
     * GET получение сущности
     * GET_ALL получение всех сущностей
     * PATCH изменение сущности
     * DELETE удаление сущности
     */
    CREATE("create"),
    GET("get/"),
    GET_ALL("getAll"),
    PATCH("patch/"),
    DELETE("delete/");

    public String endpoint;

    /**
     * Конструктор конечной точки
     */
    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Получить значение endpoint из enum
     */
    public String getEndpoint() {
        return endpoint;
    }
}
