package com.epam.jwd.task2.service.fileReader;

import com.epam.jwd.task2.service.exception.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    private static final Logger logger = LogManager.getLogger(FileReader.class);

    private static final String READING_FILE = "Reading file ";
    private static final String READ_TEXT_FILE_LOG_MESSAGE = "TextFile has been read successfully.";
    private static final String IOEXCEPTION_LOG_MESSAGE = "IOException has been caught: ";

    public String readInFile(String path) throws MyException {
        String text;
        try {
            logger.info(READING_FILE + path);
            text = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            logger.error(IOEXCEPTION_LOG_MESSAGE, e);
            throw new MyException(e);
        }

        logger.info(READ_TEXT_FILE_LOG_MESSAGE);
        return text;
    }

    public boolean isCorrectPath(String path) {
        File file = new File(path);
        return file.exists();
    }
}


