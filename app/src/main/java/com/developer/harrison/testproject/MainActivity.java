package com.developer.harrison.testproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends Activity {
    int currentSelection;
    Game game = new Game();
    int[] lastCPUMove = new int[2];
    int playerScore = 0;
    int compScore = 0;
    int firstPlayer;
    int howManyGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int[] populateHere;
        game.populateMain(game.getMainGrid());
        this.firstPlayer = Integer.parseInt(intent.getStringExtra("compFirst"));
        this.howManyGames = Integer.parseInt(intent.getStringExtra("gamesToPlay"));
        this.lastCPUMove[0] = 9999;
        if (firstPlayer == 1) {
            populateHere = this.game.compTurn(this.game.getGridOrCellSelection());
            populateGameSurface(populateHere[0], populateHere[1], 2);
            this.lastCPUMove = populateHere;
            highlightUnHighlightCell(lastCPUMove[0], lastCPUMove[1], 0);
            setTitle("Select a Cell");
        }
        else
            setTitle("Select a Grid");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void buttonListener(View v) {
        Button btn = (Button) v.findViewById(v.getId());
        Button button_submit = (Button) findViewById(R.id.button_submit);

        Button button_zero = (Button) findViewById(R.id.button_zero);
        Button button_one = (Button) findViewById(R.id.button_one);
        Button button_two = (Button) findViewById(R.id.button_two);
        Button button_three = (Button) findViewById(R.id.button_three);
        Button button_four = (Button) findViewById(R.id.button_four);
        Button button_five = (Button) findViewById(R.id.button_five);
        Button button_six = (Button) findViewById(R.id.button_six);
        Button button_seven = (Button) findViewById(R.id.button_seven);
        Button button_eight = (Button) findViewById(R.id.button_eight);

        resetButtons();
        if (this.game.getCurrentSubGrid() != 9999)
            displaySubOnButtons(this.game.getCurrentSubGrid());
        btn.setText(R.string.x_button);

        if (btn == button_zero)
            this.currentSelection = 0;
        else if (btn == button_one)
            this.currentSelection = 1;
        else if (btn == button_two)
            this.currentSelection = 2;
        else if (btn == button_three)
            this.currentSelection = 3;
        else if (btn == button_four)
            this.currentSelection = 4;
        else if (btn == button_five)
            this.currentSelection = 5;
        else if (btn == button_six)
            this.currentSelection = 6;
        else if (btn == button_seven)
            this.currentSelection = 7;
        else if (btn == button_eight)
            this.currentSelection = 8;
        else
            this.currentSelection = 9999;

        if ((this.game.getGridOrCellSelection() == 1) && (this.game.isPlayableSubGrid(this.currentSelection)))
            button_submit.setEnabled(true);
        else if ((this.game.getGridOrCellSelection() == 2) && (this.game.isPlayableCell(this.game.getCurrentSubGrid(), this.currentSelection)))
            button_submit.setEnabled(true);
        else
            button_submit.setEnabled(false);
    }

    public void submitListener(View v) {
        Switch highlight_switch = (Switch) findViewById(R.id.highlight_switch);

        int[] populateHere;


        if (this.game.getGridOrCellSelection() == 1) {
            this.game.setCurrentSubGrid(this.currentSelection);
            this.game.setGridOrCellSelection(2);
        } else {
            if (this.game.getGridOrCellSelection() == 2) {
                this.game.getMainGrid()[this.game.getCurrentSubGrid()][currentSelection] = 1;
                this.game.getMainGrid()[this.game.getCurrentSubGrid()] =
                        this.game.checkSubForWin(this.game.getMainGrid()[this.game.getCurrentSubGrid()]);
                populateGameSurface(this.game.getCurrentSubGrid(), currentSelection, 1);
                this.game.setCurrentSubGrid(this.currentSelection);
                if ((this.game.getMainGrid()[this.game.getCurrentSubGrid()][0] == 1111) ||
                        (this.game.getMainGrid()[this.game.getCurrentSubGrid()][0] == 2222) ||
                        (this.game.getMainGrid()[this.game.getCurrentSubGrid()][0] == 3333))
                    this.game.setGridOrCellSelection(1);

                else
                    this.game.setGridOrCellSelection(2);

                highlightUnHighlightCell(lastCPUMove[0], lastCPUMove[1], 1);
                populateHere = this.game.compTurn(this.game.getGridOrCellSelection());
                populateGameSurface(populateHere[0], populateHere[1], 2);
                this.lastCPUMove = populateHere;
            }
        }

        for (int i = 0; i < 9; i++) {
            if ((this.game.getMainGrid()[i][0] == 1111) || (this.game.getMainGrid()[i][0] == 2222) || this.game.getMainGrid()[i][0] == 3333) {
                populateWinningGrid(i);
                if (this.game.getMainGrid()[i][0] == 1111)
                    updateWinnerGrid(this.game.getWinnerGrid(), i, 1);
                else if (this.game.getMainGrid()[i][0] == 2222)
                    updateWinnerGrid(this.game.getWinnerGrid(), i, 2);
                else
                    updateWinnerGrid(this.game.getWinnerGrid(), i, 12);
            }
        }
        if (highlight_switch.isChecked())
            highlightUnHighlightCell(lastCPUMove[0], lastCPUMove[1], 0);
        if (this.game.getGridOrCellSelection() == 1) {
            setTitle("Select a Grid");
        } else {
            setTitle("Select a Cell");
        }


        if (this.game.checkWinnerGridForWin(this.game.getWinnerGrid()) == 1111)
            incrementScore(1111);
        else if (this.game.checkWinnerGridForWin(this.game.getWinnerGrid()) == 2222)
            incrementScore(2222);

        resetButtons();
        displaySubOnButtons(this.game.getCurrentSubGrid());
        disableSubmit();
    }

    public void resetButtons() {
        Button button_zero = (Button) findViewById(R.id.button_zero);
        Button button_one = (Button) findViewById(R.id.button_one);
        Button button_two = (Button) findViewById(R.id.button_two);
        Button button_three = (Button) findViewById(R.id.button_three);
        Button button_four = (Button) findViewById(R.id.button_four);
        Button button_five = (Button) findViewById(R.id.button_five);
        Button button_six = (Button) findViewById(R.id.button_six);
        Button button_seven = (Button) findViewById(R.id.button_seven);
        Button button_eight = (Button) findViewById(R.id.button_eight);
        button_zero.setText(R.string.blank_button);
        button_one.setText(R.string.blank_button);
        button_two.setText(R.string.blank_button);
        button_three.setText(R.string.blank_button);
        button_four.setText(R.string.blank_button);
        button_five.setText(R.string.blank_button);
        button_six.setText(R.string.blank_button);
        button_seven.setText(R.string.blank_button);
        button_eight.setText(R.string.blank_button);
    }

    public void disableSubmit() {
        Button button_submit = (Button) findViewById(R.id.button_submit);
        button_submit.setEnabled(false);
    }

    public void highlightUnHighlightCell(int subGrid, int cellNum, int un) {
        TextView cellToHighlight;
        String cellPrefix;
        String cell;
        int ID;

        if (subGrid > -1 && subGrid < 10) {
            if (subGrid == 0)
                cellPrefix = "z";
            else if (subGrid == 1)
                cellPrefix = "o";
            else if (subGrid == 2)
                cellPrefix = "t";
            else if (subGrid == 3)
                cellPrefix = "th";
            else if (subGrid == 4)
                cellPrefix = "f";
            else if (subGrid == 5)
                cellPrefix = "fi";
            else if (subGrid == 6)
                cellPrefix = "s";
            else if (subGrid == 7)
                cellPrefix = "se";
            else
                cellPrefix = "e";

            cell = cellPrefix + cellNum;
            ID = getResources().getIdentifier(cell, "id", "com.developer.harrison.testproject");
            cellToHighlight = (TextView) findViewById(ID);

            if (un != 0) {
                cellToHighlight.setBackgroundColor(0x000000000);
            } else
                cellToHighlight.setBackgroundColor(Color.parseColor("#F7FE2E"));
        }

    }

    public void populateGameSurface(int currentSubGrid, int selectedCell, int playerType) {
        TextView cellToUpdate;
        String cellPrefix;
        String cell;
        String symbol;
        int ID;

        if (playerType == 1)
            symbol = "X";
        else
            symbol = "O";

        if (currentSubGrid == 0)
            cellPrefix = "z";
        else if (currentSubGrid == 1)
            cellPrefix = "o";
        else if (currentSubGrid == 2)
            cellPrefix = "t";
        else if (currentSubGrid == 3)
            cellPrefix = "th";
        else if (currentSubGrid == 4)
            cellPrefix = "f";
        else if (currentSubGrid == 5)
            cellPrefix = "fi";
        else if (currentSubGrid == 6)
            cellPrefix = "s";
        else if (currentSubGrid == 7)
            cellPrefix = "se";
        else
            cellPrefix = "e";

        cell = cellPrefix + selectedCell;

        ID = getResources().getIdentifier(cell, "id", "com.developer.harrison.testproject");
        cellToUpdate = (TextView) findViewById(ID);
        cellToUpdate.setText(symbol);
    }

    public void populateWinningGrid(int winningGrid) {
        TextView cellToUpdate;
        String cellPrefix;
        String symbol;
        int ID;

        if (winningGrid == 0)
            cellPrefix = "z";
        else if (winningGrid == 1)
            cellPrefix = "o";
        else if (winningGrid == 2)
            cellPrefix = "t";
        else if (winningGrid == 3)
            cellPrefix = "th";
        else if (winningGrid == 4)
            cellPrefix = "f";
        else if (winningGrid == 5)
            cellPrefix = "fi";
        else if (winningGrid == 6)
            cellPrefix = "s";
        else if (winningGrid == 7)
            cellPrefix = "se";
        else
            cellPrefix = "e";

        if (this.game.getMainGrid()[winningGrid][0] == 1111) {
            symbol = "X";
            for (int i = 0; i < 9; i++) {
                ID = getResources().getIdentifier((cellPrefix + i), "id", "com.developer.harrison.testproject");
                cellToUpdate = (TextView) findViewById(ID);
                cellToUpdate.setText(symbol);
                cellToUpdate.setBackgroundColor(Color.parseColor("#2EFEF7"));
            }
        } else if (this.game.getMainGrid()[winningGrid][0] == 2222) {
            symbol = "O";
            for (int i = 0; i < 9; i++) {
                ID = getResources().getIdentifier((cellPrefix + i), "id", "com.developer.harrison.testproject");
                cellToUpdate = (TextView) findViewById(ID);
                cellToUpdate.setText(symbol);
                cellToUpdate.setBackgroundColor(Color.parseColor("#FE2E2E"));
            }
        } else {
            symbol = "N";
            for (int i = 0; i < 9; i++) {
                ID = getResources().getIdentifier((cellPrefix + i), "id", "com.developer.harrison.testproject");
                cellToUpdate = (TextView) findViewById(ID);
                cellToUpdate.setText(symbol);
                cellToUpdate.setBackgroundColor(Color.parseColor("#2EFE2E"));
            }
        }
    }

    public void updateWinnerGrid(int[] winnerGrid, int winningIndex, int whoWon) {
        winnerGrid[winningIndex] = (whoWon);
    }

    public void incrementScore(int winner) {
        TextView player_score = (TextView) findViewById(R.id.player_score);
        TextView comp_score = (TextView) findViewById(R.id.comp_score);
        if (winner == 1111) {
            this.playerScore++;
            player_score.setText("" + playerScore);
            new AlertDialog.Builder(this)
                    .setTitle("You've won!")
                    .setMessage("Nice work, you're smarter than an inanimate object!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            resetBoard();
                            resetButtons();
                        }
                    })
                    .show();
        } else {
            this.compScore++;
            comp_score.setText("" + compScore);
            new AlertDialog.Builder(this)
                    .setTitle("You've lost!")
                    .setMessage("Nice work, you've lost to an inanimate object!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            resetBoard();
                            resetButtons();
                        }
                    })
                    .show();
        }
    }

    public void displaySubOnButtons(int currentSubGrid) {
        Button[] buttons = new Button[9];

        Button button_zero = (Button) findViewById(R.id.button_zero);
        Button button_one = (Button) findViewById(R.id.button_one);
        Button button_two = (Button) findViewById(R.id.button_two);
        Button button_three = (Button) findViewById(R.id.button_three);
        Button button_four = (Button) findViewById(R.id.button_four);
        Button button_five = (Button) findViewById(R.id.button_five);
        Button button_six = (Button) findViewById(R.id.button_six);
        Button button_seven = (Button) findViewById(R.id.button_seven);
        Button button_eight = (Button) findViewById(R.id.button_eight);

        buttons[0] = button_zero;
        buttons[1] = button_one;
        buttons[2] = button_two;
        buttons[3] = button_three;
        buttons[4] = button_four;
        buttons[5] = button_five;
        buttons[6] = button_six;
        buttons[7] = button_seven;
        buttons[8] = button_eight;

        if (this.game.getGridOrCellSelection() == 2) {
            for (int i = 0; i < 9; i++) {
                if (this.game.getMainGrid()[currentSubGrid][i] == 1) {
                    buttons[i].setText(R.string.x_button);
                } else if (this.game.getMainGrid()[currentSubGrid][i] == 2) {
                    buttons[i].setText(R.string.o_button);
                }
            }
        }
    }

    public void resetBoard() {
        TextView cellToUpdate;
        String cellPrefix;
        int[] populateHere;
        int ID;



        if (this.playerScore == this.howManyGames && this.howManyGames != 0) {
            new AlertDialog.Builder(this)
                    .setTitle("That's that!")
                    .setMessage("You win, nice work!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        } else if (this.compScore == this.howManyGames && this.howManyGames != 0) {
            new AlertDialog.Builder(this)
                    .setTitle("That's that!")
                    .setMessage("You lost, how sad!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
         else {
            for (int i = 0; i < 9; i++) {
                if (i == 0)
                    cellPrefix = "z";
                else if (i == 1)
                    cellPrefix = "o";
                else if (i == 2)
                    cellPrefix = "t";
                else if (i == 3)
                    cellPrefix = "th";
                else if (i == 4)
                    cellPrefix = "f";
                else if (i == 5)
                    cellPrefix = "fi";
                else if (i == 6)
                    cellPrefix = "s";
                else if (i == 7)
                    cellPrefix = "se";
                else
                    cellPrefix = "e";

                for (int j = 0; j < 9; j++) {
                    ID = getResources().getIdentifier((cellPrefix + j), "id", "com.developer.harrison.testproject");
                    cellToUpdate = (TextView) findViewById(ID);
                    cellToUpdate.setText(R.string.blank_button);
                    cellToUpdate.setBackgroundColor(0x0000000);
                }
            }
            this.game = new Game();
            this.game.populateMain(this.game.getMainGrid());
            this.lastCPUMove[0] = 9999;
            this.game.setGridOrCellSelection(1);

            if (this.firstPlayer == 2) {
                populateHere = this.game.compTurn(this.game.getGridOrCellSelection());
                populateGameSurface(populateHere[0], populateHere[1], 2);
                this.lastCPUMove = populateHere;
                highlightUnHighlightCell(lastCPUMove[0], lastCPUMove[1], 0);
                setTitle("Select a Cell");
            }
        }
    }
}