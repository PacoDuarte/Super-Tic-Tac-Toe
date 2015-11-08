package com.developer.harrison.testproject;


import java.util.Random;

/**
 * Created by Developer on 11/19/2014.
 */
public class Game {
    private int playerType = 1;
    private int numberOfGames;
    private int gridOrCellSelection = 1;

    private int[] subOne = new int[9];
    private int[] subTwo = new int[9];
    private int[] subThree = new int[9];
    private int[] subFour = new int[9];
    private int[] subFive = new int[9];
    private int[] subSix = new int[9];
    private int[] subSeven = new int[9];
    private int[] subEight = new int[9];
    private int[] subNine = new int[9];

    private int[] winnerGrid = new int[9];

    private int[][] mainGrid = {
            subOne, subTwo, subThree,
            subFour, subFive, subSix,
            subSeven, subEight, subNine
    };

    private int currentSubGrid = 9999; //first move

    public int[][] populateMain(int[][] mainGrid) {
        for(int[] sub : mainGrid) {
            for(int cell : sub) {
                sub[cell] = 0;
            }
        }
        return mainGrid;
    }

    public int[] compTurn(int gridOrCellSelection) {
        Random Randomizer = new Random();
        int randInt = Randomizer.nextInt(9);
        int whereDoIPlay = 9999;
        int oldSubGrid = this.getCurrentSubGrid();
        int[] returnThis = new int[2];
        if(gridOrCellSelection == 2) {
            whereDoIPlay = compCanIWinSub(this.getMainGrid()[this.getCurrentSubGrid()]);
            if(whereDoIPlay != 9999) {
                this.getMainGrid()[this.getCurrentSubGrid()][whereDoIPlay] = 2;
                this.getMainGrid()[this.getCurrentSubGrid()] =
                        this.checkSubForWin(this.getMainGrid()[this.getCurrentSubGrid()]);
                this.setCurrentSubGrid(whereDoIPlay);
                if ((this.getMainGrid()[this.getCurrentSubGrid()][0] == 1111) ||
                        (this.getMainGrid()[this.getCurrentSubGrid()][0] == 2222))
                    this.setGridOrCellSelection(1);

                else
                    this.setGridOrCellSelection(2);
                returnThis[1] = whereDoIPlay;
            }
            else {
                whereDoIPlay = compSaveMySub(this.getMainGrid()[this.getCurrentSubGrid()]);
                if(whereDoIPlay != 9999) {
                    this.getMainGrid()[this.getCurrentSubGrid()][whereDoIPlay] = 2;
                    this.getMainGrid()[this.getCurrentSubGrid()] =
                            this.checkSubForWin(this.getMainGrid()[this.getCurrentSubGrid()]);
                    this.setCurrentSubGrid(whereDoIPlay);
                    if ((this.getMainGrid()[this.getCurrentSubGrid()][0] == 1111) ||
                            (this.getMainGrid()[this.getCurrentSubGrid()][0] == 2222))
                        this.setGridOrCellSelection(1);

                    else
                        this.setGridOrCellSelection(2);
                    returnThis[1] = whereDoIPlay;
                }
                else {
                    while(!this.isPlayableCell(this.getCurrentSubGrid(), randInt))
                        randInt = Randomizer.nextInt(9);
                    this.getMainGrid()[this.getCurrentSubGrid()][randInt] = 2;
                    this.getMainGrid()[this.getCurrentSubGrid()] =
                            this.checkSubForWin(this.getMainGrid()[this.getCurrentSubGrid()]);
                    this.setCurrentSubGrid(randInt);
                    if ((this.getMainGrid()[this.getCurrentSubGrid()][0] == 1111) ||
                            (this.getMainGrid()[this.getCurrentSubGrid()][0] == 2222))
                        this.setGridOrCellSelection(1);

                    else
                        this.setGridOrCellSelection(2);
                    returnThis[1] = randInt;
                }
            }
        }
        else {
            while(!this.isPlayableSubGrid(randInt))
                randInt = Randomizer.nextInt(9);
            this.setCurrentSubGrid(randInt);
            this.setGridOrCellSelection(2);
            return compTurn(this.getGridOrCellSelection());
        }
        returnThis[0] = oldSubGrid;
        return returnThis;
    }

