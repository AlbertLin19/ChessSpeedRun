public class Pawn extends Piece {

    public Pawn(int posY, int posX, boolean isWhite) {
        super(posY, posX, isWhite);
    }

    @Override
    public char getType() {
        return 'p';
    }

    @Override
    public boolean[][] getMovesRegardlessOfPinned(Board board) {
        boolean[][] possibleMoves = new boolean[8][8];

        // try each new position and see if legal

        /*
         * moving up/down allowed if no piece already there
         * and within bounds
         */
        int move = -1;
        if (!isWhite) {
            move = 1;
        }

        // check for no piece and in bounds
        if (BooleanMatrixOperations.inbounds(posY + move, posX) && board.getBoardState()[posY + move][posX] == -1) {
            possibleMoves[posY + move][posX] = true;
        }


        // do this for move = 2 if the pawn is in the original row
        if (posY == 1 && !isWhite || posY == 6 && isWhite) {
            int move2 = -2;
            if (!isWhite) {
                move2 = 2;
            }
            // check if in bounds and for no piece in between
            if (BooleanMatrixOperations.inbounds(posY + move2, posX) && board.getBoardState()[posY + move2][posX] == -1 && board.getBoardState()[posY + move][posX] == -1) {
                // check for no piece
                possibleMoves[posY + move2][posX] = true;
            }
        }

        // moving diagonally
        // two directions
        if (BooleanMatrixOperations.inbounds(posY + move, posX + 1) && board.getBoardState()[posY + move][posX + 1] == (isWhite ? 0 : 1)) {
            possibleMoves[posY + move][posX + 1] = true;
        }

        if (BooleanMatrixOperations.inbounds(posY + move, posX - 1) && board.getBoardState()[posY + move][posX - 1] == (isWhite ? 0 : 1)) {
            possibleMoves[posY + move][posX - 1] = true;
        }


        return possibleMoves;
    }
}
