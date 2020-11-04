package disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @author woshi
 * @date 2020/11/4
 */
public class DisruptorMain1 {
    public static void main(String[] args) {
        // event 的 工厂
        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 1024;     //2的N次方

        //初始化 disruptor，三个参数：工厂factory，环的大小，线程工厂
        Disruptor<LongEvent> disruptor = new Disruptor<>(factory, bufferSize, Executors.defaultThreadFactory());

        //连接 handler
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        // 从 disruptor 中获取 ringBuffer 用于发布
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        // 获取序号
        long sequence = ringBuffer.next();

        try {
            LongEvent event = ringBuffer.get(sequence);
            // 赋值
            event.set(8888l);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}