public class Sudoku {

    private int[][] puzzle;
    private boolean solved;

    public Sudoku(int[][] p) {
        puzzle = p;
    }

    public void solve(int x, int y) {
        if (x == 9) {
            solved = true;
        } else {
            if (puzzle[x][y] != 0) {
                if (y < 8) {
                    solve(x, y + 1);
                } else {
                    solve(x + 1, 0);
                }
            } else {
                int counter = 0;
                for (int i = 1; i < 10; i++) {
                    if (fits(x, y, i)) {
                        puzzle[x][y] = i;
                        if (y < 8) {
                            solve(x, y + 1);
                        } else {
                            solve(x + 1, 0);
                        }
                        if (!solved) {
                            puzzle[x][y] = 0;
                            counter++;
                        }
                    }
                }
                if (counter == 0 && !solved) {
                    puzzle[x][y] = 0;
                }
            }
        }
    }

    public boolean place(int xc, int yc) {
        for (int i = 1; i < 10; i++) {
            if (fits(xc, yc, i)) {
                puzzle[xc][yc] = i;
                return true;
            }
        }
        return false;
    }

    public boolean fits(int xc, int yc, int number) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[i][yc] == number) {
                return false;
            }//check the row
            if (puzzle[xc][i] == number) {
                return false;
            }//check the columb
        }
        int boxX = (int) Math.floor(xc / 3.0) * 3;//knowing where the box starts
        int boxY = (int) Math.floor(yc / 3.0) * 3;
        for (int i = boxX; i < boxX + 3; i++) {
            for (int j = boxY; j < boxY + 3; j++) {
                if (puzzle[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                res += "|" + puzzle[i][j] + "|";
            }
            res += "\n";
        }
        return res;
    }
}