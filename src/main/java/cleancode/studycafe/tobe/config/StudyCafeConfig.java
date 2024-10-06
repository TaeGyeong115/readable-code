package cleancode.studycafe.tobe.config;

import cleancode.studycafe.tobe.io.ConsoleInputHandler;
import cleancode.studycafe.tobe.io.ConsoleOutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;

public class StudyCafeConfig {

    private final StudyCafeFileHandler handler;
    private final ConsoleInputHandler consoleInputHandler;
    private final ConsoleOutputHandler consoleOutputHandler;

    public StudyCafeConfig(StudyCafeFileHandler handler, ConsoleInputHandler consoleInputHandler, ConsoleOutputHandler consoleOutputHandler) {
        this.handler = handler;
        this.consoleInputHandler = consoleInputHandler;
        this.consoleOutputHandler = consoleOutputHandler;
    }

    public ConsoleInputHandler getInputHandler() {
        return consoleInputHandler;
    }

    public ConsoleOutputHandler getOutputHandler() {
        return consoleOutputHandler;
    }

    public StudyCafeFileHandler getHandler() {
        return handler;
    }
}
