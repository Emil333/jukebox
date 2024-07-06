package com.innoventes.jukebox.test.dynamicprogramming.robotinagrid;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Imagine a robot sitting in the upper left corner of grid with r rows and c columns.
 * The robot can only move in two directions, right and down, but certain cells are "off limits"
 * such that the robot cannot step on them. Design an algorithm to find a path for the robot
 * from the top left to the bottom right
 */
public class RobotInAGrid {


    public static void main(String[] args) {

        boolean[][] maze = new boolean[5][5];
        Arrays.fill(maze[0], Boolean.TRUE);
        Arrays.fill(maze[1], Boolean.TRUE);
        Arrays.fill(maze[2], Boolean.TRUE);
        Arrays.fill(maze[3], Boolean.TRUE);
        Arrays.fill(maze[4], Boolean.TRUE);
        ArrayList<Point> paths = getPath(maze);
        System.out.println(paths);
    }

    private static ArrayList<Point> getPath(boolean[][] maze){
        if (maze == null || maze.length == 0) return null;

        ArrayList<Point> path = new ArrayList<>();
        HashMap<Point, Boolean> cache = new HashMap<>();
        int lastRow = maze.length - 1;
        int lastCol = maze[0].length - 1;
        if (getPath(maze, lastRow, lastCol, path, cache))
            return path;

        return null;
    }

    private static Boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path,
                                   HashMap<Point, Boolean> cache){

        if (col < 0 || row < 0 || !maze[row][col])return false;

        Point p = new Point(row, col);

        if (cache.containsKey(p))
            return cache.get(p);
        boolean isAtOrigin = (row == 0) && (col == 0);
        boolean success = false;

        if (isAtOrigin || getPath(maze, row, col - 1, path, cache)
        || getPath(maze, row - 1, col, path, cache)){
            path.add(p);
            success = true;
        }

        cache.put(p, success);
        return success;
    }
}
