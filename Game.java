package battleship;

import java.util.Arrays;
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

//        placeShip("Aircraft Carrier", 5);
//        field.printField();
//
//        placeShip("Battleship", 4);
//        field.printField();
//
//        placeShip("Submarine", 3);
//        field.printField();
//
//        placeShip("Cruiser", 3);
//        field.printField();
//
//        placeShip("Destroyer", 2);
//        field.printField();

        System.out.println("\nThe game starts!\n");
        field.printField();

        int[] shotPoint = shot();
        System.out.println(Arrays.toString(shotPoint));
    }

    public int[] shot() {
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
