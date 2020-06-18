package search;

/**
 * @author woshi
 * @date 2020/6/19
 * 查找最后一个值等于给定值的元素
 */
public class BSearch5 {
    public int bSearch(int[] a, int n, int value) {
        int low = 0;
        int high = n-1;
        while ( low >= high ){
            int mid = low + ((high-low)>>1);
            if(a[mid] > value){
                low = mid + 1;
            }else if( a[mid] < value ){
                high = mid - 1;
            }else {
                if( (mid == n-1) || a[mid+1] != value )
                    return mid;
                else
                    low = mid + 1;
            }
        }
        return -1;
    }
}