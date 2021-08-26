package com.epam.jwd.task2.entity.impl;

import com.epam.jwd.task2.entity.api.TextElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sentence implements TextElement {
    private List<TextElement> wordsAndSymbols = new ArrayList<>();

    public List<TextElement> getWordsAndSymbols() {
        return wordsAndSymbols;
    }

    public void setWordsAndSymbols(List<TextElement> wordsAndSymbols) {
        this.wordsAndSymbols = wordsAndSymbols;
    }

    public void addTextElement(TextElement element) {
        wordsAndSymbols.add(element);
    }

    public void removeTextElement(TextElement element) {
        wordsAndSymbols.remove(element);
    }

    public List<TextElement> getAllWords() {
        return wordsAndSymbols.stream()
                .filter(word -> word instanceof Word)
                .collect(Collectors.toList());
    }

    public int getNumberOfWordsInSentence() {
        return getAllWords().size();
    }

    @Override
    public void print() {
        wordsAndSymbols.forEach(TextElement::print);
        System.out.print(" ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sentence sentence = (Sentence) o;

        return wordsAndSymbols != null ? wordsAndSymbols.equals(sentence.wordsAndSymbols) : sentence.wordsAndSymbols == null;
    }

    @Override
    public int hashCode() {
        return wordsAndSymbols != null ? wordsAndSymbols.hashCode() : 0;
    }

    @Override
    public String toString() {
        if (wordsAndSymbols != null) {
            StringBuilder sb = new StringBuilder();
            for (TextElement textElement : wordsAndSymbols) {
                sb.append(textElement.toString());
            }
            return sb + " ";
        } else {
            return "Список слов и символов пустой";
        }
    }
}
