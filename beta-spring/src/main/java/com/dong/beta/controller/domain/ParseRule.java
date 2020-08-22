package com.dong.beta.controller.domain;

public class ParseRule {
    private Integer id;

    private Integer ruleId;

    private String ruleName;

    private Byte ruleType;

    private String splitChar;

    private Integer fieldId;

    private Byte ruleStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Byte getRuleType() {
        return ruleType;
    }

    public void setRuleType(Byte ruleType) {
        this.ruleType = ruleType;
    }

    public String getSplitChar() {
        return splitChar;
    }

    public void setSplitChar(String splitChar) {
        this.splitChar = splitChar == null ? null : splitChar.trim();
    }

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Byte getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(Byte ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    @Override
    public String toString() {
        return "ParseRule{" +
                "id=" + id +
                ", ruleId=" + ruleId +
                ", ruleName='" + ruleName + '\'' +
                '}';
    }
}