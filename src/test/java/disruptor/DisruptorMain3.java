package disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.io.IOException;

/**
 * @author woshi
 * @date 2020/11/4
 */
public class DisruptorMain3 {
    public static void main(String[] args) throws IOException {
//        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event:"+event));

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        ringBuffer.publishEvent((event,sequence) -> event.set(10000l));

        System.in.read();
    }
}