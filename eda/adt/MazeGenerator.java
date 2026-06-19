package eda.adt;

import eda.ds.Maze;

public interface MazeGenerator {

    Maze generate(int rows, int cols);
}