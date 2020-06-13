package stack;

/**
 * @author woshi
 * @date 2020/6/13
 */
public class ArrayStack {
    private String[] items;
    private int count;       //栈中元素个数
    private int n;           //栈的大小

    //初始化数组，申请一个大小为n的数组空间
    private ArrayStack(int n){
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    //入栈
    public boolean push(String item){
        //数组空间不够了，返回false
        if( count == n ){
            return false;
        }

        //将item放到下标为count的位置，count+1
        items[count] = item;
        ++count;
        return true;
    }

    //出栈
    public String pop(){
        //栈为空。返回null
        if(count == 0){
            return null;
        }

        //返回下标为count-1的数组元素，count-1
        String tmp = items[count-1];
        --count;
        return tmp;
    }
}