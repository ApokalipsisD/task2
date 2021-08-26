package com.epam.jwd.task2.service.parser;

import com.epam.jwd.task2.entity.api.TextElement;

import java.util.List;

public abstract class Parser {
    private Parser next;

    protected List<TextElement> splitNext(Parser next, String text) {
        this.next = next;
        return next.split(text);
    }

    public Parser getNext() {
        return next;
    }

    public abstract List<TextElement> split(String text);
}
