package pattern_improve.factory.payFactoryDemo.impl;

import pattern_improve.factory.payFactoryDemo.PayStrategy;

/**
 * @author taosh
 * @create 2020-02-27 11:49
 */
public class BankPayStrategy implements PayStrategy {
    @Override
    public String toPayHtml() {
        System.out.println("银联支付...");
        return "银联支付html";
    }
}
