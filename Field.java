package battleship;

public class Field {
    private static String[][] field;

    public Field() {
        field = new String[10][10];
        fillField(field);
    }

    public boolean isShipPositionOk(Ship ship) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length ; col++) {
                if (field[row][col].equals(" O")) {
                    if (ship.row1 == ship.row2) {
                        if (Math.abs(ship.row1 - 1 - row) <= 1) {
                            return false;
                        }
                    } else {
                        if (Math.abs(ship.col1 - 1 - col) <= 1) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void placeShip (Ship ship) {
        // from 1 to 10
        for (int row = 1; row <= field.length; row++) {
            // from 1 to 10
            for (int col = 1; col <= field.length; col++) {
                // A = A
                if (ship.row1 == ship.row2) {
                    // col1 <=  col <= col2
                    if (Math.min(ship.col1, ship.col2) <= col && col <= Math.max(ship.col1, ship.col2)) {
                        // 1 => 0 = " 0"
                        field[ship.row1 - 1][col - 1] = " O"; //
                    }
                } else if (ship.col1 == ship.col2) {
                    // row1 <= row <= row2
                    if (Math.min(ship.row1, ship.row2) <= row && row <= Math.max(ship.row1, ship.row2)) {
                        field[row - 1][ship.col1 - 1] = " O";
                    }
                }
            }
        }
    }

    private void fillField (String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = " ~";
            }
        }
    }

    public void printField() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < field.length; i++) {
            System.out.print((char) (65 + i));
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
