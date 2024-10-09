package cleancode.studycafe.assignment;

import cleancode.studycafe.assignment.io.*;

public class StudyCafeConfig {

    private static InputHandler inputHandler;
    private static OutputHandler outputHandler;
    private static FileHandler fileHandler;

    StudyCafeConfig() {
        inputHandler = new ConsoleInputHandler();
        outputHandler = new ConsoleOutputHandler();
        fileHandler = new StudyCafeFileHandler();
    }

    public InputHandler getInputHandler() {
        return inputHandler;
    }

    public OutputHandler getOutputHandler() {
        return outputHandler;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
