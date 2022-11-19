package battleship;

public class Cruiser extends Ship {
    static final int cruiserLength = 3;
    static final String cruiserType = "cruiser";
    public Cruiser() {
        super(cruiserLength);
    }

    @Override
    public String getShipType() {
        return cruiserType;
    }
}
