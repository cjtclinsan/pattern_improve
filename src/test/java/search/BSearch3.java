package search;

/**
 * @author woshi
 * @date 2020/6/19
 * 查找第一个值等于给定值的元素
 */
public class BSearch3 {
    public static int bsearch(int[] a, int n, int value){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid = low + ((high-low)>>1);
            if( a[mid] >= value ){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        if(low < n && a[low] == value){
            return low;
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,3,4,5,6,8,8,8,11,18};

        System.out.println(bsearch(a, 10, 8));
    }
}