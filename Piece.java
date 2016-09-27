public class Piece {
	// instance variables
	private Board board;
	private int xPos;
	private int yPos;
	private String typePiece;
	private boolean isFireSide;
	private boolean isCrowned = false;
	private boolean hasCapt = false;


	// constructor initializes Piece
	public Piece(boolean isFire, Board b, int x, int y, String type) {

		isFireSide = isFire;
		typePiece = type;
		xPos = x;
		yPos = y;
		board = b;
		isCrowned = false;

	}

	// returns whether piece is fire or not
	public boolean isFire() {
		return isFireSide == true;
	}

	// returns whether piece is fire or water
	public int side() {
		if (isFireSide) {
			return 0; // 0 for fire
		}
		else {
			return 1; // 1 for water
		}
	
	}

	// returns whether piece has been crowned
	public boolean isKing() {
		return isCrowned;
	}

	// returns whether piece is bomb
	public boolean isBomb() {
		return typePiece == "bomb";
	}

	// returns whether piece is shield
	public boolean isShield() {
		return typePiece == "shield";
	}

	// removes captured piece (assumed valid bc move assumes valid) while moving to (x,y)
	private void captureNormal(int x, int y) {
		// potential capture (x+/-2, y+/-2) for Crowned Pieces
		// find position of piece being captured
		int xDis = x - ((x-xPos)/2); 
		int yDis = y - ((y-yPos)/2); // positive if moving up, negative if moving down
		Piece a = board.remove(xDis,yDis);
	}

	// removes all pieces within range except shields
	// given captureNormal has occurred and bomb lands on (x,y)
	private void bombCapture(int x, int y) {
		Piece a;
		Piece bomb;
		int xNew;
		int yNew;
		for (int i = -1; i <=1; i +=1) {
			for (int j = -1; j <= 1; j +=1) {
				xNew = x + i;
				yNew = y + j;
				a = board.pieceAt(xNew,yNew);
				if (a!= null) {
					if (!a.isShield()) {
						a = board.remove(xNew,yNew);
					}
				}
			}
		}
		bomb = board.remove(x,y);
	}

	// moves Piece to (x,y) (assumes valid) & captures if applicable
 	public void move(int x, int y) {
 		int xDis = Math. abs(xPos - x);
 		int yDis = Math.abs(yPos - y);
 		// Capture conditions if distance > 1
 	 	if ((xDis > 1) || (yDis > 1)) {
 			captureNormal(x, y);
 		 	hasCapt = true;
 			if (isBomb()) {
 				bombCapture(x,y);
 				doneCapturing();
 			}
 		}
 		becomeKing(y);
 	 	xPos = x;
 		yPos = y;
	}

	// kings pieces if they reach end
	private void becomeKing(int y) {
		if (isFireSide) {
			if (y==7) {
				isCrowned = true;
			}
		}
		else {
			if (y==0) {
				isCrowned = true;
			}
		}
	}

	// Returns if piece has captured another piece this turn
	public boolean hasCaptured() {
		return hasCapt;
	}

	// called at end of each move to reset hasCapt
	public void doneCapturing() {
		hasCapt = false;
	}


}