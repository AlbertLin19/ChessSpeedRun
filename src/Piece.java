public abstract class Piece {
    public boolean isWhite;
    public int posX, posY;

    public Piece(int posY, int posX, boolean isWhite) {
        this.isWhite = isWhite;
        this.posX = posX;
        this.posY = posY;
    }

    public boolean isWhite() {
        return isWhite;
    }

    // returns boolean matrix true at the position
    public boolean[][] getPosition() {
        return BooleanMatrixOperations.getBooleanPositionMatrix(posY, posX);
    }

    public void setPosition(boolean[][] newPosition) {
        int[] position = BooleanMatrixOperations.getCoordPos(newPosition);
        posY = position[0];
        posX = position[1];
    }

    public void setPosition(int y, int x) {
        posY = y;
        posX = x;
    }

    public boolean[][] getMoves(Board board) {
        boolean[][] movesRegOfPin = getMovesRegardlessOfPinned(board);
        boolean[][] revised = new boolean[8][8];
        revised = BooleanMatrixOperations.elementWiseAndOperation(movesRegOfPin, revised);

        // store old position
        int oldY = posY;
        int oldX = posX;

        // check each possible move for if it causes a check
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (movesRegOfPin[y][x]) {
                    setPosition(y, x);

                    // remove any enemy piece if there
                    Piece removed = null;
                    for (Piece piece : board.pieces) {
                        if (piece.getPosition()[y][x]) {
                            removed = piece;
                        }
                    }
                    board.pieces.remove(removed);

                    if (board.inCheck(isWhite)) {
                        revised[y][x] = false;
                    }

                    // return the old positions
                    board.pieces.add(removed);
                    setPosition(oldY, oldX);
                }
            }
        }

        return revised;
    }

    public abstract boolean[][] getMovesRegardlessOfPinned(Board board);

    public abstract char getType();
}
