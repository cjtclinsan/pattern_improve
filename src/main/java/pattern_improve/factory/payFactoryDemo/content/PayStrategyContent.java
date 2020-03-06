package pattern_improve.factory.payFactoryDemo.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pattern_improve.factory.payFactoryDemo.PayStrategy;
import pattern_improve.factory.payFactoryDemo.PaymentEnums;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author taosh
 * @create 2020-02-27 11:51
 */
@Component
public class PayStrategyContent {
    /**策略实例集合*/
    private ConcurrentHashMap<String, PayStrategy> strategyMap = new ConcurrentHashMap<>();

    /**
     * 注入实例
     */
    @Autowired
    public PayStrategyContent(ConcurrentHashMap<String, PayStrategy> strategyMap) {
        this.strategyMap.clear();

        if(!CollectionUtils.isEmpty(strategyMap)){
            strategyMap.forEach((beanName, payStrategy)->{
                if(StringUtils.isEmpty(beanName) || payStrategy == null){
                    return;
                }
                this.strategyMap.put(beanName, payStrategy);
            });
        }
    }

    String toPayHtml(PaymentEnums paymentEnums){
        if( CollectionUtils.isEmpty(strategyMap) ){
            return "策略初始化失败...";
        }

        return this.strategyMap.get(paymentEnums.getBeanName()).toPayHtml();
    }
}
