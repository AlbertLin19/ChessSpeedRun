public class Queen extends Piece {

    public Queen(int posY, int posX, boolean isWhite) {
        super(posY, posX, isWhite);
    }

    @Override
    public char getType() {
        return 'q';
    }

    @Override
    public boolean[][] getMovesRegardlessOfPinned(Board board) {
        boolean[][] possibleMoves = new boolean[8][8];

        // try each new position and see if legal

        int[][] boardState = board.getBoardState();
        /*
         * trying up-left
         */
        for (int i = -1; posY + i >= 0 && posX + i >= 0; i--) {
            int state = boardState[posY + i][posX + i];
            if (state == -1) {
                possibleMoves[posY + i][posX + i] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[posY + i][posX + i] = true;
                break;
            }
        }

        /*
         * trying up-right
         */
        for (int i = -1; posY + i >= 0 && posX - i < 8; i--) {
            int state = boardState[posY + i][posX - i];
            if (state == -1) {
                possibleMoves[posY + i][posX - i] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[posY + i][posX - i] = true;
                break;
            }
        }

        /*
         * trying down-left
         */
        for (int i = -1; posY - i < 8 && posX + i >= 0; i--) {
            int state = boardState[posY - i][posX + i];
            if (state == -1) {
                possibleMoves[posY - i][posX + i] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[posY - i][posX + i] = true;
                break;
            }
        }

        /*
         * trying down-right
         */
        for (int i = 1; posY + i < 8 && posX + i < 8; i++) {
            int state = boardState[posY + i][posX + i];
            if (state == -1) {
                possibleMoves[posY + i][posX + i] = true;
                continue;
            }
            if (state == (isWhite ? 1 : 0)) {
                break;
            }
            if (state == (isWhite ? 0 : 1)) {
                possibleMoves[posY + i][posX + i] = true;
                break;
            }
        }

        // try each new position and see if legal

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
        for (int i = posY - 1; i >= 0; i--) {
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
