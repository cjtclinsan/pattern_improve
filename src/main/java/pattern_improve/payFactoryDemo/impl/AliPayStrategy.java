package pattern_improve.payFactoryDemo.impl;

import pattern_improve.payFactoryDemo.PayStrategy;

/**
 * @author taosh
 * @create 2020-02-27 11:49
 */
public class AliPayStrategy implements PayStrategy {
    @Override
    public String toPayHtml() {
        System.out.println("支付宝支付...");
        return "支付宝支付html";
    }
}
