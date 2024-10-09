package cleancode.studycafe.assignment;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafeConfig studyCafeConfig = new StudyCafeConfig();
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(studyCafeConfig);

        studyCafePassMachine.initializable();
        studyCafePassMachine.run();
    }

}
