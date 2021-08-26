package com.epam.jwd.task2.main;

import com.epam.jwd.task2.service.ApplicationService;
import com.epam.jwd.task2.service.exception.MyException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String START_WORK_LOG_MESSAGE = "Launching the app.";
    private static final String END_OF_WORK_LOG_MESSAGE = "Work done successfully";

    public static void main(String[] args) throws MyException {
        logger.info(START_WORK_LOG_MESSAGE);
        ApplicationService.start();
        logger.info(END_OF_WORK_LOG_MESSAGE);
    }
}
