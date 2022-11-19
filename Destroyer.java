package battleship;

public class Destroyer extends Ship {
    static final int destroyerLength = 2;
    static final String destroyerType = "destroyer";
    public Destroyer() {
        super(destroyerLength);
    }

    @Override
    public String getShipType() {
        return destroyerType;
    }
}
