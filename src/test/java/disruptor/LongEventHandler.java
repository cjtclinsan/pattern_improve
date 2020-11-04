package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author woshi
 * @date 2020/11/4
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    public static long count = 0;

    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        count++;
        System.out.println("[" + Thread.currentThread().getName() + "]" + longEvent + "序号:" + sequence);
    }
}