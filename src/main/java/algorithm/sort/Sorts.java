package algorithm.sort;

/**
 * @author woshi
 * @date 2020/6/24
 */
public class Sorts {
    //冒泡排序
    public static void bubbleSort(int[] a, int n) {
        if( n <= 1 ){
            return;
        }

        for( int i = 0; i < n; i++ ){
            //提前退出标志位
            boolean flag = false;
            for( int j = 0; j < n - 1; j++ ){
                if( a[j] > a[j+1] ){
                    //交换
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    //有数据交换
                    flag = true;
                }
            }
            //没有数据交换，退出
            if( !flag ){
                break;
            }
        }
    }

    /**
     * 冒泡排序改进:在每一轮排序后记录最后一次元素交换的位置,作为下次比较的边界,
     * 对于边界外的元素在下次循环中无需比较(肯定是最大).
     */
    public static void bubbleSort2(int[] a, int n){
        if( n <= 1 ){
            return;
        }

        //最后一次交换的位置
        int lastExchange = 0;
        //无序的数据边界，，每次只需要比较到这里
        int sortBorder = n -1;
        for( int i = 0; i < n; i++ ){
            //提前退出标志
            boolean flag = false;
            for( int j = 0; j < sortBorder; j++ ){
                if( a[j] > a[j+1] ){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    flag = true;

                    //更新最后一次交换的位置
                    lastExchange = j;
                }
            }
            sortBorder = lastExchange;
            if( !flag ){
                break;
            }
        }
    }

    //插入排序
    public static void insertionSort(int[] a, int n){
        if( n <= 1 ){
            return;
        }

        for(int i = 1; i < n; i++ ){
            int value = a[i];
            int j = i - 1;
            //查找要插入的位置
            for (; j >= 0; --j) {
                if( a[j] > value ){
                    //比value大的往后移1位
                    a[j+1] = a[j];
                }else {
                    break;
                }
            }
            //给空下来的数组赋值
            a[j+1] = value;
        }
    }

    //选择排序   每次循环获取最小值的index,和a[i]交换
    public static void selectionSort(int[] a, int n){
        if( n <= 1 ){
            return;
        }

        for( int i = 0; i < n - 1; i++ ){
            //查找最小值
            int minIndex = 1;
            for( int j = i + 1; j < n; j++ ){
                if( a[j] < a[minIndex] ){
                    minIndex = j;
                }
            }

            //交换
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }

}