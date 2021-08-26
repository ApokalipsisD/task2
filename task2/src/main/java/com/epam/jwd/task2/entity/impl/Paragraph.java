package com.epam.jwd.task2.entity.impl;

import com.epam.jwd.task2.entity.api.TextElement;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements TextElement {
    private List<TextElement> sentences = new ArrayList<>();

    public List<TextElement> getSentences() {
        return sentences;
    }

    public void setSentences(List<TextElement> sentences) {
        this.sentences = sentences;
    }

    public void addTextElement(TextElement element) {
        sentences.add(element);
    }

    public void removeTextElement(TextElement element) {
        sentences.remove(element);
    }

    @Override
    public void print() {
        sentences.forEach(TextElement::print);
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paragraph paragraph = (Paragraph) o;

        return sentences != null ? sentences.equals(paragraph.sentences) : paragraph.sentences == null;
    }

    @Override
    public int hashCode() {
        return sentences != null ? sentences.hashCode() : 0;
    }

    @Override
    public String toString() {
        if (sentences != null) {
            StringBuilder sb = new StringBuilder();
            for (TextElement textElement : sentences) {
                sb.append(textElement.toString());
            }
            return sb.toString();
        } else {
            return "Список предложений пустой";
        }
    }
}
