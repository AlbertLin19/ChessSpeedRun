public class BooleanMatrixOperations {
    // must be same dimensions
    public static boolean[][] elementWiseAndOperation(boolean[][] m1, boolean[][] m2) {
        boolean[][] newM = new boolean[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                if (m1[i][j] || m2[i][j]) {
                    newM[i][j] = true;
                }
            }
        }
        return newM;
    }

    // create position boolean matrix ([y, x] is set to true)
    public static boolean[][] getBooleanPositionMatrix(int y, int x) {
        boolean[][] position = new boolean[8][8];
        position[y][x] = true;
        return position;
    }

    // get coord position from boolean matrix (y, x)
    public static int[] getCoordPos(boolean[][] booleanPos) {
        int[] coordPos = new int[2];
        for (int y = 0; y < booleanPos.length; y++) {
            for (int x = 0; x < booleanPos[0].length; x++) {
                if (booleanPos[y][x]) {
                    coordPos[0] = y;
                    coordPos[1] = x;
                    break;
                }
            }
        }
        return coordPos;
    }

    // check if inbounds
    public static boolean inbounds(int y, int x) {
        return y < 8 && y >= 0 && x < 8 && x >= 0;
    }
}
