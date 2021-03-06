package suanfa.factory.payFactoryDemo;

import org.springframework.util.StringUtils;
import suanfa.factory.payFactoryDemo.impl.AliPayStrategy;
import suanfa.factory.payFactoryDemo.impl.BankPayStrategy;
import suanfa.factory.payFactoryDemo.impl.WechatPayStrategy;

/**
 * @author taosh
 * @create 2020-02-27 13:11
 */
public enum PaymentEnums {
    ALI_PAY("ali_pay", AliPayStrategy.class.getSimpleName()),
    WECHAT_PAY("wechat_pay", WechatPayStrategy.class.getSimpleName()),
    BANK_PAY("bank_pay", BankPayStrategy.class.getSimpleName());

    private String code;
    private String beanName;

    PaymentEnums(String code, String beanName) {
        this.code = code;
        this.beanName = StringUtils.isEmpty(beanName)?null:beanName.toLowerCase();
    }

    public static PaymentEnums getEnum(String code){
        PaymentEnums[] values = PaymentEnums.values();
        if( null != code && values.length > 0 ) {
            for ( PaymentEnums value : values ) {
                if( value.code.equals( code ) ){
                    return value;
                }
            }
        }

        return null;
    }

    public static boolean containsCode(String code){
        PaymentEnums enums = getEnum(code);

        return enums != null;
    }
    public static boolean equalsCode(String code, PaymentEnums sourceEnum){
        return sourceEnum.code.equals(code);
    }

    public String getBeanName() {
        return beanName;
    }}
