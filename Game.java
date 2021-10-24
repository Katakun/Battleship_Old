package battleship;

import java.util.Scanner;

public class Game {
    Field field;
    Scanner scanner;

    public Game() {
        field = new Field();
        scanner = new Scanner(System.in);
    }

    public void start() {
        field.printField();

        placeShip("Aircraft Carrier", 5);
        field.printField();

        placeShip("Battleship", 4);
        field.printField();

        placeShip("Submarine", 3);
        field.printField();

        placeShip("Cruiser", 3);
        field.printField();

        placeShip("Destroyer", 2);
        field.printField();
    }



    public void placeShip(String shipType, int shipSize) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", shipType, shipSize);

        while (true) {
            String input = scanner.nextLine();
            Ship ship = new Ship(input);

            boolean isShipSizeOk = ship.getSize() == shipSize;
            boolean isShipLocationOk = ship.isLocationOk();
            boolean isShipPositionOk = field.isShipPositionOk(ship);

            if (isShipSizeOk && isShipLocationOk && isShipPositionOk) {
                field.placeShip(ship);
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
