package sgh;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TicTacToe {

    public enum Result { NOT_FINISHED, NO_WINNER, X_WON, O_WON }

//    Checking rows
    public static int rows(int[][] gameBoard) {
        for (int sign : new int[]{-1, 1}) {
            for (int i = 0; i < 3; i++) {
                if (gameBoard[i][0] == sign && gameBoard[i][1] == sign && gameBoard[i][2] == sign) {
                    return sign;
                }
            }
        }
        return 0;
    }

//    Checking columns
    public static int columns(int[][] gameBoard) {
        for(int sign:new int[]{-1, 1}) {
            for(int i = 0; i < 3; i++) {
                if (gameBoard[0][i] == sign && gameBoard[1][i] == sign && gameBoard[2][i] == sign) {
                    return sign;
                }
            }
        }
        return 0;
    }

//    Checking diagonals
    public static int diagonals(int[][] gameBoard) {
        for(int sign:new int[]{-1, 1}) {
            if (gameBoard[0][0] == sign && gameBoard[1][1] == sign && gameBoard[0][2] == sign) {
                return sign;
            }
        }
        return 0;
    }


    public static Result checkBoard(String boardFileName) throws FileNotFoundException {
        File boardFile = new File(boardFileName);
        System.out.println(boardFile.getAbsolutePath());
        int[][] gameBoard = new int[3][3];
            Scanner reader = new Scanner(boardFile);
            int rowCount = 0;
            while (reader.hasNextLine()) {
                String[] data = reader.nextLine().split(";");
                int colCount = 0;
                for (String sign : data) {
                    if (sign.equals("x")) {
                        gameBoard[rowCount][colCount] = 1;
                        colCount++;
                    } else if (sign.equals("o")) {
                        gameBoard[rowCount][colCount] = -1;
                        colCount++;
                    } else {
                        gameBoard[rowCount][colCount] = 0;
                        colCount++;
                    }
                }
                rowCount++;
            }


        if (rows(gameBoard) == 1 || columns(gameBoard) == 1 || diagonals(gameBoard) == 1) {
            return Result.X_WON;
        } else if (rows(gameBoard) == -1 || columns(gameBoard) == -1 || diagonals(gameBoard) == -1) {
            return Result.O_WON;
        }

        for (int[] row : gameBoard){
            for (int mark:row){
                if(mark == 0){
                    return Result.NOT_FINISHED;
                }
            }
        }

        return Result.NO_WINNER;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Result res = checkBoard("boards/tick2.csv");
        System.out.println(res);
    }
}
