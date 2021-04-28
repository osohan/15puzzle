package com.puzzlegame;

public class Main {

    public static void main(String[] args) {
        PuzzleFrame puzzleFrame = PuzzleFrameFactory.getPuzzleFrame(PuzzleFrameType.PUZZLE_15,  new PuzzleFrameCreationNumberOfSteps(100));
        PuzzleView puzzleView = new PuzzleViewCli();
        PuzzleController puzzleController = new PuzzleController(puzzleView, puzzleFrame);
        CommunicationInterface communicationInterface = new CommunicationInterfaceCli();

        puzzleController.display();
        while (!puzzleController.isWin()) {
            Integer number = communicationInterface.getNextNumber();
            if (number != null)
                puzzleController.move(number);
        }
        puzzleController.youWin();

    }

}
