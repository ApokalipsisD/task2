package com.epam.jwd.task2.entity.impl;

import com.epam.jwd.task2.entity.api.TextElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Text implements TextElement, Serializable {
    private List<TextElement> paragraphsAndCodeBlocks = new ArrayList<>();

    public List<TextElement> getParagraphsAndCodeBlocks() {
        return paragraphsAndCodeBlocks;
    }

    public void setParagraphsAndCodeBlocks(List<TextElement> paragraphsAndCodeBlocks) {
        this.paragraphsAndCodeBlocks = paragraphsAndCodeBlocks;
    }

    public void addTextElement(TextElement element){
        paragraphsAndCodeBlocks.add(element);
    }

    public void removeTextElement(TextElement element){
        paragraphsAndCodeBlocks.remove(element);
    }

    @Override
    public void print() {
        paragraphsAndCodeBlocks.forEach(TextElement::print);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Text text = (Text) o;

        return paragraphsAndCodeBlocks != null ? paragraphsAndCodeBlocks.equals(text.paragraphsAndCodeBlocks) : text.paragraphsAndCodeBlocks == null;
    }

    @Override
    public int hashCode() {
        return paragraphsAndCodeBlocks != null ? paragraphsAndCodeBlocks.hashCode() : 0;
    }

    @Override
    public String toString(){
        if (paragraphsAndCodeBlocks != null) {
            StringBuilder sb = new StringBuilder();
            for (TextElement textElement : paragraphsAndCodeBlocks) {
                sb.append(textElement.toString());
            }
            return sb.toString();
        } else {
            return "Список абзацев пустой";
        }
    }

}
