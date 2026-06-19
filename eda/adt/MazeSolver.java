package eda.adt;

import java.util.List;
import eda.ds.Maze;

public interface MazeSolver {
    List<int[]> solve(Maze maze, int[] origin, int[] destination);
}