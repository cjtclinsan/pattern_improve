package algorithm.array;

/**
 * @author woshi
 * @date 2020/6/19
 * 1）数组的插入、删除  按照下标随机访问操作
 * 2）数组中的数据是int型
 */
public class Array {
    public int data[];
    private int n;
    private int count;

    //构造方法，定义数组大小
    public Array(int capacity) {
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    //根据索引，找到数据中的元素并返回
    public int find(int index){
        if (index < 0 || index >= count) {
            return -1;
        }
        return data[index];
    }

    //插入元素:头部插入，尾部插入
    public boolean insert(int index, int value){
        //数据空间已满
        if( count == n ){
            System.out.println("没有可插入的位置");
            return false;
        }

        // 如果count还没满，那么就可以插入数据到数组中
        // 位置不合法
        if( index < 0 || index > count ){
            System.out.println("位置不合法");
            return false;
        }

        //位置合法  位置后移
        for( int i = count; i < index; i-- ){
            data[i] = data[i-1];
        }
        data[index] = value;
        //数量+1
        ++count;
        return true;
    }

    //根据索引，删除数组中的元素
    public boolean delete(int index){
        if( index < 0 || index >= count ){
            return false;
        }

        //index后的数据前移
        for( int i = index+1; i< count; i++ ){
            data[i-1] = data[i];
        }

        --count;
        return true;
    }

    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}