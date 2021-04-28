package com.puzzlegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PuzzleFrameCreationBase implements PuzzleFrameCreation {

    public int[][] generateFrame(PuzzleFrameType puzzleFrameType) {
        switch (puzzleFrameType) {
            case PUZZLE_15: return generate(4, 4);
        }
        return null;
    }

    private int[][] generate(int height, int width) {
        Random random = new Random();
        List<Integer> possibleNumbers = new ArrayList<>();

        int totalNumber = height * width;
        for (int i = 0; i < totalNumber; i++) {
            possibleNumbers.add(i);
        }

        int row = 0;
        int col = 0;
        int[][] frame = new int[height][width];
        while (!possibleNumbers.isEmpty()) {
            Integer number = possibleNumbers.get(random.nextInt(possibleNumbers.size()));
            frame[row][col] = number;
            if (row + 1 < frame.length) {
                row++;
            }  else {
                row = 0;
                col++;
            }
            possibleNumbers.remove(number);
        }

        return frame;
    }

}
