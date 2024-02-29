package pojo;

import java.util.ArrayList;

/**
 * Класс для дисериализации и сериализации сущности
 */
public class EntityData {
    private Integer id;
    private String title;
    private Addition addition;
    private Boolean verified;
    private ArrayList<Integer> important_numbers;

    /**
     * Конструктор для заполнения сущности при создании
     */
    public EntityData(Integer id, String title, Boolean verified, Addition addition, ArrayList<Integer> important_numbers) {
        this.id = id;
        this.title = title;
        this.addition = addition;
        this.verified = verified;
        this.important_numbers = important_numbers;
    }

    public EntityData() {

    }

    public static class Addition {
        private Integer id;
        private String additional_info;
        private Integer additional_number;

        /**
         * Конструктор вложенного списка
         */
        public Addition(Integer id, String additional_info, Integer additional_number) {
            this.id = id;
            this.additional_info = additional_info;
            this.additional_number = additional_number;
        }

        public Addition() {

        }
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
