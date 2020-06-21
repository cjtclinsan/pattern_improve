package algorithm.linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * @author woshi
 * @date 2020/6/20
 * 基于数组实现LRU缓存
 * 1.空间复杂度为O（n）
 * 2.时间复杂度为O(n)
 * 3.不支持null的缓存
 */
public class LRUBasedArray<T> {
    private static final int DEFAULT_CAPACITY = (1 << 3);

    private int capacity;

    private int count;

    private T[] value;

    private Map<T, Integer> holder;

    public LRUBasedArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBasedArray(int capacity){
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<T, Integer>(capacity);
    }

    /**
     * 模拟访问某个值
     */
    public void offer(T object){
        if( object == null ){
            throw new IllegalArgumentException("该缓存不支持null类型");
        }

        //获取下标
        Integer index = holder.get(object);
        if( index == null ){
            if(isFull()){
                //移除最后一位    缓存最新的值到第一位
                removeAndCache(object);
            }else {
                //缓存最新的值到第一位
                cache(object, count);
            }
        }else {
            //更新下标到第一位
            update(index);
        }
    }

    /**
     * 缓存数据到头部，但要先右移
     * @param object
     * @param end 数据右移的边界
     */
    private void cache(T object, int end) {
        rightShift(end);
        //缓存数据到头部
        value[0] = object;
        //保存数据的下标
        holder.put(object, 0);
        count++;
    }

    /**
     * 缓存满的情况，踢出后，再缓存到数组头部
     * @param object
     */
    private void removeAndCache(T object) {
        T key = value[--count];
        //移除最后一位
        holder.remove(key);
        cache(object, count);
    }

    /**
     * 若缓存中有某个值，更新位置
     * @param end
     */
    private void update(Integer end) {
        T target = value[end];
        rightShift(end);
        //将数据放到数据第一位
        value[0] = target;
        //缓存小标
        holder.put(target, 0);
    }

    /**
     * end 左边的数据统一右移一位（即删除end）
     * @param end
     */

    private void rightShift(Integer end) {
        for( int i = end -1; i >= 0; i-- ){
            value[i+1] = value[i];
            //更新数据的下标
            holder.put(value[i], i+1);
        }
    }

    public boolean isContain(T object){
        return holder.containsKey(object);
    }

    private boolean isFull() {
        return count == capacity;
    }

    private boolean isEmpty(){
        return count == 0;
    }
}