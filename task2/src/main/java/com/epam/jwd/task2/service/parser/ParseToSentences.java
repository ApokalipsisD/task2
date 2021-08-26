package com.epam.jwd.task2.service.parser;

import com.epam.jwd.task2.entity.impl.Sentence;
import com.epam.jwd.task2.entity.api.TextElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToSentences extends Parser {
    private static final Logger logger = LogManager.getLogger(ParseToSentences.class);

    private static final String SENTENCE_PATTERN = "([0-9]*[.,]?[0-9][.?!\\n])|([^.!?\\n]+[.?!\\n]?)";
    private static final String PARAGRAPH_PARSER_LOG_MESSAGE = "Paragraph has been parsed to sentences.";
    private final List<TextElement> sentences = new ArrayList<>();


    @Override
    public List<TextElement> split(String text) {
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String splitElement = text.substring(matcher.start(), matcher.end()).trim();
            Sentence sentence = new Sentence();
            splitNext(new ParseToWordsAndSymbols(), splitElement)
                    .forEach(sentence::addTextElement);
            sentences.add(sentence);
        }
        logger.info(PARAGRAPH_PARSER_LOG_MESSAGE);
        return sentences;
    }
}
