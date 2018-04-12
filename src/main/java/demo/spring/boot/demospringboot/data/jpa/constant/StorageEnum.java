package demo.spring.boot.demospringboot.data.jpa.constant;

/**
 * 2018/4/8    Created by   chao
 */
public enum StorageEnum {
    SEARCH_HISTORY("search_history"), FIND_RECORD("find_record");

    private String category;

    StorageEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "StorageEnum{" +
                "category='" + category + '\'' +
                '}';
    }
}
