package suanfa.proxy.demo3;

import suanfa.proxy.demo2.IPerson;

/**
 * @author taosh
 * @create 2020-03-05 16:36
 */
public class Lisi implements IPerson {
    @Override
    public void findLove() {
        System.out.println("我要求高!有房有车");
    }

    public void buyInsure(){

    }
}
