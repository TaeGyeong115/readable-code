package cleancode.minesweeper.tobe;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new VeryBeginner();

        Minesweeper minesweeper = new Minesweeper(gameLevel);
        minesweeper.initalizable();
        minesweeper.run();
    }

}
