package com.sanyicloud.sanyi.common.mybatis.enums;

/**
 * 比较符
 */
public enum Operator {

    /**
     * 等于
     */
    EQUAL("="),

    /**
     * 小于
     */
    LESS_THAN("<"),

    /**
     * 小于等于
     */
    LESS_THAN_OR_EQUAL("<="),

    /**
     * 大于
     */
    GREATER_THAN(">"),

    /**
     * 大于等于
     */
    GREATER_THAN_OR_EQUAL(">="),

    ;

    /**
     * 比较符
     */
    private String character;

    private Operator(String character) {
        this.character = character;
    }

    /**
     * 获取比较符
     * @return {@link java.lang.String}
     */
    public String getCharacter() {
        return character;
    }
}
