package com.szm.test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations=3, time = 5, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time = 1,timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(3)
@State(Scope.Thread)
public class GuavaTest {
    private static Cache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1,TimeUnit.SECONDS)
            .build();

    static {
        cache.put("test","test");
    }

    @Benchmark
    public void test(){
        cache.getIfPresent("test");
    }
}
