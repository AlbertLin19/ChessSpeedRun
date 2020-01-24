public class Knight extends Piece {

    public Knight(int posY, int posX, boolean isWhite) {
        super(posY, posX, isWhite);
    }

    @Override
    public char getType() {
        return 'h';
    }

    @Override
    public boolean[][] getMovesRegardlessOfPinned(Board board) {
        boolean[][] possibleMoves = new boolean[8][8];

        // try each new position and see if legal

        int[][] boardState = board.getBoardState();
        /*
         * trying each of the 8 possibility
         */
        int[][] positions = {
                {posY - 2, posX - 1}, {posY - 2, posX + 1}, {posY + 2, posX + 1}, {posY + 2, posX - 1},
                {posY - 1, posX + 2}, {posY + 1, posX + 2}, {posY - 1, posX - 2}, {posY + 1, posX - 2}};
        for (int i = 0; i < positions.length; i++) {
            int y = positions[i][0];
            int x = positions[i][1];
            if (BooleanMatrixOperations.inbounds(y, x) && boardState[y][x] != (isWhite ? 1 : 0)) {
                possibleMoves[y][x] = true;
            }
        }

        return possibleMoves;
    }
}
