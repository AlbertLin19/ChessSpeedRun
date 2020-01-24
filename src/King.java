public class King extends Piece {

    public King(int posY, int posX, boolean isWhite) {
        super(posY, posX, isWhite);
    }

    @Override
    public char getType() {
        return 'k';
    }

    @Override
    public boolean[][] getMovesRegardlessOfPinned(Board board) {
        boolean[][] possibleMoves = new boolean[8][8];

        int[][] boardState = board.getBoardState();
        /*
         * trying each of the 8 possibility
         */
        int[][] positions = {
                {posY - 1, posX - 1}, {posY - 1, posX + 1}, {posY + 1, posX + 1}, {posY + 1, posX - 1},
                {posY, posX + 1}, {posY, posX - 1}, {posY - 1, posX}, {posY + 1, posX}};
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
