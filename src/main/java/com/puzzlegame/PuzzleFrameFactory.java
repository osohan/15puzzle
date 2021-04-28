package com.puzzlegame;

public class PuzzleFrameFactory {

    public static PuzzleFrame getPuzzleFrame(PuzzleFrameType puzzleFrameType, PuzzleFrameCreation puzzleFrameCreation) {
        switch (puzzleFrameType) {
            case PUZZLE_15: return new PuzzleFrame15(puzzleFrameCreation);
        }
        return null;
    }

}
