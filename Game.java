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
        field.printField();
        askUserCoordinates("Battleship");
        field.printField();
        askUserCoordinates("Submarine");
        field.printField();
        askUserCoordinates("Cruiser");
        field.printField();
        askUserCoordinates("Destroyer");
        field.printField();
    }

    public void askUserCoordinates(String shipType) {
        switch (shipType) {
            case "Aircraft Carrier":
                System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
                placeShip("Aircraft Carrier", 5);
                break;
            case "Battleship":
                System.out.println("Enter the coordinates of the Battleship (4 cells):");
                placeShip("Battleship", 4);
                break;
            case "Submarine":
                System.out.println("Enter the coordinates of the Submarine (3 cells):");
                placeShip("Submarine", 3);
                break;
            case "Cruiser":
                System.out.println("Enter the coordinates of the Cruiser (3 cells):");
                placeShip("Cruiser", 3);
                break;
            case "Destroyer":
                System.out.println("Enter the coordinates of the Destroyer (2 cells):");
                placeShip("Destroyer", 2);
                break;
            }
    }

//    public void placeHandler(int shipSize) {
//        String input = scanner.nextLine();
//        while (true) {
//            if (placeShip(input, 5)) {
//                break;
//            }
//        }
//    }

    public void placeShip(String shipType, int shipSize) {
        String input = scanner.nextLine();
        Ship ship = new Ship(input);

        boolean isShipSizeOk = ship.getSize() == shipSize;
        boolean isShipLocationOk = ship.isLocationOk();
        boolean isShipPositionOk = field.isShipPositionOk(ship);

        if (isShipSizeOk && isShipLocationOk && isShipPositionOk) {
            field.placeShip(ship);
        } else if (!isShipSizeOk) {
            System.out.printf("Error! Wrong length of the %s! Try again:\n", shipType);
        } else if (!isShipLocationOk) {
            System.out.println("Error! Wrong ship location! Try again:");
        } else { // !isShipPositionOk
            System.out.println("Error! You placed it too close to another one. Try again:");
        }
    }
}
