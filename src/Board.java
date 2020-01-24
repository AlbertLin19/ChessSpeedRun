import java.util.ArrayList;

public class Board {

    public ArrayList<Piece> pieces; // store pieces in here
    private Piece whiteKing;
    private Piece blackKing;
    public boolean isWhiteTurn;

    // setup the board
    public Board() {
        reset();
        isWhiteTurn = true;
    }

    /*
     * -1 means no piece
     *  1 means white
     *  0 means black
     */
    public int[][] getBoardState() {
        int[][] boardState = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardState[i][j] = -1;
            }
        }
        for (Piece piece : pieces) {
            boardState[piece.posY][piece.posX] = piece.isWhite ? 1 : 0;
        }
        return boardState;
    }

    // move a piece at y1, x1 to y2, x2
    // returns false if there is no piece there or if it is illegal
    // or if wrong color
    public boolean movePiece(int y1, int x1, int y2, int x2) {
        // if target location is not on the board, return false
        if (!BooleanMatrixOperations.inbounds(y2, x2)) {
            System.out.println("target not in bounds...");
            return false;
        }
        boolean exists = false;
        Piece pickedPiece = null;
        for (Piece piece : pieces) {
            if (piece.getPosition()[y1][x1] && piece.isWhite() == isWhiteTurn) {
                pickedPiece = piece;
                exists = true;
            }
        }
        if (!exists) {
            System.out.println("piece does not exist for that color");
            return false;
        }

        // move the piece to the new position if it exists as a possible move considering pin
        if (pickedPiece.getMoves(this)[y2][x2]) {
            Piece pieceToRemove = null;
            for (Piece piece : pieces) {
                if (piece.posY == y2 && piece.posX == x2 && piece.isWhite != pickedPiece.isWhite) {
                    pieceToRemove = piece;
                }
            }
            if (pieceToRemove != null) {
                System.out.println("removing enemy piece...");
                pieces.remove(pieceToRemove);
            }
            System.out.println("moving the piece...");
            pickedPiece.setPosition(y2, x2);
        } else {
            System.out.println("illegal move for that piece");
            System.out.println("printing legal moves...");
            boolean[][] moves = pickedPiece.getMoves(this);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(moves[i][j] ? 1 : 0);
                }
                System.out.println();
            }


            System.out.println("printing legal moves not considering pins...");
            moves = pickedPiece.getMovesRegardlessOfPinned(this);
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print(moves[i][j] ? 1 : 0);
                }
                System.out.println();
            }

            return false;
        }

        isWhiteTurn = !isWhiteTurn;
        return true;
    }

    // returns true if the indicated side is in check
    public boolean inCheck(boolean isWhite) {
        Piece king = whiteKing;
        if (!isWhite) {
            king = blackKing;
        }

        // go through all the possible moves of the other pieces
        // without considering pins, and return true if the king is in danger

        for (Piece piece : pieces) {
            if (piece.isWhite != isWhite) {
                if (piece.getMovesRegardlessOfPinned(this)[king.posY][king.posX]) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns true if the indicated side is in checkmate
    public boolean inCheckmate(boolean isWhite) {
        Piece king = whiteKing;
        if (!isWhite) {
            king = blackKing;
        }

        // go through all possible moves by king considering pin
        // if there is a way to escape, return false
        boolean[][] moves = king.getMoves(this);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (moves[y][x]) {
                    return false;
                }
            }
        }

        // not checkmate if there are any legal moves
        for (int index = 0; index < pieces.size(); index++) {
            Piece friendly = pieces.get(index);
            if (friendly.isWhite == isWhite) {
                boolean[][] friendlyMoves = friendly.getMoves(this);
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (friendlyMoves[i][j]) {
                            return false;
                        }
                    }
                }
            }
        }

        // otherwise, checkmated
        return true;
    }

    // reset the board
    private void reset() {
        pieces = new ArrayList<Piece>();

        // initialize the piece array with all the pieces of a starting board

        // create the white pawns
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(6, i, true));
        }
        // create the white rooks
        pieces.add(new Rook(7, 0, true));
        pieces.add(new Rook(7, 7, true));
        // create the white knights
        pieces.add(new Knight(7, 1, true));
        pieces.add(new Knight(7, 6, true));
        // create the white bishops
        pieces.add(new Bishop(7, 2, true));
        pieces.add(new Bishop(7, 5, true));
        // create the white queen and king
        pieces.add(new Queen(7, 3, true));
        whiteKing = new King(7, 4, true);
        pieces.add(whiteKing);

        // create the black pawns
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(1, i, false));
        }
        // create the black rooks
        pieces.add(new Rook(0, 0, false));
        pieces.add(new Rook(0, 7, false));
        // create the black knights
        pieces.add(new Knight(0, 1, false));
        pieces.add(new Knight(0, 6, false));
        // create the black bishops
        pieces.add(new Bishop(0, 2, false));
        pieces.add(new Bishop(0, 5, false));
        // create the black queen and king
        pieces.add(new Queen(0, 3, false));
        blackKing = new King(0, 4, false);
        pieces.add(blackKing);
    }

    // print board with symbols
    public String getBoard() {
        char[][] boardState = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                boardState[i][j] = '0';
            }
        }
        for (Piece piece : pieces) {
            boardState[piece.posY][piece.posX] = piece.getType();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                stringBuilder.append(boardState[row][col]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
