package cleancode.minesweeper.tobe.mineswepper.board.cell;

public class CellState {

    private boolean isFlagged;
    private boolean isOpened;

    private CellState(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellState initalize() {
        return new CellState(false, false);
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        this.isOpened = true;
    }

    public void flag() {
        this.isFlagged = true;
    }

    public boolean isChecked() {
        return isFlagged || isOpened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
}
