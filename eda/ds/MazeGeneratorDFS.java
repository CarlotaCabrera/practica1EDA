package eda.ds;

import eda.adt.MazeGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MazeGeneratorDFS implements MazeGenerator {

    @Override
    public Maze generate(int rows, int cols) {
        Maze maze = new Maze(rows, cols);
        boolean[][] visited = new boolean[rows][cols];
        
        int startRow = 1;
        int startCol = 1;
        
        dfs(maze, startRow, startCol, visited);
        
        maze.data[0][1] = Maze.EMPTY;
        maze.data[rows - 1][cols - 2] = Maze.EMPTY;
        
        return maze;
    }

    private void dfs(Maze maze, int r, int c, boolean[][] visited) {
        visited[r][c] = true;
        maze.data[r][c] = Maze.EMPTY;

        int[][] neighbors = {
            {r - 2, c, r - 1, c},
            {r + 2, c, r + 1, c},
            {r, c - 2, r, c - 1},
            {r, c + 2, r, c + 1}
        };

        List<int[]> order = new ArrayList<>();
        for (int[] n : neighbors) {
            order.add(n);
        }
        Collections.shuffle(order);

        for (int[] next : order) {
            int nRow = next[0];
            int nCol = next[1];
            int pRow = next[2];
            int pCol = next[3];

            if (nRow > 0 && nRow < maze.rows - 1 && nCol > 0 && nCol < maze.cols - 1) {
                if (!visited[nRow][nCol]) {
                    maze.data[pRow][pCol] = Maze.EMPTY;
                    dfs(maze, nRow, nCol, visited);
                }
            }
        }
    }
}