package battleship;

public abstract class Ship {
    private int bowRow;

    private int bowColumn;

    private int length;

    private boolean horizontal;

    private boolean[] hit;

    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[length];
    }

    public int getLength() {
        return this.length;
    }

    public int getBowRow() {
        return this.bowRow;
    }

    public int getBowColumn() {
        return this.bowColumn;
    }

    public boolean[] getHit() {
        return this.hit;
    }

    public boolean isHorizontal() {
        return this.horizontal;
    }

    public void setBowRow(int row) {
        this.bowRow = row;
    }

    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public abstract String getShipType();

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            if (row < 0 || row >= 10) {
                return false;
            }
            if (column < 0 || column >= 10 || column - this.getLength() + 1 < 0) {
                return false;
            }
            for (int i = row - 1; i < row + 2; i++) {
                if (i < 0 || i >= 10) {
                    continue;
                }
                for (int j = column + 1; j > column - this.getLength() - 1; j--) {
                    if (j < 0 || j >= 10) {
                        continue;
                    }
                    if (ocean.isOccupied(i, j)) {
                        return false;
                    }
                }
            }
        } else {
            if (row < 0 || row >= 10 || row - this.getLength() + 1 < 0) {
                return false;
            }
            if (column < 0 || column >= 10) {
                return false;
            }
            for (int m = row + 1; m > row - this.getLength() - 1; m--) {
                if (m < 0 || m >= 10) {
                    continue;
                }
                for (int n = column - 1; n < column + 2; n++) {
                    if (n < 0 || n >= 10) {
                        continue;
                    }
                    if (ocean.isOccupied(m, n)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.setBowRow(row);
        this.setBowColumn(column);
        this.setHorizontal(horizontal);
        int specifiedBowRow = this.getBowRow();
        int specifiedBowColumn = this.getBowColumn();
        if (horizontal) {
            for (int i = 0; i < this.getLength(); i++) {
                ocean.getShipArray()[specifiedBowRow][specifiedBowColumn - i] = this;
            }
        } else {
            for (int j = 0; j < this.getLength(); j++) {
                ocean.getShipArray()[specifiedBowRow - j][specifiedBowColumn] = this;
            }
        }
    }

    public boolean shootAt(int row, int column) {
        if (this.isSunk()) {
            return false;
        }
        if (this.isHorizontal()) {
            if (this.getBowRow() != row) {
                return false;
            }
            if (column > this.getBowColumn() || column < this.getBowColumn() - this.getLength() + 1) {
                return false;
            }
            this.getHit()[this.getBowColumn() - column] = true;
        } else {
            if (this.getBowColumn() != column) {
                return false;
            }
            if (row > this.getBowRow() || row < this.getBowRow() - this.getLength() + 1) {
                return false;
            }
            this.getHit()[this.getBowRow() - row] = true;
        }
        return true;
    }

    public boolean isSunk() {
        for (int i = 0; i < this.getLength(); i++) {
            if (this.getHit()[i] == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (this.isSunk()) {
            return "s";
        }
        return "x";
    }
}
