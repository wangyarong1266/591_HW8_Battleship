package battleship;

public abstract class Ship {
    /**
     * The row that contains the bow (front part of the ship)
     */
    private int bowRow;

    /**
     * The column that contains the bow (front part of the ship)
     */
    private int bowColumn;

    /**
     * The length of the ship
     */
    private int length;

    /**
     * A boolean that represents whether the ship is going to be placed horizontally or vertically
     */
    private boolean horizontal;

    /**
     * An array of booleans that indicate whether that part of the ship has been hit or not
     */
    private boolean[] hit;

    /**
     * Constructor sets the length property of the particular ship and initializes the hit array based on that length
     * @param length
     */
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

    /**
     * Returns the type of ship as a String.
     * Every specific type of Ship has to override and implement this method and return the corresponding ship type.
     */
    public abstract String getShipType();

    /**
     * Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location.
     * The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
     * and it must not ”stick out” beyond the array.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return
     */
    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (horizontal) {
            // Check if the row number is not out of the bound
            if (row < 0 || row >= 10) {
                return false;
            }
            // Check if each column number of the ship is not out of the bound
            if (column < 0 || column >= 10 || column - this.getLength() + 1 < 0) {
                return false;
            }
            // Check whether the ship is surrounded by any other ship
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
            // Check if each row number of the ship is not out of the bound
            if (row < 0 || row >= 10 || row - this.getLength() + 1 < 0) {
                return false;
            }
            // Check if the column number is not out of the bound
            if (column < 0 || column >= 10) {
                return false;
            }
            // Check whether the ship is surrounded by any other ship
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

    /**
     * “Puts” the ship in the ocean. This involves giving values to the bowRow, bowColumn, and horizontal instance variables in the ship,
     * and it also involves putting a reference to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
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

    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk,
     * mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return true;
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column) {
        if (this.isSunk()) {
            return false;
        }
        if (this.isHorizontal()) {
            if (this.getBowRow() != row) {
                return false;
            }
            // Check whether the part of the ship occupies the given shoot
            if (column > this.getBowColumn() || column < this.getBowColumn() - this.getLength() + 1) {
                return false;
            }
            // Mark that part of the ship as "hit"
            this.getHit()[this.getBowColumn() - column] = true;
        } else {
            if (this.getBowColumn() != column) {
                return false;
            }
            // Check whether the part of the ship occupies the given shoot
            if (row > this.getBowRow() || row < this.getBowRow() - this.getLength() + 1) {
                return false;
            }
            // Mark that part of the ship as "hit"
            this.getHit()[this.getBowRow() - row] = true;
        }
        return true;
    }

    public boolean isSunk() {
        /**
         * Return true if every part of the ship has been hit, false otherwise
         */
        for (int i = 0; i < this.getLength(); i++) {
            if (this.getHit()[i] == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a single-character String to use in the Ocean’s print method.
     * This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk.
     * This method can be used to print out locations in the ocean that have been shot at;
     * it should not be used to print locations that have not been shot at.
     * @return
     */
    @Override
    public String toString() {
        if (this.isSunk()) {
            return "s";
        }
        return "x";
    }
}
