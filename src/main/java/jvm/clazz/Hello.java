package jvm.clazz;

/**
 * @author woshi
 * @date 2020/11/18
 */
public class Hello {
    public int m( int i){
        if( i == 1 ){
            return 1;
        }
        return i * m(i-1);
    }
}