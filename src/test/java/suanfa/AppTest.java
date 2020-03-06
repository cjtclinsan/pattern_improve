package suanfa;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        AppTest app = new AppTest();
        int [][] arr = app.findContinuousSequence(100);
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.println(anInt);
            }
        }
    }

    public int[][] findContinuousSequence(int target) {

        LinkedList<LinkedList<Integer>> result = new LinkedList<>();
        for( int i = 1; i < target/2; i++ ){
            LinkedList<Integer> arr = new LinkedList<>();
            arr.add(i);
            int temp = i;
            for( int j = i + 1; j < target/2; j++ ){
                if( temp+j == target ){
                    arr.add(j);
                    result.add(arr);
                    break;
                }else if ( temp+j < target ){
                    temp += j;
                    arr.add(j);
                    continue;
                }else if( temp+j > target ){
                    break;
                }
            }
        }

        int[][] out = listToArr(result);

        return out;
    }

    private int[][] listToArr(LinkedList<LinkedList<Integer>> result) {
        int[][] arr = new int[result.size()][];

        int j = 0;
        for (LinkedList<Integer> list : result) {
            int[] tmp = new int[list.size()];
            for( int i = 0; i < list.size(); i++ ){
                tmp[i] = list.get(i);
            }
            arr[j] = tmp;
            j++;
        }

        return arr;
    }
}
