package com.puzzlegame;

import java.util.Arrays;

public class PuzzleViewCli implements PuzzleView {

    @Override
    public void display(PuzzleFrame puzzleFrame) {
        System.out.println();
        System.out.println("---- FRAME ----");
        for (int[] row : puzzleFrame.getFrame()) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    @Override
    public void youWin() {
        System.out.println();
        System.out.println("You win");
        System.out.println();
    }
}
