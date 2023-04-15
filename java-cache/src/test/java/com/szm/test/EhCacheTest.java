package com.szm.test;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@BenchmarkMode({Mode.AverageTime})
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations=3, time = 5, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time = 1,timeUnit = TimeUnit.SECONDS)
@Threads(8)
@Fork(3)
@State(Scope.Thread)
public class EhCacheTest {
    private static CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .build(true);

    private static Cache<String, String> cache = cacheManager.createCache("myCache",
            CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, String.class,
                    ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, MemoryUnit.MB))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(1L))).build());

    static {
        cache.put("test","test");
    }

    @Benchmark
    public void test(){
        cache.get("test");
    }
}
