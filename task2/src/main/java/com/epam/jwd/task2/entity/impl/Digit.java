package com.epam.jwd.task2.entity.impl;

import com.epam.jwd.task2.entity.api.TextElement;

public class Digit implements TextElement {
    private int value;

    public Digit(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    @Override
    public void print() {
        System.out.print(getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Digit digit = (Digit) o;

        return value == digit.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
