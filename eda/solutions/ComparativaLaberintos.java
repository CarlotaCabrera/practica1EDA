package eda.solutions;

import eda.adt.MazeGenerator;
import eda.adt.MazeSolver;
import eda.ds.Maze;
import eda.ds.MazeGeneratorDFS;
import eda.ds.MazeSolverDC;
import eda.ds.MazeSolverDP;
import java.util.List;

public class ComparativaLaberintos {

    public static void main(String[] args) {
        MazeGenerator generator = new MazeGeneratorDFS();
        MazeSolver solverDC = new MazeSolverDC();
        MazeSolver solverDP = new MazeSolverDP();

        int[] dimensiones = {11, 21, 31, 41};

        System.out.println("Dimensión | Divide y vencerás | Programación dinámica");

        for (int dim : dimensiones) {
            Maze maze = generator.generate(dim, dim);
            int[] origin = {1, 1};
            int[] destination = {dim - 2, dim - 2};

            long startDC = System.nanoTime();
            List<int[]> pathDC = solverDC.solve(maze, origin, destination);
            long finishDC = System.nanoTime();
            double timeDC = (finishDC - startDC) / 1000000.0;

            long startDP = System.nanoTime();
            List<int[]> pathDP = solverDP.solve(maze, origin, destination);
            long finishDP = System.nanoTime();
            double timeDP = (finishDP - startDP) / 1000000.0;

            System.out.printf("%5dx%5d | %14.6f ms | %18.6f ms\n", dim, dim, timeDC, timeDP);
        }
    }
}