    public int compCanIWinSub(int[] subGrid) {
        int x = 0;

        for(int i = 0; i < 3; i++) {
            if (subGrid[x] + subGrid[x + 1] + subGrid[x + 2] == 4) {
                if (subGrid[x] == 0)
                    return x;
                else if (subGrid[x + 1] == 0)
                    return x + 1;
                else if (subGrid[x + 2] == 0)
                    return x + 2;
            }
            x += 3;
        }
        x = 0;
        for(int i = 0; i < 3; i++) {
            if (subGrid[x] + subGrid[x + 3] + subGrid[x + 6] == 4) {
                if (subGrid[x] == 0)
                    return x;
                else if (subGrid[x + 3] == 0)
                    return x + 3;
                else if (subGrid[x + 6] == 0)
                    return x + 6;
            }
            x += 1;
        }
        if(subGrid[0] + subGrid[4] + subGrid[8] == 4) {
            if (subGrid[0] == 0)
                return 0;
            else if (subGrid[4] == 0)
                return 4;
            else if (subGrid[8] == 0)
                return 8;
        }
        else if(subGrid[6] + subGrid[4] + subGrid[2] == 4) {
            if(subGrid[6] == 0)
                return 6;
            else if (subGrid[4] == 0)
                return 4;
            else if (subGrid[2] == 0)
                return 2;
        }
        return 9999;
    }

    public int compSaveMySub(int[] subGrid) {
        int x = 0;

        for(int i = 0; i < 3; i++) {
            if ((subGrid[x] + subGrid[x + 1] + subGrid[x + 2] == 2) && (subGrid[x] != 2) && (subGrid[x + 1] != 2) && (subGrid[x + 2] != 2)) {
                if (subGrid[x] == 0)
                    return x;
                else if (subGrid[x + 1] == 0)
                    return x + 1;
                else if (subGrid[x + 2] == 0)
                    return x + 2;
            }
            x += 3;
        }
        x = 0;
        for(int i = 0; i < 3; i++) {
            if ((subGrid[x] + subGrid[x + 3] + subGrid[x + 6] == 2) && (subGrid[x] != 2) && (subGrid[x + 3] != 2) && (subGrid[x + 6] != 2)) {
                if (subGrid[x] == 0)
                    return x;
                else if (subGrid[x + 3] == 0)
                    return x + 3;
                else if (subGrid[x + 6] == 0)
                    return x + 6;
            }
            x += 1;
        }
        if((subGrid[0] + subGrid[4] + subGrid[8] == 2) && (subGrid[0] != 2) && (subGrid[4] != 2) && (subGrid[8] != 2)) {
            if (subGrid[0] == 0)
                return 0;
            else if (subGrid[4] == 0)
                return 4;
            else if (subGrid[8] == 0)
                return 8;
        }
        else if((subGrid[6] + subGrid[4] + subGrid[2] == 2) && (subGrid[6] != 2) && (subGrid[4] != 2) && (subGrid[2] != 2)) {
            if(subGrid[6] == 0)
                return 6;
            else if (subGrid[4] == 0)
                return 4;
            else if (subGrid[2] == 0)
                return 2;
        }
        return 9999;
    }

