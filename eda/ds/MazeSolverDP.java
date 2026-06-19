package eda.ds;

import eda.adt.MazeSolver;
import java.util.ArrayList;
import java.util.List;

public class MazeSolverDP implements MazeSolver {

    private static final int INF = Integer.MAX_VALUE / 2;

    @Override
    public List<int[]> solve(Maze maze, int[] origin, int[] destination) {
        int[][] memo = new int[maze.rows][maze.cols];
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                memo[i][j] = -1;
            }
        }

        boolean[][] visited = new boolean[maze.rows][maze.cols];
        computeCosts(maze, origin[0], origin[1], destination[0], destination[1], visited, memo);

        List<int[]> path = new ArrayList<>();
        if (memo[origin[0]][origin[1]] >= INF) {
            return path;
        }

        int r = origin[0];
        int c = origin[1];
        path.add(new int[]{r, c});

        while (r != destination[0] || c != destination[1]) {
            int[][] directions = {
                {r - 1, c},
                {r + 1, c},
                {r, c - 1},
                {r, c + 1}
            };

            int bestR = -1;
            int bestC = -1;
            int minCost = INF;

            for (int[] dir : directions) {
                int nr = dir[0];
                int nc = dir[1];

                if (nr >= 0 && nr < maze.rows && nc >= 0 && nc < maze.cols) {
                    if (maze.data[nr][nc] == Maze.EMPTY && memo[nr][nc] != -1 && memo[nr][nc] < minCost) {
                        minCost = memo[nr][nc];
                        bestR = nr;
                        bestC = nc;
                    }
                }
            }

            if (bestR == -1) {
                break;
            }

            r = bestR;
            c = bestC;
            path.add(new int[]{r, c});
        }

        return path;
    }

    private int computeCosts(Maze maze, int r, int c, int destR, int destC, boolean[][] visited, int[][] memo) {
        if (r < 0 || r >= maze.rows || c < 0 || c >= maze.cols) {
            return INF;
        }
        if (maze.data[r][c] == Maze.WALL || visited[r][c]) {
            return INF;
        }
        if (r == destR && c == destC) {
            memo[r][c] = 0;
            return 0;
        }
        if (memo[r][c] != -1) {
            return memo[r][c];
        }

        visited[r][c] = true;

        int[][] directions = {
            {r - 1, c},
            {r + 1, c},
            {r, c - 1},
            {r, c + 1}
        };

        int minNeighbors = INF;
        for (int[] dir : directions) {
            int cost = computeCosts(maze, dir[0], dir[1], destR, destC, visited, memo);
            if (cost < minNeighbors) {
                minNeighbors = cost;
            }
        }

        visited[r][c] = false;

        if (minNeighbors >= INF) {
            memo[r][c] = INF;
        } else {
            memo[r][c] = minNeighbors + 1;
        }

        return memo[r][c];
    }
}