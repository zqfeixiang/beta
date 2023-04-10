package com.dong.beta.constants;

import com.dong.beta.exception.InvalidOperatorException;

/**
 * @author dzq
 * @date 11/23/22 11:39
 */
public enum Operator {
    GTE(">="),
    LTE("<="),
    EQ("=="),

    /**
     * Type safe equals
     */
    TSEQ("==="),
    NE("!="),

    /**
     * Type safe not equals
     */
    TSNE("!=="),
    LT("<"),
    GT(">"),
    REGEX("=~"),
    NIN("NIN"),
    IN("IN"),
    CONTAINS("CONTAINS"),
    ALL("ALL"),
    SIZE("SIZE"),
    EXISTS("EXISTS"),
    TYPE("TYPE"),
    MATCHES("MATCHES"),
    EMPTY("EMPTY"),
    SUBSETOF("SUBSETOF");

    private final String operatorString;

    Operator(String operatorString) {
        this.operatorString = operatorString;
    }

    public static Operator fromString(String operatorString) {
        for (Operator operator : values()) {
            if (operator.operatorString.equals(operatorString.toUpperCase())) {
                return operator;
            }
        }
        throw new InvalidOperatorException("Filter operator " + operatorString + " is not supported!");
    }

    @Override
    public String toString() {
        return operatorString;
    }
}
