package suanfa.factory.payFactoryDemo.impl;

import suanfa.factory.payFactoryDemo.PayStrategy;

/**
 * @author taosh
 * @create 2020-02-27 11:49
 */
public class WechatPayStrategy implements PayStrategy {
    @Override
    public String toPayHtml() {
        System.out.println("微信支付...");
        return "微信支付html";
    }
}
