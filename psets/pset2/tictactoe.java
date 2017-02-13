class tictactoe {

    private static char[][] board = new char[3][3];
    private static boolean player = false;
    private static int moves = 0;

    public static void main(String[] args) {
        System.out.println("Tic Tac Toe");
        initBoard();
        while ( moves < 9 ) {
            showBoard();
            updateBoard(readInput());
            if ( gameEnd() ){
                System.out.println("You won!");
                break;
            }
        }
        System.out.println("Game Over");
        showBoard();
    }

    private static int readInput() {
        int n = 10;
        do {
            System.out.print("Please enter your move (1-9): ");
            String input = System.console().readLine();
            try {
                n = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            }
            if ( n < 1 || n > 9 ) {
                n = 10;
                System.out.println("Sorry, that wasn't a valid move");
            }
        } while ( n > 9 );
        return n-1;
    }

    private static void initBoard() {
        for ( int i=0; i<9; i++ ){board[i/3][i%3]=Character.forDigit(i+1,10);}
    }

    private static void showBoard() {
        System.out.println();
        for ( int i=0; i<3; i++ ){
            for ( int j=0; j<3; j++ ){
                System.out.print(board[i][j]);
                if (j<2){ System.out.print("|"); }
            }
            System.out.println();
            if (i<2){ System.out.println("-----"); }
        }
        System.out.println();
    }

    private static void updateBoard(int n) {
        if ( board[n/3][n%3] == 'X' || board[n/3][n%3] == 'O' ){
            System.out.println("Sorry, that move was taken");
        } else {
            board[n/3][n%3] = player ? 'X' : 'O';
            player = !player;
            moves++;
        }
    }

    private static boolean checkRow(int r) {
        return board[r][0] == board[r][1] && board[r][1] == board[r][2];
    }
    
    private static boolean checkCol(int c) {
        return board[1][c] == board[1][c] && board[1][c] == board[2][c];
    }

    private static boolean checkDia(int d) {
        if (d == 1) return false;
        return board[0][d] == board[1][1] && board[1][1] == board[2][2-d];
    }

    private static boolean gameEnd() {
        for ( int i=0; i<3; i++ ){
            if (checkRow(i) || checkCol(i) || checkDia(i)) return true;
        }
        return false;
    }
}
