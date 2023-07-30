package com.example.redis;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedissonUtils {

    private static final Logger log = LoggerFactory.getLogger(RedissonUtils.class);
    private int scanInterval;
    private String addNodeAddress;
    private String pwd;
    private Integer dataBase;
    private Config config = null;
    private static RedissonClient redisson = null;

    public RedissonUtils() {}

    public static RedissonClient getRedisson() {
        return redisson;
    }

    public void init() {
        try {
            this.config = this.createConfig();
            redisson = Redisson.create(this.config);
            log.info("加载redis 锁成功...");
        } catch (Exception var2) {
            log.error("加载redis 锁异常...", var2);
        }

    }

    private Config createConfig() {
        Config config = new Config();
        Integer dataBaseNum = 0;
        if (this.getDataBase() != null) {
            dataBaseNum = this.getDataBase();
        }

        if (!StringUtils.isEmpty(this.getPwd())) {
            config.useSingleServer().setAddress(this.getAddNodeAddress()).setPassword(this.getPwd())
                .setDatabase(dataBaseNum);
        } else {
            config.useSingleServer().setAddress(this.getAddNodeAddress()).setDatabase(dataBaseNum);
        }

        return config;
    }

    public int getScanInterval() {
        return this.scanInterval;
    }

    public void setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
    }

    public String getAddNodeAddress() {
        return this.addNodeAddress;
    }

    public void setAddNodeAddress(String addNodeAddress) {
        this.addNodeAddress = addNodeAddress;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getDataBase() {
        return this.dataBase;
    }

    public void setDataBase(Integer dataBase) {
        this.dataBase = dataBase;
    }

    public static RLock tryLock(String redisKey, long waitSec, long leaseSec) {
        if (StringUtils.isBlank(redisKey)) {
            return null;
        } else if (redisson == null) {
            return null;
        } else {
            RLock rLock = redisson.getLock(redisKey);
            if (rLock == null) {
                return null;
            } else {
                try {
                    boolean isLocked = rLock.tryLock(waitSec, leaseSec, TimeUnit.SECONDS);
                    return !isLocked ? null : rLock;
                } catch (Exception var7) {
                    log.error("Redisson tryLock error", var7);
                    return null;
                }
            }
        }
    }

    public static boolean isLocked(String redisKey) {
        RLock rLock = redisson.getLock(redisKey);
        return rLock.isLocked();
    }

    public static void releaseLock(RLock lock) {
        if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }

    }
}
