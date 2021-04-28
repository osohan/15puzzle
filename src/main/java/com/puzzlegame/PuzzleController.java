package com.puzzlegame;

public class PuzzleController {

    private PuzzleView puzzleView;
    private PuzzleFrame puzzleFrame;

    public PuzzleController(PuzzleView puzzleView, PuzzleFrame puzzleFrame) {
        this.puzzleView = puzzleView;
        this.puzzleFrame = puzzleFrame;
    }

    public void move(int number) {
        if (puzzleFrame.move(number)) {
            puzzleView.display(puzzleFrame);
        }
    }

    public void youWin() {
        puzzleView.youWin();
    }

    public boolean isWin() {
        return puzzleFrame.isWin();
    }

    public void display() {
        puzzleView.display(puzzleFrame);
    }

}
