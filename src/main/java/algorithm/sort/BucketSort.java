package algorithm.sort;

/**
 * @author woshi
 * @date 2020/6/29
 * 桶排序算法
 */
public class BucketSort {
    /**
     * 桶排序
     * @param arr
     * @param bucketSize
     */
    public static void bucketSort(int[] arr, int bucketSize){
        if( arr.length < 2 ){
            return;
        }

        //数组最小值,最大值
        int minValue = arr[0];
        int maxValue = arr[1];
        for(int i = 0; i < arr.length; i++){
            if( arr[i] < minValue ){
                minValue = arr[i];
            }else if( arr[i] > maxValue ){
                maxValue = arr[i];
            }
        }

        //桶数量
        int bucketCount = (maxValue-minValue)/bucketSize + 1;
        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];

        //将数组中值分配到各个桶里
        for( int i = 0; i < arr.length; i++ ){
            //分配到第index个桶
            int bucketIndex = (arr[i] - minValue) / bucketSize;
            if( indexArr[bucketIndex] == buckets[bucketIndex].length){
                //扩容
                ensureCapacity(buckets, bucketIndex);
            }
            //第index个桶里的数据+1
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }

        //对每个桶进行排序
        int k = 0;
        for( int i = 0; i <buckets.length; i++ ){
            if( indexArr[i] == 0 ){
                continue;
            }

            quickSort(buckets[i], 0, indexArr[i]-1);
            for( int j = 0; j < indexArr[i]; j++ ){
                arr[k++] = buckets[i][j];
            }
        }
    }

    /**
     * 第index个桶扩容  数组扩容
     * @param buckets
     * @param bucketIndex
     */
    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length * 2];
        for( int j = 0; j < tempArr.length; j++ ){
            newArr[j] = tempArr[j];
        }
        buckets[bucketIndex] = newArr;
    }

    /**
     * 快速排序递归函数
     */
    private static void quickSort(int[] arr, int p, int r){
        if ( p >= r ) {
            return;
        }
        int q = partition(arr, p, r);
        quickSort(arr, p, q-1);
        quickSort(arr, p+1, r);
    }


    /**
     * 分区函数
     * @param arr
     * @param p
     * @param r
     * @return
     */
    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        for( int j = p; j < r; j++){
            if( arr[j] <= pivot ){
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i , r);
        return i;
    }

    /**
     * 交换
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {

        if( i == j ){
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}