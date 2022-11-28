package battleship;

import java.util.Random;

public class Ocean {
    /**
     * Used to quickly determine which ship is in any given location
     */
    private Ship[][] ships = new Ship[10][10];

    /**
     * The total number of shots fired by the user
     */
    private int shotsFired;

    /**
     * The number of times a shot hit a ship. If the user shoots the same part of a ship more than once,
     * every hit is counted, even though additional “hits” don’t do the user any good.
     */
    private int hitCount;

    /**
     * The number of ships sunk (10 ships in all)
     */
    private int shipsSunk;

    /**
     * Creates an ”empty” ocean (and fills the ships array with EmptySea objects).
     * Also initializes any game variables, such as how many shots have been fired.
     */
    public Ocean() {
        //Creates an empty ocean and fills the ships array with EmptySea objects.
        this.fillEmptyOceanShips();
        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;
    }

    private void fillEmptyOceanShips() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Ship newShip = new EmptySea();
                newShip.setBowRow(i);
                newShip.setBowColumn(j);
                newShip.setHorizontal(true);
                this.ships[i][j] = newShip;
            }
        }
    }

    public Ship[][] getShipArray() {
        return this.ships;
    }

    public int getShotsFired() {
        return this.shotsFired;
    }

    public int getHitCount() {
        return this.hitCount;
    }

    public int getShipsSunk() {
        return this.shipsSunk;
    }

    public void setShipsSunk() {
        this.shipsSunk = this.getShipsSunk() + 1;
    }

    /**
     * Place all ten ships randomly on the (initially empty) ocean.
     */
    public void placeAllShipsRandomly() {
        Random random = new Random();
        int row;
        int column;
        int trueOrFalse;
        //Place one battleship.
        Ship battleshipToPlace = new Battleship();
        row = random.nextInt(10);
        column = random.nextInt(10);
        trueOrFalse = random.nextInt(2);
        boolean battleShipOkToPlace = battleshipToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
        //Util the battleship is OK to place here.
        while (!battleShipOkToPlace) {
            row = random.nextInt(10);
            column = random.nextInt(10);
            trueOrFalse = random.nextInt(2);
            battleShipOkToPlace = battleshipToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
        }
        battleshipToPlace.placeShipAt(row, column, trueOrFalse == 1, this);
        //Place two cruisers.
        for (int i = 0; i < 2; i++) {
            Ship cruiserToPlace = new Cruiser();
            row = random.nextInt(10);
            column = random.nextInt(10);
            trueOrFalse = random.nextInt(2);
            boolean cruiserOkToPlace = cruiserToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
            //Util the cruiser is OK to place here.
            while (!cruiserOkToPlace) {
                row = random.nextInt(10);
                column = random.nextInt(10);
                trueOrFalse = random.nextInt(2);
                cruiserOkToPlace = cruiserToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
            }
            cruiserToPlace.placeShipAt(row, column, trueOrFalse == 1, this);
        }
        //Place three destroyers.
        for (int j = 0; j < 3; j++) {
            Ship destroyerToPlace = new Destroyer();
            row = random.nextInt(10);
            column = random.nextInt(10);
            trueOrFalse = random.nextInt(2);
            boolean destroyerOkToPlace = destroyerToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
            //Util the destroyer is OK to place here.
            while (!destroyerOkToPlace) {
                row = random.nextInt(10);
                column = random.nextInt(10);
                trueOrFalse = random.nextInt(2);
                destroyerOkToPlace = destroyerToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
            }
            destroyerToPlace.placeShipAt(row, column, trueOrFalse == 1, this);
        }
        //Place four submarines.
        for (int k = 0; k < 4; k++) {
            Ship submarineToPlace = new Submarine();
            row = random.nextInt(10);
            column = random.nextInt(10);
            trueOrFalse = random.nextInt(2);
            boolean submarineOkToPlace = submarineToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
            //Util the submarine is OK to place here.
            while (!submarineOkToPlace) {
                row = random.nextInt(10);
                column = random.nextInt(10);
                trueOrFalse = random.nextInt(2);
                submarineOkToPlace = submarineToPlace.okToPlaceShipAt(row, column, trueOrFalse == 1, this);
            }
            submarineToPlace.placeShipAt(row, column, trueOrFalse == 1, this);
        }
    }

    /**
     *  Returns true if the given location contains a ship, false if it does not.
     * @param row
     * @param column
     * @return
     */
    public boolean isOccupied(int row, int column) {
        if (!this.getShipArray()[row][column].getShipType().equals("empty")) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not.
     * In addition, this method updates the number of shots that have been fired, and the number of hits.
     * @param row
     * @param column
     * @return
     */
    public boolean shootAt(int row, int column) {
        //Update the number of shots that have been fired.
        this.shotsFired = this.getShotsFired() + 1;
        Ship ship = this.getShipArray()[row][column];
        if (this.isOccupied(row, column)) {
            if (!ship.isSunk()) {
                if (ship.isHorizontal()) {
                    ship.getHit()[ship.getBowColumn() - column] = true;
                } else {
                    ship.getHit()[ship.getBowRow() - row] = true;
                }
                //Update the number of hits that hit real afloat ships.
                this.hitCount = this.getHitCount() + 1;
                return true;
            }
        }
        ship.getHit()[0] = true;
        return false;
    }

    /**
     * Prints the Ocean. To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top.
     * Numbers should be 0 to 9, not 1 to 10.
     * The top left corner square should be 0, 0.
     * ‘x’: Use ‘x’ to indicate a location that you have fired upon and hit a (real) ship.
     * ‘-’: Use ‘-’ to indicate a location that you have fired upon and found nothing there.
     * ‘s’: Use ‘s’ to indicate a location containing a sunken ship.
     *  ‘.’: and use ‘.’ (a period) to indicate a location that you have never fired upon.
     */
    public void print() {
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" ");

        String eachShipToString = "";
        for (int j = 0; j < 10; j++) {
            String rowNumber = String.valueOf(j);
            System.out.print(rowNumber);
            for (int k = 0; k < 10; k++) {
                Ship eachShip = this.getShipArray()[j][k];
                if (eachShip.getShipType().equals("empty")) {
                    if (!eachShip.getHit()[0]) {
                        System.out.print(" " + ".");
                    } else {
                        eachShipToString = eachShip.toString();
                        System.out.print(" " + eachShipToString);
                    }
                } else {
                    if (eachShip.isHorizontal()) {
                        if (eachShip.getHit()[eachShip.getBowColumn() - k]) {
                            eachShipToString = eachShip.toString();
                            System.out.print(" " + eachShipToString);
                        } else {
                            System.out.print(" " + ".");
                        }
                    } else {
                        if (eachShip.getHit()[eachShip.getBowRow() - j]) {
                            eachShipToString = eachShip.toString();
                            System.out.print(" " + eachShipToString);
                        } else {
                            System.out.print(" " + ".");
                        }
                    }
                }
            }
            System.out.println(" ");
        }
    }

    public void printWithShips() {
        System.out.print(" ");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" ");

        for (int j = 0; j < 10; j++) {
            String rowNumber = String.valueOf(j);
            System.out.print(rowNumber);
            for (int k = 0; k < 10; k++) {
                Ship eachShip = this.getShipArray()[j][k];
                if (eachShip.getShipType().equals("empty")) {
                    System.out.print(" " + " ");
                }
                if (eachShip.getShipType().equals("battleship")) {
                    System.out.print(" " + "b");
                }
                if (eachShip.getShipType().equals("cruiser")) {
                    System.out.print(" " + "c");
                }
                if (eachShip.getShipType().equals("destroyer")) {
                    System.out.print(" " + "d");
                }
                if (eachShip.getShipType().equals("submarine")) {
                    System.out.print(" " + "s");
                }
            }
            System.out.println(" ");
        }

    }
}

