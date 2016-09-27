import static org.junit.Assert.*;
import org.junit.Test;


// Perform tests of Board functions
public class TestBoard {

	// Test PieceAt
	@Test
	public void testPieceAt1() {
		Board b = new Board(false);
		Piece firePawn = new Piece(true, b, 0, 0, "pawn");
		assertEquals(firePawn.isFire(), b.pieceAt(0,0).isFire());
		assertEquals(firePawn.isKing(), b.pieceAt(0,0).isKing());
		assertEquals(firePawn.isShield(), b.pieceAt(0,0).isShield());
		Piece waterBomb = new Piece(false, b, 5, 5, "bomb");
		assertEquals(waterBomb.isFire(), b.pieceAt(5,5).isFire());
		assertEquals(waterBomb.isKing(), b.pieceAt(5,5).isKing());
		assertEquals(waterBomb.isShield(), b.pieceAt(5,5).isShield());
		assertEquals(waterBomb.isBomb(), b.pieceAt(5,5).isBomb());
	}

	// Test PieceAt again for null cases
	@Test
	public void testPieceAt2() {
		Board b = new Board(false);
		assertEquals(null, b.pieceAt(3,6));
		assertEquals(null, b.pieceAt(7, 10));

	}

	// test Place and Remove
	@Test
	public void testPlaceRemove() {
		Board b = new Board(true);
		Piece firePawn = new Piece(true, b, 0, 0, "pawn");
		b.place(firePawn, 4, 5);
		assertEquals(firePawn.isFire(), b.pieceAt(4,5).isFire());
		assertEquals(firePawn.isKing(), b.pieceAt(4,5).isKing());
		Piece waterBomb = new Piece(false, b, 5, 5, "bomb");
		b.place(waterBomb, 3, 7);
		assertEquals(waterBomb.isFire(), b.pieceAt(3,7).isFire());
		assertEquals(waterBomb.isKing(), b.pieceAt(3,7).isKing());
		assertEquals(waterBomb.isBomb(), b.pieceAt(3,7).isBomb());
		Piece lost = b.remove(3, 7);
		assertEquals(waterBomb.isFire(),lost.isFire());
		assertEquals(waterBomb.isBomb(), lost.isBomb());
	}

	// test canSelect and select methods
	@Test
	public void testCanSelect() {
		Board b = new Board(false);
		assertEquals(true, b.canSelect(4,0));
		assertEquals(true, b.canSelect(7,1));
		assertEquals(false, b.canSelect(5,5));
		assertEquals(false, b.canSelect(4,10));
		assertEquals(true, b.canSelect(6,2));
		b.select(6,2);
		assertEquals(true, b.canSelect(5,3));
		assertEquals(true, b.canSelect(7,3));
		assertEquals(true, b.canSelect(7,1));
		assertEquals(false, b.canSelect(5,5));		
	}

	// Test end results (Counter and winner statement)
	@Test
	public void testWinner() {
		Board b = new Board(true);
		Piece waterBomb = new Piece(false, b, 5, 5, "bomb");
		Piece firePawn = new Piece(true, b, 1, 1, "pawn");
		Piece waterShield = new Piece(false, b, 2, 2, "shield");
		b.endTurn();
		assertEquals("No one", b.winner());
		b.place(firePawn, 0, 6);
		b.endTurn();
		assertEquals("Fire", b.winner());
		b.place(waterBomb, 0, 0);
		b.endTurn();
		assertEquals(null, b.winner());
		b.remove(0,6);
		b.place(waterShield, 3, 1);
		b.endTurn();
		assertEquals("Water", b.winner());
	}

	// Test becoming king
	@Test
	public void testKing(){
		Board b = new Board(true);
		Piece firePawn = new Piece(true, b, 6, 6, "pawn");
		b.place(firePawn, 6, 6);
		if (b.canSelect(6,6)) {
			b.select(6,6);
		}
		if (b.canSelect(5,7)) {
			b.select(5,7);
		}
		Piece check = b.pieceAt(5,7);
		assertEquals(true, check.isFire());
		assertEquals(true, check.isKing());
	}

	public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestBoard.class);
	}
}