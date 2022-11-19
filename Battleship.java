package battleship;

public class Battleship extends Ship {
    static final int battleshipLength = 4;
    static final String battleshipType = "battleship";
    public Battleship() {
        super(battleshipLength);
    }

    @Override
    public String getShipType() {
        return battleshipType;
    }
}
