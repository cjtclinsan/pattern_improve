package sort;

/**
 * @author woshi
 * @date 2020/6/16
 */
public class InsertionSort {
    public void insertionSort(int[] a, int n){
        if( n <= 1){
            return;
        }

        for(int i = 0; i < n; ++i){
            int value = a[i];
            int j = i - 1;
            //查找插入的位置
            for(; j >= 0; --j){
                if(a[j] > value ){
                    //数据整体往后移动
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            //插入数据
            a[j+1] = value;
        }
    }
}