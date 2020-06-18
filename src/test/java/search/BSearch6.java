package search;

/**
 * @author woshi
 * @date 2020/6/19
 */
public class BSearch6 {
    public static int bsearch(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid = low + ((high-low)>>1);
            if( a[mid] >= value ){
                if( (mid == 0) || a[mid-1] < value )
                    return mid;
                else
                    high = mid - 1;
            }else if( a[mid] < value ){
                low = mid + 1;
            }
        }
        return -1;
    }
}