package com.example.springboot.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

/**
 * @author xingce
 */
public class XxlJob {

    private static final Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(KeepAliveUnit.getInstance().isAlive("test"));
        KeepAliveUnit.getInstance().addJob("test");
        KeepAliveUnit.getInstance().removeJob("test");
    }

    static class KeepAliveUnit implements Runnable {

        private Set<String> jobSet = new TreeSet<>();
        private static volatile KeepAliveUnit instance;
        private static Thread thread = null;

        public static KeepAliveUnit getInstance() {
            if (instance == null) {
                synchronized (KeepAliveUnit.class) {
                    if (instance == null) {
                        instance = new KeepAliveUnit();
                        thread = new Thread(instance);
                        thread.start();
                    }
                }
            }
            return instance;
        }

        public void addJob(String jobName) {
            synchronized (jobSet) {
                try {
                    Integer expireSec = Integer.MAX_VALUE;
                    map.put(jobName, 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jobSet.add(jobName);
            }
        }

        public void removeJob(String jobName) {
            synchronized (jobSet) {
                try {
                    map.remove(jobName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                jobSet.remove(jobName);
            }
        }

        @Override
        public void run() {
            while (true) {
                synchronized (jobSet) {
                    for (String curJob : jobSet) {
                        incJobCount(curJob);
                    }
                    System.out.println(jobSet.size());
                }
                idle();
            }
        }

        public boolean isAlive(String jobName) {
            try {
                Integer jobValue = map.get(jobName);
                if (jobValue == null) {
                    return false;
                }

                int checkLimit = 10;
                for (int i = 0; i < checkLimit; ++i) {
                    idle();
                    Integer newJobValue = map.get(jobName);
                    if (newJobValue == null) {
                        // 轮询期间前一次调度任务完成，jobValue被清除；
                        return false;
                    }
                    if (newJobValue > jobValue) {
                        // 任务计数保活生效，前一个job还在工作；
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

        private void incJobCount(String jobName) {
            try {
                Integer jobValue = map.get(jobName);
                if (jobValue == null) {
                    return;
                }
                map.put(jobName, ++jobValue);
                System.out.println(map.get(jobName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void idle() {
            try {
                long idleMillis = 1000;
                TimeUnit.MILLISECONDS.sleep(idleMillis);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
