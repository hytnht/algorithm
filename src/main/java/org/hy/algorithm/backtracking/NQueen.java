package main.java.org.hy.algorithm.backtracking;

public class NQueen {
    static int n;

    public static void play(int n) {
        NQueen.n = n;
        int[][] board = new int[n][n];
        if (place(0, board)) {
            print(board);
            return;
        }
        System.out.println("No solution.");
    }

    private static boolean place(int y, int[][] board) {
        if (y == n) return true;
        for (int i = 0; i < n; i++) {
            if (check(i, y, board)) {
                board[i][y] = 1;
                if (place(y + 1, board)) return true;
                else board[i][y] = 0;
            }
        }
        return false;
    }

    private static boolean check(int x, int y, int[][] board) {
        for (int i = 0; i < n; i++) {
            if (board[x][i] == 1) return false;
            if (y - i >= 0) {
                if (x - i >= 0)
                    if (board[x - i][y - i] == 1) return false;
                if (x + i < n)
                    if (board[x + i][y - i] == 1) return false;
            }
        }
        return true;
    }

    private static void print(int[][] board) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        play(3);
        System.out.println();
        play(4);
        System.out.println();
        play(6);
    }
}
