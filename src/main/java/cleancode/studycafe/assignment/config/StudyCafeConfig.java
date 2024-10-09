package cleancode.studycafe.assignment.config;

import cleancode.studycafe.assignment.io.*;

public class StudyCafeConfig {

    public InputHandler getInputHandler() {
        return new ConsoleInputHandler();
    }

    public OutputHandler getOutputHandler() {
        return new ConsoleOutputHandler();
    }

    public FileHandler getFileHandler() {
        return new StudyCafeFileHandler();
    }
}
