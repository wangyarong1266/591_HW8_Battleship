package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));

		Ship battleship = new Battleship();
		row = 2;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(2, 4));

		Ship cruiser = new Cruiser();
		row = 6;
		column = 8;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(6, 7));

		assertFalse(ocean.isOccupied(9, 7));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));

		Submarine submarine = new Submarine();
		row = 2;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(2, 6));
		assertTrue(submarine.isSunk());

		Cruiser cruiser = new Cruiser();
		row = 7;
		column = 2;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(7, 2));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(6, 2));
		assertFalse(cruiser.isSunk());

		Battleship battleship = new Battleship();
		row = 8;
		column = 9;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(8, 9));
		assertFalse(battleship.isSunk());
		assertFalse(ocean.shootAt(8, 0));
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());

		Ship battleship = new Battleship();
		row = 6;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(6, 8));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(6, 7));
		assertFalse(battleship.isSunk());
		assertEquals(8, ocean.getShotsFired());

		Ship cruiser = new Cruiser();
		row = 9;
		column = 7;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(9, 7));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(9, 6));
		assertFalse(cruiser.isSunk());
		assertEquals(10, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());

		Submarine submarine = new Submarine();
		row = 2;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(2, 6));
		assertTrue(submarine.isSunk());
		assertEquals(2, ocean.getHitCount());

		Battleship battleship = new Battleship();
		row = 6;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		assertFalse(ocean.shootAt(0, 1));
		assertFalse(battleship.isSunk());
		assertEquals(2, ocean.getHitCount());

		Cruiser cruiser = new Cruiser();
		row = 9;
		column = 8;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertFalse(ocean.shootAt(0, 0));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(9, 7));
		assertEquals(3, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		Cruiser cruiser = new Cruiser();
		row = 6;
		column = 7;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(6, 6));
		assertFalse(cruiser.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		Submarine submarine = new Submarine();
		row = 9;
		column = 8;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertFalse(ocean.shootAt(9, 1));
		assertFalse(cruiser.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());

		Submarine submarine = new Submarine();
		int row = 9;
		int column = 8;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertEquals("submarine", shipArray[9][8].getShipType());

		Destroyer destroyer = new Destroyer();
		row = 6;
		column = 6;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertEquals("empty", shipArray[6][0].getShipType());

		Cruiser cruiser = new Cruiser();
		row = 7;
		column = 9;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);

		assertEquals("cruiser", shipArray[6][9].getShipType());

	}

}
