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
        askUserCoordinates("Aircraft Carrier");
        askUserCoordinates("Battleship");
        askUserCoordinates("Submarine");
        askUserCoordinates("Cruiser");
        askUserCoordinates("Destroyer");
    }

    public void askUserCoordinates(String shipType) {
        switch (shipType) {
            case "Aircraft Carrier":
                System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
                placeHandler(5);
            case "Battleship":
                System.out.println("Enter the coordinates of the Battleship (4 cells):");
                placeHandler(4);
            case "Submarine":
                System.out.println("Enter the coordinates of the Submarine (3 cells):");
                placeHandler(3);
            case "Cruiser":
                System.out.println("Enter the coordinates of the Cruiser (3 cells):");
                placeHandler(3);
            case "Destroyer":
                System.out.println("Enter the coordinates of the Destroyer (2 cells):");
                placeHandler(2);
            }
    }

    public void placeHandler(int shipSize) {
        String input = scanner.nextLine();
        while (true) {
            if (placeShip(input, 5)) {
                break;
            }
        }
    }

    public boolean placeShip(String input, int shipSize) {
        Ship ship = new Ship(input);
        // checkShipSize
        boolean isShipSizeOk = ship.size() == shipSize;
        boolean isShipLocationOk = ship.isLocationOk();
        boolean isShipPositionOk = field.isShipPositionOk(ship);

        if (isShipSizeOk && isShipLocationOk && isShipPositionOk) {
            field.placeShip(ship);
            return true;
        } else if (!isShipSizeOk) {
            System.out.println("Error! Wrong length of the Submarine! Try again:");
            return false;
        } else if (!isShipLocationOk) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        } else { // !isShipPositionOk
            System.out.println("Error! You placed it too close to another one. Try again:");
            return false;
        }
    }
}
