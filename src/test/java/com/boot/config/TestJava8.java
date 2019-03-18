package com.boot.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

public class TestJava8 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestJava8.class);


    @Test
    public void testStreamMap() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 7, 3);
        //获取对应的平方数
        LOGGER.info("list = {}", Arrays.toString(numbers.stream().map(i -> i * i).distinct().toArray()));
    }

    @Test
    public void testStreamFilter() {
        List<String> strings = Arrays.asList("a", "", "c", "", "");
        //取空字符串个数
        long count = strings.stream().filter(String::isEmpty).count();
        LOGGER.info("count = {}", count);
    }

    @Test
    public void testStreamLimit() {
        Random random = new Random();
        random.ints().limit(10).forEach(System.out::println);
    }

    @Test
    public void testStreamSorted() {
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);
    }

    @Test
    public void testStreamStatistics() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 7, 3);
        //统计
        IntSummaryStatistics intSummaryStatistics = numbers.stream().mapToInt(x -> x).summaryStatistics();
        LOGGER.info("max" + intSummaryStatistics.getMax());
        LOGGER.info("min" + intSummaryStatistics.getMin());
        LOGGER.info("ave" + intSummaryStatistics.getAverage());
        LOGGER.info("sum" + intSummaryStatistics.getSum());
    }
}