    public int[] checkSubForWin(int[] subGrid) {
        if( ((subGrid[0] == 1) && (subGrid[1] == 1) && (subGrid[2] == 1))||
            ((subGrid[3] == 1) && (subGrid[4] == 1) && (subGrid[5] == 1))||
            ((subGrid[6] == 1) && (subGrid[7] == 1) && (subGrid[8] == 1))) {
            subGrid[0] = 1111;
            return subGrid;
        }
        else if( ((subGrid[0] == 2) && (subGrid[1] == 2) && (subGrid[2] == 2))||
                 ((subGrid[3] == 2) && (subGrid[4] == 2) && (subGrid[5] == 2))||
                 ((subGrid[6] == 2) && (subGrid[7] == 2) && (subGrid[8] == 2))) {
            subGrid[0] = 2222;
            return subGrid;
        }
        else if( ((subGrid[0] == 1) && (subGrid[3] == 1) && (subGrid[6] == 1))||
                 ((subGrid[1] == 1) && (subGrid[4] == 1) && (subGrid[7] == 1))||
                 ((subGrid[2] == 1) && (subGrid[5] == 1) && (subGrid[8] == 1))) {
            subGrid[0] = 1111;
            return subGrid;
        }
        else if( ((subGrid[0] == 2) && (subGrid[3] == 2) && (subGrid[6] == 2))||
                 ((subGrid[1] == 2) && (subGrid[4] == 2) && (subGrid[7] == 2))||
                 ((subGrid[2] == 2) && (subGrid[5] == 2) && (subGrid[8] == 2))) {
            subGrid[0] = 2222;
            return subGrid;
        }
        else if( ((subGrid[0] == 1) && (subGrid[4] == 1) && (subGrid[8] == 1))||
                 ((subGrid[6] == 1) && (subGrid[4] == 1) && (subGrid[2] == 1))) {
            subGrid[0] = 1111;
            return subGrid;
        }
        else if( ((subGrid[0] == 2) && (subGrid[4] == 2) && (subGrid[8] == 2))||
                 ((subGrid[6] == 2) && (subGrid[4] == 2) && (subGrid[2] == 2))) {
            subGrid[0] = 2222;
            return subGrid;
        }
        return checkSubForTie(subGrid);
    }

    public int checkWinnerGridForWin(int[] winnerGrid) {
        if( ((winnerGrid[0] == 1) && (winnerGrid[1] == 1) && (winnerGrid[2] == 1))||
                ((winnerGrid[3] == 1) && (winnerGrid[4] == 1) && (winnerGrid[5] == 1))||
                ((winnerGrid[6] == 1) && (winnerGrid[7] == 1) && (winnerGrid[8] == 1))) {
            return 1111;
        }
        else if( ((winnerGrid[0] == 2) && (winnerGrid[1] == 2) && (winnerGrid[2] == 2))||
                ((winnerGrid[3] == 2) && (winnerGrid[4] == 2) && (winnerGrid[5] == 2))||
                ((winnerGrid[6] == 2) && (winnerGrid[7] == 2) && (winnerGrid[8] == 2))) {
            return 2222;
        }
        else if( ((winnerGrid[0] == 1) && (winnerGrid[3] == 1) && (winnerGrid[6] == 1))||
                ((winnerGrid[1] == 1) && (winnerGrid[4] == 1) && (winnerGrid[7] == 1))||
                ((winnerGrid[2] == 1) && (winnerGrid[5] == 1) && (winnerGrid[8] == 1))) {
            return 1111;
        }
        else if( ((winnerGrid[0] == 2) && (winnerGrid[3] == 2) && (winnerGrid[6] == 2))||
                ((winnerGrid[1] == 2) && (winnerGrid[4] == 2) && (winnerGrid[7] == 2))||
                ((winnerGrid[2] == 2) && (winnerGrid[5] == 2) && (winnerGrid[8] == 2))) {
            return 2222;
        }
        else if( ((winnerGrid[0] == 1) && (winnerGrid[4] == 1) && (winnerGrid[8] == 1))||
                ((winnerGrid[6] == 1) && (winnerGrid[4] == 1) && (winnerGrid[2] == 1))) {
            return 1111;
        }
        else if( ((winnerGrid[0] == 2) && (winnerGrid[4] == 2) && (winnerGrid[8] == 2))||
                ((winnerGrid[6] == 2) && (winnerGrid[4] == 2) && (winnerGrid[2] == 2))) {
            return 2222;
        }
        else
            return checkWinnerGridWithTies(winnerGrid);
    }

