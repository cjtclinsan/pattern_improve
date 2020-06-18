package search;

/**
 * @author woshi
 * @date 2020/6/19
 */
public class Bsearch {
    public int bsearch(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;

        if( low <= high ){
            int mid = low + ((high-low)>>1);
            if( a[mid] == value ){
                return mid;
            }else if( a[mid] < value ){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }

        return -1;
    }
}