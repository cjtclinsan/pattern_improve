package pattern_improve.singleton;

/**
 * @author taosh
 * @create 2020-03-04 18:48
 */
public enum  EnumSingleton {
    INSTANCE;

    private Object data;

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
