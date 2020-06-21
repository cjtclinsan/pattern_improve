package algorithm.array;

/**
 * @author woshi
 * @date 2020/6/19
 */
public class GenericArray<T> {
    private T[] data;
    private int size;

    public GenericArray(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    //无参构造，默认容量10
    public GenericArray() {
        this(10);
    }

    //获取容量
    public int getCapacity(){
        return data.length;
    }

    //获取当前元素个数
    public int getCount(){
        return size;
    }

    //判断数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //修改index位置的元素
    public void set(int index, T e){
        checkIndex(index);
        data[index] = e;
    }

    //查看数组是否包含某元素
    public boolean contains(T e){
        for( int i = 0; i < size; i++ ){
            if( data[i] == e ){
                return true;
            }
        }
        return false;
    }

    //获取对应元素下标
    public int[] getIndex(T e){
        int[] index = new int[]{};
        int j = 0;
        for( int i = 0; i < size; i++ ){
            if( data[i].equals(e) ){
                index[j] = i;
                j++;
            }
        }

        return index;
    }

    //在index位置，插入元素e，时间复杂度0（m+n）
    public void add(int index, T e){
        checkIndexForAdd(index);
        //如果当前元素个数等于数组容量，则将数组扩展为原来的2倍
        if( size == data.length ){
            resize(2*data.length);
        }

        //后移
        for( int i = size -1; i >= index; i-- ){
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //向数组头插入元素
    public void addHead(T e){
        add(0, e);
    }

    //向数组尾插入元素
    public void addTail(T e){
        add(size, e);
    }

    //删除index位置的元素，并返回
    public T remove(int index){
        checkIndex(index);

        T ret = data[index];
        for( int i = index+1; i< size; i++){
            data[i] = data[i+1];
        }
        size--;
        data[size] = null;

        //缩容
        if( size == (data.length>>2) ){
            resize(data.length>>1);
        }

        return ret;
    }

    //删除第一个元素
    public T removeHead(){
        return remove(0);
    }

    //删除最后一个元素
    public T removeTail(){
        return remove(size-1);
    }

    // 从数组中删除指定元素
//    public void removeElement(T e){
//        int[] index = getIndex(e);
//        if( index.length > 0 ){
//            for (int i : index) {
//                remove(i);
//            }
//        }
//    }



    private void checkIndexForAdd(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed! Require index >=0 and index <= size.");
        }
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed!");
        }
    }

    //时间复杂度O(n)
    private void resize(int capacity) {
        T[] newData = (T[]) new Object[capacity];

        //将原数据放入新的地址
        for(int i = 0; i < size; i++){
            newData[i] = data[i];
        }

        data = newData;
    }

}