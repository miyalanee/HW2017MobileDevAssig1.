package com.example.miyag.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[] mButtons_var = new Button[10];
    private TicTacToeLogic.TTTElement[] mBoardStatus = new TicTacToeLogic.TTTElement[9];
    private Button mTemporary_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    public void onClick(View v) {

        if (v.getId() == mButtons_var[9].getId()) {  // if "New game" is clicked
            mButtons_var[9].setVisibility(View.INVISIBLE);
            activate_buttons();
            reset_board();
        }

        else { // if a tic-tac-toe box is clicked
            mTemporary_Button = (Button)findViewById(v.getId());

            if (mTemporary_Button.getText() == "") { // set text to "x" and update board if text is empty
                mTemporary_Button.setText("X");
                update_board_status(mTemporary_Button, TicTacToeLogic.TTTElement.X);

                if (TicTacToeLogic.isGameOver(mBoardStatus)) { // check if game has been won
                    if (mButtons_var[9].getVisibility() == View.INVISIBLE) {
                        mButtons_var[9].setVisibility(View.VISIBLE);
                        mButtons_var[9].setText("New Game");
                        deactivate_buttons();
                    }
                }

                else {
                    int best_position_index = TicTacToeLogic.getBestMove(mBoardStatus);
                    mButtons_var[best_position_index].setText("O");
                    update_board_status(mButtons_var[best_position_index], TicTacToeLogic.TTTElement.O);

                    if (TicTacToeLogic.isGameOver(mBoardStatus)) {
                        if (mButtons_var[9].getVisibility() == View.INVISIBLE) {
                            mButtons_var[9].setVisibility(View.VISIBLE);
                            mButtons_var[9].setText("New Game");
                            deactivate_buttons();
                        }
                    }
                }
            }
        }
    }

    public void update_board_status(Button b, TicTacToeLogic.TTTElement type){
        for (int i = 0; i < mButtons_var.length; i++){
            if (mButtons_var[i].getId() == b.getId()){
                mBoardStatus[i] = type;
                break;
            }
        }
    }

    public void reset_board(){
        for(int i = 0; i < mButtons_var.length; i++){
            mButtons_var[i].setText("");
        }
        for(int i = 0; i < mBoardStatus.length; i++){
            mBoardStatus[i] = TicTacToeLogic.TTTElement.EMPTY;
        }
    }

    public void deactivate_buttons(){
        for (int i = 0; i < mButtons_var.length -1; i++){
            mButtons_var[i].setEnabled(false);
        }
    }

    public void activate_buttons(){
        for (int i = 0; i < mButtons_var.length -1; i++){
            mButtons_var[i].setEnabled(true);
        }
    }

    public void initialize() {
        mButtons_var[0] = (Button) findViewById(R.id.but1);
        mButtons_var[1] = (Button) findViewById(R.id.but2);
        mButtons_var[2] = (Button) findViewById(R.id.but3);
        mButtons_var[3] = (Button) findViewById(R.id.but4);
        mButtons_var[4] = (Button) findViewById(R.id.but5);
        mButtons_var[5] = (Button) findViewById(R.id.but6);
        mButtons_var[6] = (Button) findViewById(R.id.but7);
        mButtons_var[7] = (Button) findViewById(R.id.but8);
        mButtons_var[8] = (Button) findViewById(R.id.but9);
        mButtons_var[9] = (Button) findViewById(R.id.but10);

        for (int i = 0; i < mButtons_var.length; i++) {
            mButtons_var[i].setOnClickListener(MainActivity.this);
        }

        for (int i = 0; i < mBoardStatus.length; i++) {
            mBoardStatus[i] = TicTacToeLogic.TTTElement.EMPTY;
        }
    }

}