package com.epam.jwd.task2.service.operations;

import com.epam.jwd.task2.entity.impl.Paragraph;
import com.epam.jwd.task2.entity.impl.Sentence;
import com.epam.jwd.task2.entity.api.TextElement;
import com.epam.jwd.task2.entity.impl.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Operation {
    private static final Logger logger = LogManager.getLogger(Operation.class);

    private static final int FIXED_WORD_LENGTH = 4;
    private static final String FINDING_WORD_GIVEN_LENGTH_LOG_MESSAGE = "Finding words given length in interrogative sentence.";
    private static final String GETTING_QUESTION_SENTENCES_LOG_MESSAGE = "Getting question sentences.";
    private static final String SHOW_RECEIVING_WORDS_LOG_MESSAGE = "Getting receiving words.";
    private static final String STARING_SWAP_WORDS_LOG_MESSAGE = "Starting to switch first and last word in sentence.";
    private static final String SWAP_FIRST_AND_LAST_WORDS_LOG_MESSAGE = "Swap first and last word in sentences.";
    private static final String SORT_WORDS_ALPHABET_LOG_MESSAGE = "Sorting words by alphabet.";
    private static final String SORT_WORDS_ALPHABET_SUCCESSFUL_LOG_MESSAGE = "Sort by alphabet is successful.";
    private final List<TextElement> allSentences = new ArrayList<>();
    private final List<TextElement> allWordsAndSymbols = new ArrayList<>();
    private List<TextElement> text;

    public Operation(List<TextElement> fullText) {
        this.text = fullText;
    }

    public void setNewText(List<TextElement> text) {
        this.text = text;
    }

    public void uniteAllElements() {
        collectAllSentences();
        collectAllWordsAndSymbols();
    }

    public List<TextElement> getFullText() {
        return text;
    }

    public List<TextElement> getAllSentences() {
        return allSentences;
    }

    public List<TextElement> getAllWordsAndSymbols() {
        return allWordsAndSymbols;
    }

    public void printText() {
        getFullText().forEach(TextElement::print);
    }

    public void collectAllSentences() {
        text.stream()
                .filter(textElement -> textElement instanceof Paragraph)
                .map(textElement -> ((Paragraph) textElement).getSentences())
                .forEach(allSentences::addAll);
    }

    public void collectAllWordsAndSymbols() {
        allSentences.stream()
                .map(textElement -> ((Sentence) textElement).getWordsAndSymbols())
                .forEach(allWordsAndSymbols::addAll);
    }

    public List<TextElement> findWordsGivenLengthInInterrogativeSentence() {
        logger.info(FINDING_WORD_GIVEN_LENGTH_LOG_MESSAGE);
        List<TextElement> sentences = getQuestionSentences();
        List<TextElement> words = new ArrayList<>();
        for (TextElement sentence : sentences) {
            words.addAll(((Sentence) sentence).getWordsAndSymbols()
                    .stream()
                    .filter(word -> word instanceof Word)
                    .filter(word -> word.toString().trim().length() == FIXED_WORD_LENGTH)
                    .collect(Collectors.toSet()));
        }
        logger.info(SHOW_RECEIVING_WORDS_LOG_MESSAGE);
        return words;
    }

    private List<TextElement> getQuestionSentences() {
        List<TextElement> sentences = new ArrayList<>();
        for (TextElement sentence : getAllSentences()) {
            if (sentence.toString().trim().endsWith("?")) {
                sentences.add(sentence);
            }
        }
        logger.info(GETTING_QUESTION_SENTENCES_LOG_MESSAGE);
        return sentences;
    }

    public void swapFirstAndLastWordInSentence() {
        logger.info(STARING_SWAP_WORDS_LOG_MESSAGE);
        TextElement current;
        for (TextElement element : getAllSentences()) {
            Sentence sentence = (Sentence) element;
            if (isWordInSentence(sentence)) {
                int firstWordIndex = getIndexOfFirstWordInSentence(sentence);
                int lastWordIndex = getIndexOfLastWordInSentence(sentence);

                current = sentence.getWordsAndSymbols().get(firstWordIndex);

                sentence.getWordsAndSymbols()
                        .set(firstWordIndex, sentence.getWordsAndSymbols().get(lastWordIndex));
                sentence.getWordsAndSymbols()
                        .set(lastWordIndex, current);
            }
        }
        logger.info(SWAP_FIRST_AND_LAST_WORDS_LOG_MESSAGE);
    }

    private int getIndexOfFirstWordInSentence(Sentence sentence) {
        TextElement firstWord = sentence.getAllWords().get(0);
        return sentence.getWordsAndSymbols().indexOf(firstWord);
    }

    private int getIndexOfLastWordInSentence(Sentence sentence) {
        TextElement lastWord = sentence.getAllWords()
                .get(sentence.getNumberOfWordsInSentence() - 1);
        return sentence.getWordsAndSymbols().indexOf(lastWord);
    }

    private boolean isWordInSentence(Sentence sentence) {
        return sentence.getWordsAndSymbols().stream()
                .anyMatch(word -> word instanceof Word);
    }

    public void printWordsInAlphabetSort() {
        logger.info(SORT_WORDS_ALPHABET_LOG_MESSAGE);
        List<String> words = getWords();
        words.sort(String::compareToIgnoreCase);
        for (String word : words) {
            if (Character.isUpperCase(word.charAt(0))) {
                System.out.print("\n" + word + " ");
            } else {
                System.out.print(word + " ");
            }
        }
        System.out.println();
        logger.info(SORT_WORDS_ALPHABET_SUCCESSFUL_LOG_MESSAGE);
    }

    private List<String> getWords() {
        List<String> words = new ArrayList<>();
        for (TextElement word : getAllWordsAndSymbols()) {
            if (word instanceof Word) {
                words.add(word.toString().trim());
            }
        }
        return words;
    }
}
