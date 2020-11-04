package disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * @author woshi
 * @date 2020/11/4
 */
public class DisruptorMain2 {
    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 区别
        EventTranslator<LongEvent> translator = (longEvent, sequence) -> longEvent.set(8888l);
        ringBuffer.publishEvent(translator);

        //-------------------------------
        EventTranslatorOneArg<LongEvent, Long> translatorOneArg = (longEvent, sequence, l) -> longEvent.set(l);
        ringBuffer.publishEvent(translatorOneArg, 7777l);

        //-------------------------------
        EventTranslatorTwoArg<LongEvent, Long, Long> translatorTwoArg = (longEvent, sequence, l1, l2) -> longEvent.set(l1+l2);
        ringBuffer.publishEvent(translatorTwoArg, 10000l, 10000l);

        //-------------------------------
        EventTranslatorThreeArg<LongEvent, Long, Long, Long> translatorThreeArg = (longEvent, sequence, l1, l2, l3) -> longEvent.set(l1+l2+l3);
        ringBuffer.publishEvent(translatorThreeArg, 11111l, 11111l, 11111l);

        //-------------------------------
        EventTranslatorVararg<LongEvent> translatorVararg = (longEvent, sequence, objects) -> {
            long result = 0;
            for (Object object : objects) {
                long l = (long) object;
                result += l;
            }
            longEvent.set(result);
        };
        ringBuffer.publishEvent(translatorVararg, 10000l, 20000l, 30000l, 40000l);
    }
}