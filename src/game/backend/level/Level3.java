package game.backend.level;

import game.backend.GameState;
import game.backend.Grid;
import game.backend.cell.CandyGeneratorCell;
import game.backend.cell.Cell;
import game.backend.element.Gap;
import game.backend.element.Wall;

public class Level3 extends Grid {

    private static int REQUIRED_SCORE = 5000;
    private static int MAX_MOVES = 20;
    private static int INITIAL_JELLY_COUNT = 25;

    private Cell wallCell;
    private Cell gapCell;
    private Cell candyGenCell;

    @Override
    protected GameState newState() {
        return new Level3.Level3State(REQUIRED_SCORE, MAX_MOVES, INITIAL_JELLY_COUNT);
    }

    @Override
    protected void fillCells() {

        wallCell = new Cell(this);
        wallCell.setContent(new Wall());
        gapCell = new Cell(this);
        gapCell.setContent(new Gap());
        candyGenCell = new CandyGeneratorCell(this);

        //corners
        g()[0][0].setAround(candyGenCell, g()[1][0], wallCell, g()[0][1]);
        g()[0][SIZE-1].setAround(candyGenCell, g()[1][SIZE-1], g()[0][SIZE-2], wallCell);
        g()[SIZE-1][0].setAround(g()[SIZE-2][0], wallCell, wallCell, g()[SIZE-1][1]);
        g()[SIZE-1][SIZE-1].setAround(g()[SIZE-2][SIZE-1], wallCell, g()[SIZE-1][SIZE-2], wallCell);

        //central cells jelly
        for (int i = 2; i < SIZE-2; i++) {
            for (int j = 2; j < SIZE-2; j++) {
                g()[i][j].shiftJelly();
            }
        }
        //upper line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[0][j].setAround(candyGenCell,g()[1][j],g()[0][j-1],g()[0][j+1]);
        }
        //bottom line cells
        for (int j = 1; j < SIZE-1; j++) {
            g()[SIZE-1][j].setAround(g()[SIZE-2][j], wallCell, g()[SIZE-1][j-1],g()[SIZE-1][j+1]);
        }
        //left line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][0].setAround(g()[i-1][0],g()[i+1][0], wallCell ,g()[i][1]);
        }
        //right line cells
        for (int i = 1; i < SIZE-1; i++) {
            g()[i][SIZE-1].setAround(g()[i-1][SIZE-1],g()[i+1][SIZE-1], g()[i][SIZE-2], wallCell);
        }
        //central cells
        for (int i = 1; i < SIZE-1; i++) {
            for (int j = 1; j < SIZE-1; j++) {
                g()[i][j].setAround(g()[i - 1][j], g()[i + 1][j], g()[i][j - 1], g()[i][j + 1]);
            }
        }
    }

    @Override
    public boolean tryMove(int i1, int j1, int i2, int j2) {
        boolean ret;
        if (ret = super.tryMove(i1, j1, i2, j2)) {
            state().addMove();
        }
        return ret;
    }

    private class Level3State extends GameState {
        private long requiredScore;
        private long maxMoves;

        public Level3State(long requiredScore, int maxMoves, int jellyCount) {
            this.requiredScore = requiredScore;
            this.maxMoves = maxMoves;
            this.jellyCount = jellyCount;
        }

        public boolean gameOver() {
            return playerWon() || getMoves() >= maxMoves;
        }

        public boolean playerWon() {
            return super.getScore() > requiredScore && jellyCount == 0;
        }

        @Override
        public String getStatus(){
            return super.getStatus() + "\tJellyCount: " + jellyCount;
        }
    }
}