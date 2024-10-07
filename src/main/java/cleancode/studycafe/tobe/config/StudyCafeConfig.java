package cleancode.studycafe.tobe.config;

import cleancode.studycafe.tobe.io.*;

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
