package algorithm.sort;

/**
 * @author woshi
 * @date 2020/6/29
 * 计数排序
 */
public class CountingSort {
    //计数排序，a数组，n数组大小  非负整数
    public static void countingSort(int[] a, int n){
        if( n <= 1 ){
            return;
        }

        //查找数组中的数据范围
        int max = a[0];
        for( int i =0; i < n; i++ ){
            if( max < a[i] ){
                max = a[i];
            }
        }

        //申请一个计数数组c，下标大小[0,max]
        int[] c = new int[max + 1];

        //计算每个元素的个数
        for( int i = 0; i < n; i++ ){
            c[a[i]]++;
        }

        //累加
        for( int i = 1; i < max + 1; i++ ){
            c[i] = c[i-1] + c[i];
        }

        //临时数组r，存储排序之后的结果
        int[] r = new int[n];
        //计算排序
        for( int i = n - 1; i >= 0; --i ){
            int index = c[a[i]] - 1;
            r[index] = a[i];
            //减 1
            c[a[i]]--;
        }
    }
}