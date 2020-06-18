package search;

/**
 * @author woshi
 * @date 2020/6/19
 */
public class BSearch2 {
    public int bSearch(int[] a, int n, int value){
        return bsearchInternally(a, 0, n-1, value);
    }

    private int bsearchInternally(int[] a, int low, int high, int value) {
        if( low > high ){
            return -1;
        }

        int mid = low + ((high-low)>>1);
        if( a[mid] == value ){
            return mid;
        }else if( a[mid] < value ){
            return bsearchInternally(a, mid+1, high, value);
        }else {
            return bsearchInternally(a, low, mid-1, value);
        }
    }
}