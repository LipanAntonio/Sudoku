public class Main {

    private static final int SIZE = 9;

    public static void main(String[] args) {

        int [][] board = {
                {0, 0, 8, 0, 0, 0, 0, 1, 3},
                {0, 0, 3, 0, 6, 0, 8, 0, 9},
                {0, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 7, 0, 0, 0, 6, 0, 0},
                {0, 1, 0, 0, 0, 7, 0, 3, 4},
                {0, 6, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 6, 3, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 5, 9, 0},
                {1, 7, 0, 4, 5, 0, 0, 6, 0}
        };

        if(solve(board)){
            System.out.println("Solved!");
            printBoard(board);
        }else{
            System.out.println("Unsolvable");
        }
    }

    private static boolean rowOk(int[][] board, int number, int row){
        for(int i = 0; i < SIZE; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean columnOk(int[][] board, int number, int column){
        for(int i = 0; i < SIZE; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean gridOk(int[][] board, int number, int row, int column){
        int gridRow = row - row % 3;
        int gridColumn = column - column % 3;


        for(int i = gridRow; i < gridRow + 3; i++){
            for(int j = gridColumn; j < gridColumn + 3; j++){
                if (board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean placementOk(int[][] board, int number, int row, int column){
        return !rowOk(board, number, row) && !columnOk(board, number, column) && !gridOk(board, number, row, column);
    }

    private static boolean solve(int[][] board){
        for(int row = 0; row < SIZE; row++){
            for(int column = 0; column < SIZE; column++){
                if (board[row][column] == 0){
                    for (int currentNumber = 1; currentNumber <= SIZE; currentNumber++){
                        if (placementOk(board, currentNumber, row, column)){
                            board[row][column] = currentNumber;

                            if(solve(board)){
                                return true;
                            }else{
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static void printBoard(int[][] board){
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }



}