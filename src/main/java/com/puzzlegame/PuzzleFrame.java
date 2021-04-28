package com.puzzlegame;

public abstract class PuzzleFrame {

    private int[][] frame;

    public PuzzleFrame(int[][] frame) {
        this.frame = frame;
    }

    public int[][] getFrame() {
        return this.frame;
    }

    public boolean move(int number) {
        int[] pos = getPosition(number);
        if (pos != null) {
            int[][] neighbors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] neighbor : neighbors) {
                int neighborRow = pos[0] + neighbor[0];
                int neighborCol = pos[1] + neighbor[1];
                if (insideBoundaries(new int[]{neighborRow, neighborCol}) && frame[neighborRow][neighborCol] == 0) {
                    frame[neighborRow][neighborCol] = number;
                    frame[pos[0]][pos[1]] = 0;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isWin() {
        int count = 1;
        int lastNumber = frame.length * frame[0].length;
        for (int row = 0; row < frame.length; row++) {
            for (int col = 0; col < frame[row].length; col++) {
                if (count == lastNumber && row == frame.length - 1 && col == frame[row].length - 1) return true;
                if (frame[row][col] != count) return false;
                count++;
            }
        }
        return false;
    }

    private boolean insideBoundaries(int[] pos) {
        return  !(pos[0] < 0 || pos[0] >= frame.length || pos[1] < 0 || pos[1] >= frame[pos[0]].length);
    }

    private int[] getPosition(int number) {
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[i].length; j++) {
                if (frame[i][j] == number) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

}
