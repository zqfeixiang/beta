package com.dong.beta.strategy.enums;

public enum InsertTypeEnum {
    INSERT_ONE("insertOne"),
    INSERT_TWO("insertTwo");

    private String flag;

    InsertTypeEnum(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
}
