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
        Ship[] ships = new Ship[5]; // массив всех кораблей

        Ship aircraftCarrier = placeShip("Aircraft Carrier", 5);
        ships[0] = aircraftCarrier;
        myField.printField();

        Ship battleship = placeShip("Battleship", 4);
        ships[1] = battleship;
        myField.printField();

        Ship submarine = placeShip("Submarine", 3);
        ships[2] = submarine;
        myField.printField();

        Ship cruiser =  placeShip("Cruiser", 3);
        ships[3] = cruiser;
        myField.printField();

        Ship destroyer = placeShip("Destroyer", 2);
        ships[4] = destroyer;
        myField.printField();

        System.out.println("\nThe game starts!");

        myField.printFogField();

        while (myField.hasShipAfloat()) { // пока хоть один корабль на плаву
            int[] shotPoint = shotPoint(); // Take a shot возвращает координаты
            shootRegistration(shotPoint);  // Отмечает на карте X или M
            myField.printFieldWithFogAndShotPoint(); // Выводит карту с туманом войны
            printHitOrMiss(shotPoint);  // You hit a ship! или You missed!

            // проверка на потопление корабля
            for (Ship ship : ships) {
                if (ship.atFloat) { // если корабль на плаву
                    if (!ship.isShipAtFloat(myField) && myField.hasShipAfloat()) { // если проверка показала что он утонул
                        System.out.println("You sank a ship! Specify a new target:");
                    }
                }
            }

        }

        System.out.println("You sank the last ship. You won. Congratulations!");





    }

    public void shootRegistration(int[] shotPoint) {
        int row = shotPoint[0];
        int col = shotPoint[1];
        if (myField.field[row][col].equals(" O") || myField.field[row][col].equals(" X") ) {
            myField.field[row][col] = " X";
        } else {
            myField.field[row][col] = " M";
        }
    }

    public void printHitOrMiss(int[] shotPoint) {
        int row = shotPoint[0];
        int col = shotPoint[1];
        if (myField.field[row][col].equals(" X") && myField.hasShipAfloat()) {
            System.out.println("\nYou hit a ship!");
        } else if (myField.field[row][col].equals(" M")){
            System.out.println("\nYou missed!");
        }
    }

    public int[] shotPoint() {
        System.out.println("\nTake a shot!\n");

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


    public Ship placeShip(String shipType, int shipSize) {
        System.out.printf("\nEnter the coordinates of the %s (%d cells):\n\n", shipType, shipSize);

        while (true) {
            String input = scanner.nextLine();
            Ship ship = new Ship(input, shipType);

            boolean isShipSizeOk = ship.getSize() == shipSize;
            boolean isShipLocationOk = ship.isLocationOk();
            boolean isShipPositionOk = myField.isShipPositionOk(ship);

            if (isShipSizeOk && isShipLocationOk && isShipPositionOk) {
                myField.placeShip(ship);
                return ship;
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
