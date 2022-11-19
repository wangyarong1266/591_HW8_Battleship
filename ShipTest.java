package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		ship = new Submarine();
		assertEquals(1, ship.getLength());

		ship = new Destroyer();
		assertEquals(2, ship.getLength());

		ship = new Cruiser();
		assertEquals(3, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		Ship submarine = new Submarine();
		int submarineRow = 6;
		int submarineColumn = 8;
		boolean submarineHorizontal = true;
		submarine.placeShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);
		assertEquals(submarineRow, submarine.getBowRow());

		Ship destroyer = new Destroyer();
		int destroyerRow = 9;
		int destroyerColumn = 9;
		boolean destroyerHorizontal = false;
		destroyer.placeShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);
		assertEquals(destroyerColumn, destroyer.getBowColumn());

		Ship cruiser = new Cruiser();
		int cruiserRow = 9;
		int cruiserColumn = 1;
		boolean cruiserHorizontal = false;
		cruiser.placeShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);
		assertEquals(cruiserColumn, cruiser.getBowColumn());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		Ship submarine = new Submarine();
		int submarineRow = 4;
		int submarineColumn = 0;
		boolean submarineHorizontal = false;
		submarine.placeShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);
		submarine.setBowColumn(submarineColumn);
		assertEquals(submarineColumn, submarine.getBowColumn());

		Ship cruiser = new Cruiser();
		int cruiserRow = 6;
		int cruiserColumn = 7;
		boolean cruiserHorizontal = true;
		cruiser.placeShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);
		cruiser.setBowColumn(cruiserColumn);
		assertEquals(cruiserColumn, cruiser.getBowColumn());

		Ship destroyer = new Destroyer();
		int destroyerRow = 8;
		int destroyerColumn = 8;
		boolean destroyerHorizontal = false;
		destroyer.placeShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);
		destroyer.setBowColumn(destroyerColumn);
		assertEquals(destroyerColumn, destroyer.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		ship = new Submarine();
		boolean[] submarineHits = new boolean[1];
		assertArrayEquals(submarineHits, ship.getHit());
		assertFalse(ship.getHit()[0]);

		ship = new Cruiser();
		boolean[] cruiserHits = new boolean[3];
		assertArrayEquals(cruiserHits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);

		ship = new Destroyer();
		boolean[] destroyerHits = new boolean[2];
		assertArrayEquals(destroyerHits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);

		ship = new EmptySea();
		boolean[] emptySeaHits = new boolean[1];
		assertArrayEquals(emptySeaHits, ship.getHit());
		assertFalse(ship.getHit()[0]);
	}

	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());

		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());

		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());

		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());

		ship = new EmptySea();
		assertEquals("empty", ship.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());

		Ship submarine = new Submarine();
		int submarineRow = 4;
		int submarineColumn = 0;
		boolean submarineHorizontal = false;
		submarine.placeShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);
		assertFalse(submarine.isHorizontal());

		Ship cruiser = new Cruiser();
		int cruiserRow = 6;
		int cruiserColumn = 7;
		boolean cruiserHorizontal = true;
		cruiser.placeShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);
		assertTrue(cruiser.isHorizontal());

		Ship destroyer = new Destroyer();
		int destroyerRow = 8;
		int destroyerColumn = 8;
		boolean destroyerHorizontal = false;
		destroyer.placeShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);
		assertFalse(destroyer.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());

		Ship submarine = new Submarine();
		int submarineRow = 4;
		int submarineColumn = 0;
		boolean submarineHorizontal = false;
		submarine.setBowRow(submarineRow);
		assertEquals(submarineRow, submarine.getBowRow());

		Ship cruiser = new Cruiser();
		int cruiserRow = 6;
		int cruiserColumn = 7;
		boolean cruiserHorizontal = true;
		cruiser.setBowRow(cruiserRow);
		assertEquals(cruiserRow, cruiser.getBowRow());

		Ship destroyer = new Destroyer();
		int destroyerRow = 8;
		int destroyerColumn = 8;
		boolean destroyerHorizontal = false;
		destroyer.setBowRow(destroyerRow);
		assertEquals(destroyerRow, destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());

		Ship submarine = new Submarine();
		int submarineRow = 4;
		int submarineColumn = 0;
		boolean submarineHorizontal = false;
		submarine.setBowColumn(submarineColumn);
		assertEquals(submarineColumn, submarine.getBowColumn());

		Ship cruiser = new Cruiser();
		int cruiserRow = 6;
		int cruiserColumn = 7;
		boolean cruiserHorizontal = true;
		cruiser.setBowColumn(cruiserColumn);
		assertEquals(cruiserColumn, cruiser.getBowColumn());

		Ship destroyer = new Destroyer();
		int destroyerRow = 8;
		int destroyerColumn = 8;
		boolean destroyerHorizontal = false;
		destroyer.setBowColumn(destroyerColumn);
		assertEquals(destroyerColumn, destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());

		Ship submarine = new Submarine();
		int submarineRow = 4;
		int submarineColumn = 0;
		boolean submarineHorizontal = false;
		submarine.setHorizontal(submarineHorizontal);
		assertFalse(submarine.isHorizontal());

		Ship cruiser = new Cruiser();
		int cruiserRow = 6;
		int cruiserColumn = 7;
		boolean cruiserHorizontal = true;
		cruiser.setHorizontal(cruiserHorizontal);
		assertTrue(cruiser.isHorizontal());

		Ship destroyer = new Destroyer();
		int destroyerRow = 8;
		int destroyerColumn = 8;
		boolean destroyerHorizontal = false;
		destroyer.setHorizontal(destroyerHorizontal);
		assertFalse(destroyer.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");

		//test the submarine can not be placed next to the battleship
		battleship.placeShipAt(row, column, horizontal, ocean);
		Ship submarine = new Submarine();
		int submarineRow = 0;
		int submarineColumn = 5;
		boolean submarineHorizontal = true;
		boolean submarineNotOk = submarine.okToPlaceShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);
		assertFalse(submarineNotOk, "Not Ok to place ship here.");

		//test the cruiser can not be placed next to the battleship
		Ship cruiser = new Cruiser();
		int cruiserRow = 2;
		int cruiserColumn = 5;
		boolean cruiserHorizontal = false;
		boolean cruiserNotOk = cruiser.okToPlaceShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);
		assertFalse(cruiserNotOk, "Not Ok to place ship here.");

		//test the destroyer can not be placed at the position where the battleship has been placed
		Ship destroyer = new Destroyer();
		int destroyerRow = 0;
		int destroyerColumn = 4;
		boolean destroyerHorizontal = true;
		boolean destroyerNotOk = destroyer.okToPlaceShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);
		assertFalse(destroyerNotOk, "Not Ok to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

		//test third ship
		Battleship battleship3 = new Battleship();
		row = 3;
		column = 5;
		horizontal = false;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3, "Not OK to place ship right next to the battleship1.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);

		Ship submarine = new Submarine();
		int submarineRow = 2;
		int submarineColumn = 6;
		boolean submarineHorizontal = true;
		submarine.placeShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);
		assertEquals(submarineRow, submarine.getBowRow());
		assertEquals(submarineColumn, submarine.getBowColumn());
		assertTrue(submarine.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[2][1].getShipType());
		assertEquals(submarine, ocean.getShipArray()[2][6]);

		Ship cruiser = new Cruiser();
		int cruiserRow = 6;
		int cruiserColumn = 2;
		boolean cruiserHorizontal = false;
		cruiser.placeShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);
		assertEquals(cruiserRow, cruiser.getBowRow());
		assertEquals(cruiserColumn, cruiser.getBowColumn());
		assertFalse(cruiser.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[6][0].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[5][2]);

		Ship destroyer = new Destroyer();
		int destroyerRow = 7;
		int destroyerColumn = 8;
		boolean destroyerHorizontal = true;
		destroyer.placeShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);
		assertEquals(destroyerRow, destroyer.getBowRow());
		assertEquals(destroyerColumn, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());

		assertEquals("empty", ocean.getShipArray()[7][1].getShipType());
		assertEquals(destroyer, ocean.getShipArray()[7][7]);
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());

		Ship submarine = new Submarine();
		int submarineRow = 2;
		int submarineColumn = 8;
		boolean submarineHorizontal = true;
		submarine.placeShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);

		assertFalse(submarine.shootAt(2, 4));
		boolean[] submarineHitArray = {false};
		assertArrayEquals(submarineHitArray, submarine.getHit());
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		Ship battleShip = new Battleship();
		int battleShipRow = 4;
		int battleShipColumn = 6;
		boolean battleShipHorizontal = true;
		battleShip.placeShipAt(battleShipRow, battleShipColumn, battleShipHorizontal, ocean);

		assertFalse(battleShip.isSunk());
		assertTrue(battleShip.shootAt(4, 4));
		assertFalse(battleShip.isSunk());

		Ship cruiser = new Cruiser();
		int cruiserRow = 8;
		int cruiserColumn = 2;
		boolean cruiserHorizontal = false;
		cruiser.placeShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);

		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(8, 2));
		assertTrue(cruiser.shootAt(7, 2));
		assertTrue(cruiser.shootAt(6, 2));
		assertTrue(cruiser.isSunk());

		Ship destroyer = new Destroyer();
		int destroyerRow = 9;
		int destroyerColumn = 7;
		boolean destroyerHorizontal = true;
		destroyer.placeShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);

		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(9, 7));
		assertFalse(destroyer.shootAt(9, 0));
		assertFalse(destroyer.isSunk());
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());

		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());

		int submarineRow = 0;
		int submarineColumn = 1;
		boolean submarineHorizontal = false;
		submarine.placeShipAt(submarineRow, submarineColumn, submarineHorizontal, ocean);
		submarine.shootAt(submarineRow, submarineColumn);
		assertEquals("s", submarine.toString());

		Ship cruiser = new Cruiser();
		assertEquals("x", cruiser.toString());

		int cruiserRow = 6;
		int cruiserColumn = 8;
		boolean cruiserHorizontal = true;
		cruiser.placeShipAt(cruiserRow, cruiserColumn, cruiserHorizontal, ocean);
		cruiser.shootAt(6, 7);
		assertEquals("x", cruiser.toString());

		Ship destroyer = new Destroyer();
		assertEquals("x", destroyer.toString());

		int destroyerRow = 9;
		int destroyerColumn = 9;
		boolean destroyerHorizontal = false;
		destroyer.placeShipAt(destroyerRow, destroyerColumn, destroyerHorizontal, ocean);
		destroyer.shootAt(8, 9);
		assertEquals("x", destroyer.toString());
	}

}
