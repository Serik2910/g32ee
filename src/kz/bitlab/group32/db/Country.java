package kz.bitlab.group32.db;

public class Country {
    private Integer id;
    private String name;
    private String code;
    private String name_rus;

    public Country(Integer id, String name, String code, String name_rus) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.name_rus = name_rus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName_rus() {
        return name_rus;
    }

    public void setName_rus(String name_rus) {
        this.name_rus = name_rus;
    }
}
