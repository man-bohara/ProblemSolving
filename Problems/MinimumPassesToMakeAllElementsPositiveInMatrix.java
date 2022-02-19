import java.util.LinkedList;
import java.util.Queue;

/*
The problem is to convert all the negative numbers to positive in a matrix in minimum passes.
A number can be converted to positive if one or more of it's adjacent(left, right, up and down) elements is positive.
0 is neither positive nor negative.
* */
public class MinimumPassesToMakeAllElementsPositiveInMatrix {

    public static void main(String[] args) {
           int[][] matrix = {
                   {0, -2, -1, -2},
                   {-5, 2, 0, 3},
                   {-6, -2, 0, 5}
           };

           MinimumPassesToMakeAllElementsPositiveInMatrix matrixPasses = new MinimumPassesToMakeAllElementsPositiveInMatrix();
        System.out.println(matrixPasses.minimumPassesOfMatrix(matrix));
    }

    public int minimumPassesOfMatrix(int[][] matrix) {

        Queue<int[]> current = new LinkedList<>();
        Queue<int[]> next = new LinkedList<>();

        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[0].length; c++) {
                if(matrix[r][c] > 0) {
                    current.add(new int[]{r, c});
                }
            }
        }

        int passes = 0;
        while(!current.isEmpty() || !next.isEmpty()) {
            int[] positive = current.poll();
            int r = positive[0];
            int c = positive[1];
            if(r-1 >=0 && matrix[r-1][c] < 0) {
                matrix[r-1][c] *= -1;
                next.add(new int[]{r-1,c});
            }

            if(r+1 < matrix.length && matrix[r+1][c] < 0) {
                matrix[r+1][c] *= -1;
                next.add(new int[]{r+1, c});
            }

            if(c-1 >=0 && matrix[r][c-1] < 0) {
                matrix[r][c-1] *= -1;
                next.add(new int[]{r, c-1});
            }

            if(c+1 < matrix[0].length && matrix[r][c+1] < 0) {
                matrix[r][c+1] *= -1;
                next.add(new int[]{r, c+1});
            }

            if(current.size() <= 0) {
                passes++;
                Queue<int[]> temp = current;
                current = next;
                next = temp;
            }
        }

        for(int r=0; r<matrix.length; r++) {
            for(int c=0; c<matrix[0].length; c++) {
                if(matrix[r][c] < 0) {
                    return -1;
                }
            }
        }

        return passes -1;
    }
}
