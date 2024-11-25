package org.example;

import javax.swing.*;
import java.awt.*;

public class GameOfLifePanel extends JPanel {
    private final int rows;
    private final int cols;
    private final int cellSize;
    private int[][] grid; // 2D array representing the grid state (1 = alive, 0 = dead)

    public GameOfLifePanel(int rows, int cols, int cellSize) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.grid = new int[rows][cols]; // Initialize the grid with 0s (dead cells)
    }

    // Update the grid state and repaint
    public void setGrid(int[][] newGrid) {
        this.grid = newGrid;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the grid
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Calculate cell position
                int x = col * cellSize;
                int y = row * cellSize;

                // Draw cell background (1 = alive = black, 0 = dead = white)
                if (grid[row][col] == 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, cellSize, cellSize);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, cellSize, cellSize);
                }

                // Draw cell border
                g.setColor(Color.GRAY);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(cols * cellSize, rows * cellSize);
    }
}