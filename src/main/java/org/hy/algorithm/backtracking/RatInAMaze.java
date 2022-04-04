package main.java.org.hy.algorithm.backtracking;

public class RatInAMaze {
    static int size;

    public static void run(int[][] maze) {
        size = maze.length;
        int[] xMove = {0, 1, 0, -1};
        int[] yMove = {1, 0, -1, 0};
        maze[0][0] = 2;
        if (move(0, 0, maze, xMove, yMove)) {
            print(maze);
        } else {
            System.out.println("No solution");
        }
    }

    private static boolean move(int x, int y, int[][] maze, int[] xMove, int[] yMove) {
        if (x == size - 1 && y == size - 1) return true;
        for (int i = 0; i < 4; i++) {
            int xNext = x + xMove[i];
            int yNext = y + yMove[i];
            if (check(xNext, yNext, maze)) {
                maze[xNext][yNext] = 2;
                if (move(xNext, yNext, maze, xMove, yMove)) {
                    return true;
                } else {
                    maze[xNext][yNext] = 1;
                }
            }
        }
        return false;
    }

    private static boolean check(int x, int y, int[][] maze) {
        boolean checkX = x >= 0 && x < size;
        boolean checkY = y >= 0 && y < size;
        return checkX && checkY && maze[x][y] == 1;
    }

    private static void print(int[][] maze) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (maze[row][col] == 2) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] maze1 = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}};
        run(maze1);
        System.out.println();
        int[][] maze2 = {
                {1, 1, 0, 0},
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {0, 1, 0, 1}};
        run(maze2);
    }
}
