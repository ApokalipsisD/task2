package com.epam.jwd.task2.service.parser;

import com.epam.jwd.task2.entity.impl.CodeBlock;
import com.epam.jwd.task2.entity.impl.Paragraph;
import com.epam.jwd.task2.entity.api.TextElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToParagraphAndCodeBlock extends Parser {
    private static final Logger logger = LogManager.getLogger(ParseToParagraphAndCodeBlock.class);

    private static final String PARAGRAPH_AND_CODE_BLOCK_PATTERN = "((^.+?\\{([\\s\\S]+?)^\\})|^.+)";
    private static final String CODE_BLOCK_PATTERN = "(^.+?\\{([\\s\\S]+?)^\\})";
    private static final String TEXT_PARSER_LOG_MESSAGE = "Text has been parsed to paragraphs and codeBlocks.";
    private final List<TextElement> paragraphsAndCodeBlock = new ArrayList<>();

    @Override
    public List<TextElement> split(String text) {
        Pattern pattern = Pattern.compile(PARAGRAPH_AND_CODE_BLOCK_PATTERN, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String splitElement = text.substring(matcher.start(), matcher.end()).trim();
            Pattern codePattern = Pattern.compile(CODE_BLOCK_PATTERN, Pattern.MULTILINE);
            Matcher codeMatcher = codePattern.matcher(splitElement);
            if (codeMatcher.find()) {
                paragraphsAndCodeBlock.add(new CodeBlock(splitElement));
            } else {
                Paragraph paragraph = new Paragraph();
                splitNext(new ParseToSentences(), splitElement)
                        .forEach(paragraph::addTextElement);
                paragraphsAndCodeBlock.add(paragraph);
            }
        }
        logger.info(TEXT_PARSER_LOG_MESSAGE);
        return paragraphsAndCodeBlock;
    }
}
