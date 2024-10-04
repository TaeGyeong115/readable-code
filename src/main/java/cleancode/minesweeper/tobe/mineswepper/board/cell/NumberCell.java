package cleancode.minesweeper.tobe.mineswepper.board.cell;

public class NumberCell implements Cell {

    private final int nearbyLandMineCount;
    private final CellState cellState = CellState.initalize();

    public NumberCell(int nearbyLandMineCount) {
        this.nearbyLandMineCount = nearbyLandMineCount;
    }

    @Override
    public CellSnapshot getSnapshot() {
        if(cellState.isOpened()) {
            return CellSnapshot.ofNumber(nearbyLandMineCount);
        }
        if(cellState.isFlagged()) {
            return CellSnapshot.ofFlag();
        }
        return CellSnapshot.ofUnchecked();
    }

    @Override
    public void flag() {
        cellState.flag();
    }

    @Override
    public void open() {
        cellState.open();
    }

    @Override
    public boolean isChecked() {
        return cellState.isOpened();
    }

    @Override
    public boolean isOpened() {
        return cellState.isOpened();
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

}
