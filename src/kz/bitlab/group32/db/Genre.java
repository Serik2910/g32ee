package kz.bitlab.group32.db;

public class Genre {
    private Integer id;
    private String name;
    private String name_rus;

    public Genre(Integer id, String name, String name_rus) {
        this.id = id;
        this.name = name;
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

    public String getName_rus() {
        return name_rus;
    }

    public void setName_rus(String name_rus) {
        this.name_rus = name_rus;
    }
}
