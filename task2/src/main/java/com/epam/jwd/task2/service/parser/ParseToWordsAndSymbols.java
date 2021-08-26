package com.epam.jwd.task2.service.parser;

import com.epam.jwd.task2.entity.api.TextElement;
import com.epam.jwd.task2.entity.impl.Digit;
import com.epam.jwd.task2.entity.impl.Punctuation;
import com.epam.jwd.task2.entity.impl.Space;
import com.epam.jwd.task2.entity.impl.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToWordsAndSymbols extends Parser {
    private static final Logger logger = LogManager.getLogger(ParseToWordsAndSymbols.class);

    private static final String SPLIT_ELEMENT_PATTERN = "((?<=[\\s\\p{Punct}])|(?=[\\s\\p{Punct}]))";
    private static final String DIGIT_PATTERN = "[0-9][.,]?[0-9]?";
    private static final String PUNCTUATION_PATTERN = "\\p{Punct}";
    private static final String SPACE_PATTERN = "\\p{Blank}";
    private static final String SENTENCE_PARSER_LOG_MESSAGE = "Sentence has benn parsed to words and symbols.";
    private final List<TextElement> wordsAndSymbols = new ArrayList<>();

    @Override
    public List<TextElement> split(String text) {
        List<String> listText = List.of(text.split(SPLIT_ELEMENT_PATTERN));
        for (String splitElement : listText) {
            Pattern digit = Pattern.compile(DIGIT_PATTERN, Pattern.MULTILINE);
            Pattern punctuation = Pattern.compile(PUNCTUATION_PATTERN, Pattern.MULTILINE);
            Pattern space = Pattern.compile(SPACE_PATTERN, Pattern.MULTILINE);

            Matcher digitMatcher = digit.matcher(splitElement);
            Matcher punctuationMatcher = punctuation.matcher(splitElement);
            Matcher spaceMatcher = space.matcher(splitElement);

            if (digitMatcher.find()) {
                wordsAndSymbols.add(new Digit(splitElement));
            } else if (punctuationMatcher.find()) {
                wordsAndSymbols.add(new Punctuation(splitElement));
            } else if (spaceMatcher.find()) {
                wordsAndSymbols.add(new Space(splitElement));
            } else {
                wordsAndSymbols.add(new Word(splitElement));
            }
        }
        logger.info(SENTENCE_PARSER_LOG_MESSAGE);
        return wordsAndSymbols;
    }
}
