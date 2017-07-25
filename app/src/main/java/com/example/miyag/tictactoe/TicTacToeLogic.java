package com.example.miyag.tictactoe;

/**
 * Created by miyag on 7/24/17.
 */

public class TicTacToeLogic {


    public enum TTTElement {EMPTY, O, X;}

    // return the index (0-8) of the best move for the current game state. return -1 if no move available.
    public static int getBestMove(TTTElement[] gameState) {
        if (isGameOver(gameState)) {
            return -1; // game has been
            // on or no valid move
        }
        int bestScore = -1000;
        int bestIndex = -1;
        for (int i = 0; i < 9; i++) {
            if (gameState[i] == TTTElement.EMPTY) {
                gameState[i] = TTTElement.O; // set to computer
                int score = minimax(gameState, false); // start minimax for at human turn, minimizing the score.
                if (score > bestScore) {
                    bestScore = score;
                    bestIndex = i;
                }
                gameState[i] = TTTElement.EMPTY;
            }
        }
        return bestIndex;
    }

    public static boolean isGameOver(TTTElement[] gameState) {
        // someone has won or no moves remaining
        return (evaluate(gameState) != 0) || !hasMoves(gameState);
    }

    private static int minimax(TTTElement[] gameState, boolean isMaximizer) {
        int score = evaluate(gameState);
        if (score == 10 || score == -10) return score;
        if (!hasMoves(gameState)) return 0;

        if (isMaximizer) { //find best move to maximize for comp player
            int bestScore = -1000;
            for (int i = 0; i < 9; i++) {
                if (gameState[i] == TTTElement.EMPTY) {
                    gameState[i] = TTTElement.O;
                    score = minimax(gameState, !isMaximizer);
                    if (score > bestScore) {
                        bestScore = score;
                    }
                    gameState[i] = TTTElement.EMPTY;
                }
            }
            return bestScore;
        } else { // minimize
            int bestScore = 1000;
            for (int i = 0; i < 9; i++) {
                if (gameState[i] == TTTElement.EMPTY) {
                    gameState[i] = TTTElement.X;
                    score = minimax(gameState, !isMaximizer);
                    if (score < bestScore) {
                        bestScore = score;
                    }
                    gameState[i] = TTTElement.EMPTY;
                }
            }
            return bestScore;
        }
    }

    private static boolean hasMoves(TTTElement[] gameState) {
        for (int i = 0; i < 9; i++) {
            if (gameState[i] == TTTElement.EMPTY) return true;
        }
        return false;
    }

    private static int evaluate(TTTElement[] gameState) {
        // The index sets of the 8 possible winning configurations.
        int[][] lineIndices =
                {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for (int i = 0; i < 8; i++) {

            if (gameState[lineIndices[i][0]] == TTTElement.X &&
                    gameState[lineIndices[i][1]] == TTTElement.X &&
                    gameState[lineIndices[i][2]] == TTTElement.X) { // check for human win.
                return -10; // which is bad
            } else if (gameState[lineIndices[i][0]] == TTTElement.O &&
                    gameState[lineIndices[i][1]] == TTTElement.O &&
                    gameState[lineIndices[i][2]] == TTTElement.O) { // check for comp win.
                return 10; // which is good
            }
        }
        return 0; // no winner for this state
    }


}