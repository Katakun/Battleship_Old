package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    Field myField;
    Scanner scanner;

    public Game() {
        myField = new Field();
        scanner = new Scanner(System.in);
    }

    public void start() {
        myField.printField();

        placeShip("Aircraft Carrier", 5);
        myField.printField();

        placeShip("Battleship", 4);
        myField.printField();

        placeShip("Submarine", 3);
        myField.printField();

        placeShip("Cruiser", 3);
        myField.printField();

        placeShip("Destroyer", 2);
        myField.printField();

        System.out.println("\nThe game starts!\n");
        myField.printField();

        int[] shotPoint = shotPoint();
        shotIndicate(shotPoint);

    }


    public void shotIndicate(int[] shotPoint) {
        int row = shotPoint[0];
        int col = shotPoint[1];
        if (myField.field[row][col].equals(" O")) {
            myField.field[row][col] = " X";
            myField.printField();
            System.out.println("You hit a ship!");
        } else {
            myField.field[row][col] = " M";
            myField.printField();
            System.out.println("You missed!");
        }
    }

    public int[] shotPoint() {
        System.out.println("Take a shot!\n");

        while (true) {
            String input = scanner.nextLine();
            int[] shotPoint = Ship.stringToInt(input);
            if (shotPoint[0] < 0 || shotPoint[0] > 9 || shotPoint[1] < 0 || shotPoint[1] > 9 ) {
                System.out.println("\nError! You entered the wrong coordinates! Try again:\n");
            } else {
                return shotPoint;
            }
        }
    }


    public void placeShip(String shipType, int shipSize) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", shipType, shipSize);

        while (true) {
            String input = scanner.nextLine();
            Ship ship = new Ship(input);

            boolean isShipSizeOk = ship.getSize() == shipSize;
            boolean isShipLocationOk = ship.isLocationOk();
            boolean isShipPositionOk = myField.isShipPositionOk(ship);

            if (isShipSizeOk && isShipLocationOk && isShipPositionOk) {
                myField.placeShip(ship);
                break;
            } else if (!isShipSizeOk) {
                System.out.printf("\nError! Wrong length of the %s! Try again:\n", shipType);
            } else if (!isShipLocationOk) {
                System.out.println("\nError! Wrong ship location! Try again:");
            } else { // !isShipPositionOk
                System.out.println("\nError! You placed it too close to another one. Try again:");
            }
        }

    }
}
