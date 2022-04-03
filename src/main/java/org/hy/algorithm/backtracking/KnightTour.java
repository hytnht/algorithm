package main.java.org.hy.algorithm.backtracking;

public class KnightTour {
    static int size;

    public static void tour(int size) {
        KnightTour.size = size;
        int[] xMove = new int[]{-2, -2, -1, -1, 1, 1, 2, 2};
        int[] yMove = new int[]{-1, 1, -2, 2, -2, 2, -1, 1};
        int[][] board = new int[size][size];
        board[0][0] = -1;
        if (move(0, 0, 1, board, xMove, yMove)) {
            board[0][0] = 0;
            print(board);
        } else {
            System.out.println("No solution.");
        }
    }

    private static boolean move(int x, int y, int step, int[][] board, int[] xMove, int[] yMove) {
        if (step == size * size) return true;
        for (int i = 0; i < 8; i++) {
            int xNext = x + xMove[i];
            int yNext = y + yMove[i];
            if (check(xNext, yNext, board)) {
                board[xNext][yNext] = step;
                if (move(xNext, yNext, step + 1, board, xMove, yMove)) {
                    return true;
                } else {
                    board[xNext][yNext] = 0;
                }
            }
        }
        return false;
    }

    private static boolean check(int x, int y, int[][] board) {
        boolean checkX = x >= 0 && x < size;
        boolean checkY = y >= 0 && y < size;
        return checkY && checkX && board[x][y] == 0;
    }

    private static void print(int[][] board) {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.printf("%3d", board[row][column]);
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        tour(3);
        System.out.println();
        tour(5);
        System.out.println();
        tour(7);
    }
}
