package com.puzzlegame;

import java.util.*;

public class PuzzleFrameCreationNumberOfSteps implements PuzzleFrameCreation {

    private int numberOfSteps;
    private Set<String> generated = new HashSet<>();

    public PuzzleFrameCreationNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    @Override
    public int[][] generateFrame(PuzzleFrameType puzzleFrameType) {
        Random random = new Random();

        int[][] frame = getEmptyFrame(puzzleFrameType);

        int maxVal = frame.length * frame[0].length;
        int count = 1;
        for (int row = 0; row < frame.length; row++) {
            for (int col = 0; col < frame[row].length; col++) {
                if (count == maxVal) {
                    frame[row][col] = 0;
                } else {
                    frame[row][col] = count++;
                }
            }
        }

        generated.add(stringRepresentationOfTheFrame(frame));

        int[] zeroPosition = {frame.length - 1, frame[0].length - 1};

        for (int k = 0; k < numberOfSteps; k++) {
            List<int[]> positions = getNeighbors(frame, zeroPosition);
            while (positions.size() > 0) {
                int[] neighborPosition = positions.get(random.nextInt(positions.size()));
                swap(frame, zeroPosition, neighborPosition);
                String str = stringRepresentationOfTheFrame(frame);
                if (!generated.contains(str)) {
                    zeroPosition = neighborPosition;
                    generated.add(str);
                    break;
                } else {
                    swap(frame, zeroPosition, neighborPosition);
                    positions.remove(neighborPosition);
                }
            }
        }


        return frame;
    }

    private String stringRepresentationOfTheFrame(int[][] frame) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[i].length; j++) {
                sb.append(frame[i][j]);
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private void swap(int[][] frame, int[] zeroPos, int[] neighborPos) {
        int tmp = frame[zeroPos[0]][zeroPos[1]];
        frame[zeroPos[0]][zeroPos[1]] = frame[neighborPos[0]][neighborPos[1]];
        frame[neighborPos[0]][neighborPos[1]] = tmp;
    }

    private List<int[]> getNeighbors(int[][] frame, int[] pos) {
        List<int[]> neighborsList = new LinkedList<>();
        int[][] neighbors = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] neighbor : neighbors) {
            int neighborRow = pos[0] + neighbor[0];
            int neighborCol = pos[1] + neighbor[1];
            if (insideBoundaries(frame, new int[]{neighborRow, neighborCol})) {
                neighborsList.add(new int[]{neighborRow, neighborCol});
            }
        }
        return neighborsList;
    }

    private boolean insideBoundaries(int[][] frame, int[] pos) {
        return  !(pos[0] < 0 || pos[0] >= frame.length || pos[1] < 0 || pos[1] >= frame[pos[0]].length);
    }

    private int[][] getEmptyFrame(PuzzleFrameType puzzleFrameType) {
        switch (puzzleFrameType) {
            case PUZZLE_15: return new int[4][4];
        }
        return null;
    }
}
