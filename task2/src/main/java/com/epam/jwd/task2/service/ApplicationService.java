package com.epam.jwd.task2.service;

import com.epam.jwd.task2.service.editor.TextEditor;
import com.epam.jwd.task2.service.exception.MyException;
import com.epam.jwd.task2.service.operations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ApplicationService {
    private static final Logger logger = LogManager.getLogger(ApplicationService.class);
    private static final String MENU = """
            Choose operation:
            1. Print text
            2. Print words given length from interrogative sentences
            3. Swap first and last word in sentence
            4. Print words in alphabet sort considering upper case
            5. Return to original text
            6. Exit
            """;

    public static void start() throws MyException {
        Scanner scanner = new Scanner(System.in);
        TextEditor textEditor = new TextEditor();
        textEditor.readText();
        textEditor.saveText();
        Operation operation = new Operation(textEditor.getFullText());
        operation.uniteAllElements();
        logger.info(MENU);
        show(MENU);

        int choice = 1;
        while (choice != 6) {
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    operation.printText();
                }
                case 2 -> {
                    operation.findWordsGivenLengthInInterrogativeSentence()
                            .forEach(word -> System.out.print(word + " "));
                    System.out.println();
                }
                case 3 -> {
                    operation.swapFirstAndLastWordInSentence();
                }
                case 4 -> {
                    operation.printWordsInAlphabetSort();
                }
                case 5 -> {
                    operation.setNewText(textEditor.getOriginalText());
                    operation.uniteAllElements();
                }
            }
        }
    }

    public static void show(String text) {
        System.out.println(text);
    }
}
