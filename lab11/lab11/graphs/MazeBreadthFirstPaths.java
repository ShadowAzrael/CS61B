package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int source;
    private int target;
    private boolean isFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        source = maze.xyTo1D(sourceX, sourceY);
        target = maze.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new Queue<Integer>();
        queue.enqueue(source);
        while (!queue.isEmpty()) {
            int frontier = queue.dequeue();
            marked[frontier] = true;
            announce();
            if (frontier == target) {
                isFound = true;
                break;
            }
            for (int w : maze.adj(frontier)) {
                if (!marked[w]) {
                    distTo[w] = distTo[frontier] + 1;
                    edgeTo[w] = frontier;
                    announce();
                    queue.enqueue(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

