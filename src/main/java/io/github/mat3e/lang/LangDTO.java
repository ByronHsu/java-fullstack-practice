package io.github.mat3e.lang;

class LangDTO {
    private Integer id;
    private String code;

    LangDTO(Lang lang) {
        this.id = lang.getId();
        this.code = lang.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
