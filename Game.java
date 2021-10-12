package battleship;

public class Game {
    Field field;

    public Game() {
        field = new Field();
    }

    public void start() {
        field.printField();
        System.out.println(placeShip(field, "A1 A3", 3));
        field.printField();

        System.out.println(placeShip(field, "B8 B10", 3));
        field.printField();

    }

    public String placeShip(Field field, String input, int shipSize) {
        Ship ship = new Ship(input);
        // checkShipSize
        boolean isShipSizeOk = ship.size() == shipSize;
        boolean isShipLocationOk = ship.isLocationOk();
        boolean isShipPositionOk = field.isShipPositionOk(ship);

        if (isShipSizeOk && isShipLocationOk && isShipPositionOk) {
            field.placeShip(ship);
            return "ok";
        } else if (!isShipSizeOk) {
            return "sizeError";
        } else if (!isShipLocationOk) {
            return "locationError";
        } else if (!isShipPositionOk) {
            return "positionError";
        }
        return "unknownError";
    }

}
