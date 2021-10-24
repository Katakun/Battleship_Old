package battleship;

public class Field {
    public String[][] field;

    public Field() {
        field = new String[10][10];
        fillField(field);
    }

    boolean checkArray(String[][] arr, int row, int col) {
        return row >=0 && row < arr.length &&
                col >= 0 && col < arr[0].length;
    }

    public boolean isCollision(int[] point) {
        for (int row = point[0] - 1; row <= point[0] + 1; row++) {
            for (int col = point[1] - 1; col <= point[1] + 1 ; col++) {
               if (checkArray(field, row, col)) {
                    if (field[row][col].equals(" O")) {
                       return true;
                   }
                }
            }
        }
        return false;
    }

    public boolean isShipPositionOk(Ship ship) {
        boolean result;
        for (int row = Math.min(ship.row1, ship.row2); row <= Math.max(ship.row1, ship.row2); row++) {
            for (int col = Math.min(ship.col1, ship.col2); col <= Math.max(ship.col1, ship.col2); col++) {
                if (isCollision(new int[]{row, col})) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeShip (Ship ship) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length; col++) {
                if (ship.row1 == ship.row2) {
                    // col1 <=  col <= col2
                    if (Math.min(ship.col1, ship.col2) <= col && col <= Math.max(ship.col1, ship.col2)) {
                        field[ship.row1][col] = " O";
                    }
                } else if (ship.col1 == ship.col2) {
                    // row1 <= row <= row2
                    if (Math.min(ship.row1, ship.row2) <= row && row <= Math.max(ship.row1, ship.row2)) {
                        field[row][ship.col1] = " O";
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
