package suanfa;

import java.util.Random;

/**
 * @author taosh
 * @create 2020-03-11 14:40
 */
public class CanThreePartsEqualSum {
    public static boolean canThreePartsEqualSum(int[] A) {
        if ( null == A || A.length < 3 )
            return false;

        int a = 0;
//        for ( int i = 0; i < A.length-2; i++) {
//            a += A[i];
//            int b = 0;
//            for( int j = i+1; j < A.length -1; j++){
//                b += A[j];
//                int c = 0;
//                for ( int h = j+1; h < A.length; h++ ){
//                    c += A[h];
//                    if( a == b && a == c){
//                        return true;
//                    }
//                }
//            }
//        }
        for ( int i = 0; i < A.length; i++) {
            a += A[i];
        }
        if( a % 3 != 0 ){
            return false;
        }
        int b = a / 3;
        int c = 0;
        for ( int i = 0; i < A.length-2; i++) {
            c += A[i];
            if( c == b ){
                int d = 0;
                for ( int j = i+1; j < A.length-1; j++ ){
                    d += A[j];
                    if( d == b ){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,3,6,5,-2,2,5,1,-9,4};
        System.out.println(canThreePartsEqualSum(arr));
    }
}
