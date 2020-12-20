package io.github.mat3e;

class Lang {
    private final Long id;
    private String greetingMsg;
    private String code;

    public Lang(Long id, String greetingMsg, String code) {
        this.id = id;
        this.greetingMsg = greetingMsg;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getGreetingMsg() {
        return greetingMsg;
    }

    public void setGreetingMsg(String greetingMsg) {
        this.greetingMsg = greetingMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
