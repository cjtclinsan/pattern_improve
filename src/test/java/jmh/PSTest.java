package jmh;

import org.openjdk.jmh.annotations.*;

/**
 * @author woshi
 * @date 2020/11/3
 */
public class PSTest {
    @Benchmark
    @Warmup(iterations = 1, time = 3)        //首先进行预热，预热多少次，预热多少时间
    @Fork(5)                                 //用过多少线程去执行方法
    @BenchmarkMode(Mode.Throughput)          //基准测试的一种模式，Throughput 吞吐量模式
    @Measurement(iterations = 1, time = 3)   //整个测试需要测几遍，调用这个方法几次
    public void testForeach(){
        PS.foreach();
    }
}