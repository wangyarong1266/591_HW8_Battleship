package battleship;

import java.util.Scanner;

public class BattleshipGame {
    /**
     * Get the row number and the column number of the shoot from the user
     * Check whether the row number and the column number of the shoot are valid
     * @param getShotFromUser
     * @return
     */
    public int[] getQualifiedShotFromUser(Scanner getShotFromUser) {
        int[] qualifiedTargetRowAndColumn = new int[2];
        int targetRowNumber = 0;
        boolean targetRowIsValid = false;
        while (!targetRowIsValid) {
            System.out.println("Please enter the target row (any integer from 0 to 9): ");
            //Remove possible trailing and leading whitespaces of target row.
            String targetRow = getShotFromUser.nextLine().trim();
            //Check whether the user's input can be converted to an integer.
            try {
                targetRowNumber = Integer.parseInt(targetRow);
                //If the user's input can be converted to an integer,
                //then check whether the integer is in the range [0, 9].
                if (targetRowNumber >= 0 && targetRowNumber <= 9) {
                    targetRowIsValid = true;
                } else {
                    System.out.println("The target row can not be out of the range [0, 9].");
                }
            } catch (NumberFormatException e) {
                System.out.println("The target row is invalid. Please enter any integer from 0 to 9!");
            }

        }
        int targetColumnNumber = 0;
        boolean targetColumnIsValid = false;
        while (!targetColumnIsValid) {
            System.out.println("Please enter the target column (any integer from 0 to 9): ");
            //Remove possible trailing and leading whitespaces of target column.
            String targetColumn = getShotFromUser.nextLine().trim();
            try {
                targetColumnNumber = Integer.parseInt(targetColumn);
                if (targetColumnNumber >= 0 && targetColumnNumber <= 9) {
                    targetColumnIsValid = true;
                } else {
                    System.out.println("The target column can not be out of the range [0, 9].");
                }
            } catch (NumberFormatException e) {
                System.out.println("The target column is invalid. Please enter any integer from 0 to 9!");
            }
        }
        qualifiedTargetRowAndColumn[0] = targetRowNumber;
        qualifiedTargetRowAndColumn[1] = targetColumnNumber;
        return qualifiedTargetRowAndColumn;
    }

    public static void main(String[] args) {
        BattleshipGame newBattleshipGame = new BattleshipGame();
        Ocean newOcean = new Ocean();
        newOcean.placeAllShipsRandomly();
//        newOcean.printWithShips();
        newOcean.print();
        //Int array to hold target row and target column.
        int[] targetRowAndColumn;
        int targetRow;
        int targetColumn;
        Ship targetShip;
        Scanner getShotFromUser = new Scanner(System.in);
        //Util all of 10 ships are sunk.
        while (newOcean.getShipsSunk() < 10) {
            targetRowAndColumn = newBattleshipGame.getQualifiedShotFromUser(getShotFromUser);
            targetRow = targetRowAndColumn[0];
            targetColumn = targetRowAndColumn[1];
            targetShip = newOcean.getShipArray()[targetRow][targetColumn];
            if (newOcean.shootAt(targetRow, targetColumn)) {
                System.out.println("You hits the part of a ship!");
                if (targetShip.shootAt(targetRow, targetColumn)) {
                    if (targetShip.isSunk()) {
                        newOcean.setShipsSunk();
                        System.out.println("You just sank a ship - " + targetShip.getShipType());
                    }
                }
            } else {
                if (targetShip.getShipType().equals("empty")) {
                    System.out.println("Oops! You miss the part of a ship!");
                }
            }
            newOcean.print();
        }
        System.out.println("You sunk all of ships! The game is over!");
        System.out.println("You got " + newOcean.getShotsFired() + " shots fired.");
        System.out.println(newOcean.getHitCount() + " hits are required to sink all of ships.");
    }
}
