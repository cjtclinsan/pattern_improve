package suanfa;

/**
 * @author taosh
 * @create 2020-04-07 8:56
 */
public class Rotate {

    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        int num = length-1;

        int[][] tmp = new int[length][length];
        for (int i = 0; i < length; i++) {
            for( int j = 0; j < length; j++ ){
                tmp[i][j] = matrix[num-j][i];
            }
        }

        for (int[] ints : tmp) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}};
        rotate(matrix);
    }

}