    public int checkWinnerGridWithTies(int[] winnerGrid) {
        int x = 0;
        for(int i = 0; i < 3; i++) {
            if (((winnerGrid[x] + winnerGrid[x + 1] + winnerGrid[x + 2] == 14) && (winnerGrid[x] != 2) && (winnerGrid[x + 1] != 2) && (winnerGrid[x + 2] != 2) && (winnerGrid[x] != 0) && (winnerGrid[x + 1] != 0) && (winnerGrid[x + 2] != 0)) || (winnerGrid[x] + winnerGrid[x + 1] + winnerGrid[x + 2] == 25))
                return 1111;
            x += 3;
        }
        x = 0;
        for(int i = 0; i < 3; i++) {
            if ((winnerGrid[x] + winnerGrid[x + 1] + winnerGrid[x + 2] == 16) || (winnerGrid[x] + winnerGrid[x + 1] + winnerGrid[x + 2] == 26))
                return 2222;
            x += 3;
        }
        x = 0;
        for(int i = 0; i < 3; i++) {
            if (((winnerGrid[x] + winnerGrid[x + 3] + winnerGrid[x + 6] == 14) && (winnerGrid[x] != 2) && (winnerGrid[x + 3] != 2) && (winnerGrid[x + 6] != 2) && (winnerGrid[x] != 0) && (winnerGrid[x + 1] != 0) && (winnerGrid[x + 0] != 2)) || (winnerGrid[x] + winnerGrid[x + 1] + winnerGrid[x + 2] == 25))
                return 1111;
            x++;
        }
        x = 0;
        for(int i = 0; i < 3; i++) {
            if ((winnerGrid[x] + winnerGrid[x + 3] + winnerGrid[x + 6] == 16) || (winnerGrid[x] + winnerGrid[x + 1] + winnerGrid[x + 2] == 26))
                return 2222;
            x++;
        }
        if (((winnerGrid[0] + winnerGrid[4] + winnerGrid[8] == 14) && (winnerGrid[0] != 2) && (winnerGrid[4] != 2) && (winnerGrid[8] != 2) && (winnerGrid[0] != 0) && (winnerGrid[4] != 0) && (winnerGrid[8] != 0)) || (winnerGrid[0] + winnerGrid[4] + winnerGrid[8] == 25))
            return 1111;
        else if (((winnerGrid[6] + winnerGrid[4] + winnerGrid[2] == 14) && (winnerGrid[6] != 2) && (winnerGrid[4] != 2) && (winnerGrid[2] != 2) && (winnerGrid[6] != 0) && (winnerGrid[4] != 0) && (winnerGrid[2] != 0)) || (winnerGrid[6] + winnerGrid[4] + winnerGrid[2] == 25))
            return 1111;
        else if ((winnerGrid[0] + winnerGrid[4] + winnerGrid[8] == 16) || (winnerGrid[0] + winnerGrid[4] + winnerGrid[8] == 26))
            return 2222;
        else if ((winnerGrid[6] + winnerGrid[4] + winnerGrid[2] == 16) || (winnerGrid[0] + winnerGrid[4] + winnerGrid[8] == 26))
            return 2222;
        return 9999;
        }

    public int[] checkSubForTie(int[] subGrid) {
        if (isFullSub(subGrid) && subGrid[0] != 1111 && subGrid[0] != 2222) {
            subGrid[0] = 3333;
            return subGrid;
        }
        return subGrid;
    }

    public boolean isFullSub(int[] subGrid) {
        for(int cell : subGrid) {
            if (cell == 0)
                return false;
        }
        return true;
    }

    public boolean isPlayableCell(int subGrid, int cell) {
        return (this.mainGrid[subGrid][cell] == 0);
    }

    public boolean isPlayableSubGrid(int subGrid) {
        return ((this.mainGrid[subGrid][0] != 1111) && (this.mainGrid[subGrid][0] != 2222));
    }

    public int[][] getMainGrid() {
        return mainGrid;
    }

    public int getGridOrCellSelection() {
        return gridOrCellSelection;
    }

    public int[] getWinnerGrid() { return winnerGrid; }

    public void setGridOrCellSelection(int gridOrCellSelection) {
        this.gridOrCellSelection = gridOrCellSelection;
    }

    public int getCurrentSubGrid() {
        return currentSubGrid;
    }

    public void setCurrentSubGrid(int currentSubGrid) {
        this.currentSubGrid = currentSubGrid;
    }


    @Override
    public String toString() {
        String gameString = "";
            for(int[] subGrid : this.getMainGrid()){
                for(int cell : subGrid){
                    gameString += cell;
                }
                gameString += "\n";
            }
        return gameString;
    }
}
