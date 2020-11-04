package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author woshi
 * @date 2020/11/4
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}