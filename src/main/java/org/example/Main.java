package org.example;
import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // Grid dimensions
        int rows = 20;
        int cols = 20;
        int cellSize = 20;

        // Create the GameOfLifePanel
        GameOfLifePanel panel = new GameOfLifePanel(rows, cols, cellSize);

        // Example: Initialize some alive cells
        int[][] grid = new int[rows][cols];

        grid[10][11] = 1;
        grid[10][12] = 1;
        grid[10][13] = 1;
        grid[9][12] = 1;
        grid[11][12] = 1;
        panel.setGrid(grid);

        // Set up the JFrame
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Timer(200, e -> {
            changeGrid(grid);
            panel.setGrid(grid);
        }).start();
    }

    public static void changeGrid(int[][] grid) {
        int[][] temp_grid = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                int[][] gridToCheck = getSurroundingGrid(grid, i, j);
                int count = checkAround(gridToCheck);
                if (grid[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        temp_grid[i][j] = 0;
                    } else {
                        temp_grid[i][j] = 1;
                    }
                } else {
                    if (count == 3) {
                        temp_grid[i][j] = 1;
                    }
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            System.arraycopy(temp_grid[i], 0, grid[i], 0, grid[i].length);
        }
    }

    public static int[][] getSurroundingGrid(int[][] grid, int row, int col) {
        int[][] surroundingGrid = new int[3][3]; // 3x3 grid

        // Loop through the surrounding cells, handling edge cases
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                // Check if the indices are within bounds
                if (row + i >= 0 && row + i < grid.length && col + j >= 0 && col + j < grid[row].length) {
                    surroundingGrid[i + 1][j + 1] = grid[row + i][col + j]; // Copy value to the 3x3 grid
                }
            }
        }
        return surroundingGrid; // Return the 3x3 grid
    }

    public static int checkAround(int[][] grid){
        int count = 0;
        for (int[] ints : grid) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    count++;
                }
            }
        }

        if (grid[1][1] == 1){
            count--;
        }
        return count;
    }
}