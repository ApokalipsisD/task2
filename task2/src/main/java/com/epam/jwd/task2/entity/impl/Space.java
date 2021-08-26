package com.epam.jwd.task2.entity.impl;

import com.epam.jwd.task2.entity.api.TextElement;

public class Space implements TextElement {
    private String value;

    public Space(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void print() {
        System.out.print(getValue());
    }

    @Override
    public String toString() {
        return getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Space space = (Space) o;

        return value != null ? value.equals(space.value) : space.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
