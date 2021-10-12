package battleship;

public class Ship {
    int[][] coordinates;
    int row1;
    int col1;
    int row2;
    int col2;

    public Ship(String input) {
        coordinates = convertInput(input);
        row1 = coordinates[0][0];
        col1 = coordinates[0][1];
        row2 = coordinates[1][0];
        col2 = coordinates[1][1];
    }

    public boolean isLocationOk() {
        return row1 == row2 || col1 == col2;
    }

    public int size() {
        int size = 0;
        if (row1 == row2) {
            size =  Math.max(col1, col2) - Math.min(col1, col2) + 1;
        } else if (col1 == col2) {
            size = Math.max(row1, row2) - Math.min(row1, row2) + 1;
        }
        return size;
    }

    private int[][] convertInput(String input) {
        // A1 D1 -> [1, 1][4, 1]
        String[] stringArr = input.split(" ");
        int[] intArr1 = stringToInt(stringArr[0]);
        int[] intArr2 = stringToInt(stringArr[1]);
        int[][] ship = {intArr1, intArr2};
        return ship;
    }

    private int[] stringToInt(String input) {
        // A1 -> [1, 1]
        char ch = input.charAt(0);
        int row = charToInt(ch);
        int col = Integer.parseInt(input.substring(1));
        int[] result = new int[2];
        result[0] = row;
        result[1] = col;
        return result;
    }

    private int charToInt(char ch) {
        // A -> 1
        return (int) ch - 64;
    }
}