package com.epam.jwd.task2.service.editor;

import com.epam.jwd.task2.entity.api.TextElement;
import com.epam.jwd.task2.service.exception.MyException;
import com.epam.jwd.task2.service.fileReader.FileReader;
import com.epam.jwd.task2.service.parser.ParseToParagraphAndCodeBlock;
import com.epam.jwd.task2.service.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TextEditor {
    private static final Logger logger = LogManager.getLogger(TextEditor.class);

    private static final String PATH = "text.txt";
    private static final String SAVE_PATH = "saveText.dat";
    private static final String PATH_INCORRECT = "Path incorrect";
    private static final String PATH_IS_AVAILABLE_LOG_MESSAGE = "Path is available.";
    private static final String FILE_READER_EXCEPTION_LOG_MESSAGE = "File does not exist.";
    private static final String TEXT_HAS_BEEN_PARSED = "All text has been parsed.";
    private static final String SAVE_TEXT_LOG_MESSAGE = "Saving text in file.";
    private static final String READ_SAVE_TEXT_LOG_MESSAGE = "Receiving the saved file.";
    private static final String IOEXCEPTION_LOG_MESSAGE = "IOException has been caught: ";
    private static final String IOEXCEPTION_AND_CLASS_FOUND_LOG_MESSAGE = "IOException or ClassNotFoundException has been caught: ";
    private List<TextElement> fullText = new ArrayList<>();

    public void readText() throws MyException {
        FileReader fileReader = new FileReader();
        Parser parser = new ParseToParagraphAndCodeBlock();
        if (fileReader.isCorrectPath(PATH)) {
            logger.info(PATH_IS_AVAILABLE_LOG_MESSAGE);
            String text = fileReader.readInFile(PATH);
            fullText = parser.split(text);
            logger.info(TEXT_HAS_BEEN_PARSED);
        } else {
            logger.error(FILE_READER_EXCEPTION_LOG_MESSAGE);
            throw new MyException(PATH_INCORRECT);
        }
    }

    public List<TextElement> getFullText() {
        return fullText;
    }

    public void saveText() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
            logger.info(SAVE_TEXT_LOG_MESSAGE);
            output.writeObject(fullText);
        } catch (IOException e) {
            logger.error(IOEXCEPTION_LOG_MESSAGE, e);
            e.printStackTrace();
        }
    }

    public List<TextElement> checkText() {
        List<TextElement> text = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
            logger.info(READ_SAVE_TEXT_LOG_MESSAGE);
            text = (List<TextElement>) input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error(IOEXCEPTION_AND_CLASS_FOUND_LOG_MESSAGE, e);
            e.printStackTrace();
        }
        return text;
    }
    public List<TextElement> getOriginalText(){
        return checkText();
    }

    public void printOriginalText(){
        checkText().forEach(TextElement::print);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextEditor that = (TextEditor) o;

        return fullText != null ? fullText.equals(that.fullText) : that.fullText == null;
    }

    @Override
    public int hashCode() {
        return fullText != null ? fullText.hashCode() : 0;
    }

    @Override
    public String toString() {
        if (fullText != null) {
            StringBuilder sb = new StringBuilder();
            for (TextElement textElement : fullText) {
                sb.append(textElement.toString()).append("\n");
            }
            return sb.toString();
        } else {
            return "Текст отсутствует";
        }
    }
}
