package com.puzzlegame;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PuzzleGameTest {

    @Test
    public void puzzleFrameCreationNumberOfStepsTest() {
        PuzzleFrame puzzleFrame = PuzzleFrameFactory.getPuzzleFrame(PuzzleFrameType.PUZZLE_15, new PuzzleFrameCreationNumberOfSteps(2));
        assertTrue(checkSolvable(puzzleFrame.getFrame()));
    }

    private boolean checkSolvable(int[][] f){
        int index = 0;
        int[] frame = new int[f.length * f[0].length];
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                frame[index++] = f[i][j];
            }
        }

        int parity = 0;
        int gridWidth = (int) Math.sqrt(frame.length);
        int row = 0;
        int blankRow = 0;

        for (int i = 0; i < frame.length; i++) {
            if (i % gridWidth == 0) {
                row++;
            }
            if (frame[i] == 0) {
                blankRow = row;
                continue;
            }
            for (int j = i + 1; j < frame.length; j++) {
                if (frame[i] > frame[j] && frame[j] != 0) {
                    parity++;
                }
            }
        }

        if (gridWidth % 2 == 0) {
            if (blankRow % 2 == 0) {
                return parity % 2 == 0;
            } else {
                return parity % 2 != 0;
            }
        } else {
            return parity % 2 == 0;
        }
    }

    @Test
    public void creationTest() {
        PuzzleFrame puzzleFrame = PuzzleFrameFactory.getPuzzleFrame(PuzzleFrameType.PUZZLE_15, new PuzzleFrameCreationBase());
        assertTrue(puzzleFrame.getFrame().length == 4 && puzzleFrame.getFrame()[0].length == 4);

        int[][] puzzleFrameLink = puzzleFrame.getFrame();

        int maxVal = puzzleFrameLink.length * puzzleFrameLink[0].length;
        int count = 1;
        for (int row = 0; row < puzzleFrameLink.length; row++) {
            for (int col = 0; col < puzzleFrameLink[row].length; col++) {
                if (count == maxVal) {
                    puzzleFrameLink[row][col] = 0;
                } else {
                    puzzleFrameLink[row][col] = count++;
                }
            }
        }

        assertTrue(puzzleFrame.isWin());
        puzzleFrame.move(15);
        assertFalse(puzzleFrame.isWin());
    }

    @Test
    public void frameGenerationAlgorithmForPuzzle15Test() {
        int[][] frame = new PuzzleFrameCreationBase().generateFrame(PuzzleFrameType.PUZZLE_15);
        assertTrue(frame.length == 4 && frame[0].length == 4);

        Set<Integer> s = new HashSet<>();
        for (int row = 0; row < frame.length; row++) {
            for (int col = 0; col < frame[row].length; col++) {
                s.add(frame[row][col]);
            }
        }

        for (int i = 0; i < 15; i++) {
            assertTrue(s.contains(i));
        }

    }

}
