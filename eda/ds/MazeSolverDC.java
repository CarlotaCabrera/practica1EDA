package eda.ds;

import eda.adt.MazeSolver;
import java.util.ArrayList;
import java.util.List;

public class MazeSolverDC implements MazeSolver {

    @Override
    public List<int[]> solve(Maze maze, int[] origin, int[] destination) {
        boolean[][] visited = new boolean[maze.rows][maze.cols];
        List<int[]> currentPath = new ArrayList<>();
        List<int[]> bestPath = new ArrayList<>();
        
        findPath(maze, origin[0], origin[1], destination[0], destination[1], visited, currentPath, bestPath);
        return bestPath;
    }

    private void findPath(Maze maze, int r, int c, int destR, int destC, 
                          boolean[][] visited, List<int[]> currentPath, List<int[]> bestPath) {
        
        if (r < 0 || r >= maze.rows || c < 0 || c >= maze.cols) {
            return;
        }
        if (maze.data[r][c] == Maze.WALL || visited[r][c]) {
            return;
        }

        currentPath.add(new int[]{r, c});
        visited[r][c] = true;

        if (r == destR && c == destC) {
            if (bestPath.isEmpty() || currentPath.size() < bestPath.size()) {
                bestPath.clear();
                for (int[] cell : currentPath) {
                    bestPath.add(cell.clone());
                }
            }
        } else {
            int[][] directions = {
                {r - 1, c},
                {r + 1, c},
                {r, c - 1},
                {r, c + 1}
            };

            for (int[] dir : directions) {
                findPath(maze, dir[0], dir[1], destR, destC, visited, currentPath, bestPath);
            }
        }

        visited[r][c] = false;
        currentPath.remove(currentPath.size() - 1);
    }
}