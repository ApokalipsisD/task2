package com.epam.jwd.task2.entity.impl;

import com.epam.jwd.task2.entity.api.TextElement;

public class CodeBlock implements TextElement {
    private String value;

    public CodeBlock(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void print() {
        System.out.println(getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CodeBlock codeBlock = (CodeBlock) o;

        return value != null ? value.equals(codeBlock.value) : codeBlock.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
