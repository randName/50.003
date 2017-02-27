package Week5;
import java.util.ArrayList;

public class MatrixThreadArray {
    private static int num_threads;

    public static void main (String[] args) {
        try {
            num_threads = new Integer(args[0]);
        } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
            System.out.println("Please enter the number of threads");
            return;
        }

        int[][] mat_a, mat_b;
        mat_a = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        mat_b = new int[][]{{2,1,1},{1,2,2},{3,2,1}};
                
        ArrayList<MatrixThread> t = new ArrayList<MatrixThread>();
        MatrixThread.setTotal(num_threads);
        MatrixThread.setInputs(mat_a, mat_b);

        for ( int i = 0; i < num_threads; i++ ) {
            t.add(new MatrixThread(i));
            t.get(i).start();
        }

        for ( int i = 0; i < num_threads; i++ ) {
            try {
                t.get(i).join();
            } catch (InterruptedException e) {
                System.out.println("Thread "+i+" didn't finish!");
            }
        }

        PrintMatrix(MatrixThread.getResult());
    }

    public static void PrintMatrix (int[][] toprint) {
        for (int i = 0; i < toprint.length; i++) {
            for (int j = 0; j < toprint[0].length; j++) {
                System.out.print(toprint[i][j] + "\t");
            }
            System.out.println();
        }
    }
}

class MatrixThread extends Thread {
    private static int[][] input_a, input_b, output;
    private static int total, msize;
    private int offset;
        
    public MatrixThread (int offset) {
        this.offset = offset;
    }

    public void run () {
        int i, j, k, row, col;
        for ( i = this.offset; i < msize*msize; i += total ) {
            row = i/msize;
            col = i%msize;
            output[row][col] = 0;
            for ( j = 0; j < msize; j++ ) {
                output[row][col] += input_a[row][j]*input_b[j][col];
            }

        }
    }

    public static void setInputs(int[][] mat_a, int[][] mat_b) {
        input_a = mat_a;
        input_b = mat_b;
        msize = mat_a.length;
        output = new int[msize][msize];
    }

    public static void setTotal(int t) {
        total = t;
    }

    public static int[][] getResult() {
        return output;
    }
}
