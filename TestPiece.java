import static org.junit.Assert.*;
import org.junit.Test;


// Perform tests of Piece functions
public class TestPiece {
	
	// Test constructor
	@Test
	public void testConstructor() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		Piece firePawn = new Piece(true, b, 0, 0, "pawn");
		assertEquals(true, firePawn.isFire());
		assertEquals(false, firePawn.isKing());
		assertEquals(false, firePawn.isShield());
		assertEquals(false, firePawn.isBomb());
		assertEquals(0, firePawn.side());
	}

	// Test booleans
	@Test
	public void testBooleans() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		Piece waterShield = new Piece(false, b, 3, 4, "shield");
		assertEquals(false, waterShield.isKing());
		assertEquals(false, waterShield.isFire());
		assertEquals(true, waterShield.isShield());
		assertEquals(false, waterShield.isBomb());
		assertEquals(1, waterShield.side());

	}

	// Test capture and Bomb capture
	@Test
	public void testCapture() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(true);
		Piece fireBomb = new Piece(true, b, 1, 1, "bomb");
		Piece waterPawna = new Piece(false, b, 2, 2, "pawn");
		Piece waterPawnb = new Piece(false, b, 4, 4, "pawn");
		Piece waterPawnc = new Piece(false, b, 2, 4, "pawn");
		Piece waterPawnd = new Piece(false, b, 4, 2, "pawn");
		b.place(fireBomb, 1, 1);
		b.place(waterPawna, 2, 2);
		b.place(waterPawnb, 4, 4);
		b.place(waterPawnc, 2, 4);		
		b.place(waterPawnd, 4, 2);
		b.select(1,1);
		if (b.canSelect(3,3)) {
			b.select(3,3);
		}
		assertEquals(null, b.pieceAt(2,2));
		assertEquals(null, b.pieceAt(4,4));		
		assertEquals(null, b.pieceAt(2,4));
		assertEquals(null, b.pieceAt(4,2));
		assertEquals(null, b.pieceAt(3,3));
	}


	public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestPiece.class);
	}
}