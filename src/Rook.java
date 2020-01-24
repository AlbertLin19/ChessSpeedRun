public class Rook extends Piece {

    public Rook(int posY, int posX, boolean isWhite) {
        super(posY, posX, isWhite);
    }

    @Override
    public char getType() {
        return 'r';
    }

    @Override
    public boolean[][] getMovesRegardlessOfPinned(Board board) {
        boolean[][] possibleMoves = new boolean[8][8];

        // try each new position and see if legal

        int[][] boardState = board.getBoardState();
        /*
         * trying vertical down
         */
        for (int i = posY + 1; i < 8; i++) {
            int state = boardState[i][posX];
            if (state == -1) {
                possibleMoves[i][posX] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[i][posX] = true;
                break;
            }
        }

        /*
         * trying vertical up
         */
        for (int i = posY - 1; i >= 0; i++) {
            int state = boardState[i][posX];
            if (state == -1) {
                possibleMoves[i][posX] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[i][posX] = true;
                break;
            }
        }

        /*
         * trying horizontal left
         */
        for (int i = posX - 1; i >= 0; i++) {
            int state = boardState[posY][i];
            if (state == -1) {
                possibleMoves[posY][i] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[posY][i] = true;
                break;
            }
        }

        /*
         * trying horizontal right
         */
        for (int i = posX + 1; i < 8; i++) {
            int state = boardState[posY][i];
            if (state == -1) {
                possibleMoves[posY][i] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[posY][i] = true;
                break;
            }
        }

        return possibleMoves;
    }
}
