package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Класс для дисериализации и сериализации сущности
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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

        public Integer getId() {
            return id;
        }

        public String getAdditional_info() {
            return additional_info;
        }

        public Integer getAdditional_number() {
            return additional_number;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Addition getAddition() {
        return addition;
    }

    public Boolean getVerified() {
        return verified;
    }

    public ArrayList<Integer> getImportant_numbers() {
        return important_numbers;
    }
}
