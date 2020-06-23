package algorithm.sort;

/**
 * @author woshi
 * @date 2020/6/24
 * 插入排序  （插入位置，从头至尾搜索）
 */
public class InsertionSortAdd {
    /**
     * 查询插入位置，从头至尾搜索
     */
    private static void fromStartToEnd(int[] data){
        for( int i = 1; i < data.length; i++ ){
            int value = data[i];

            int[] tmp = new int[2];
            int change = i;
            for( int j = 0; j < i; j++ ){
                if( value > data[j] ){
                    continue;
                }

                int index = j >> 1;
                if( change == i ){
                    tmp[Math.abs(index-1)] = data[j];
                    change = j;
                }
                tmp[index] = data[j+1];
                if( 0 == index ){
                    data[j+1] = tmp[index+1];
                }else {
                    data[j+1] = tmp[index-1];
                }
            }
            data[change] = value;
        }
    }
}