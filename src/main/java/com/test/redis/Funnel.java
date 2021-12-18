package com.test.redis;

class Funnel {

    int capacity;
    float leakingRate;
    int leftQuota;
    long leakingTs;

    public Funnel(int capacity, float leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = capacity;
        this.leakingTs = System.currentTimeMillis();
    }

    void makeSpace() {
        long nowTs = System.currentTimeMillis();
        long deltaTs = nowTs - leakingTs;
        int deltaQuota = (int) (deltaTs * leakingRate);
        if (deltaQuota < 0) { // 间隔时间太长，整数数字过大溢出
            this.leftQuota = capacity;
            this.leakingTs = nowTs;
            return;
        }
        if (deltaQuota < 1) { // 腾出空间太小，最小单位是1
            return;
        }
        this.leftQuota += deltaQuota;
        this.leakingTs = nowTs;
        if (this.leftQuota > this.capacity) {
            this.leftQuota = this.capacity;
        }
    }

    boolean watering(int quota) {
        makeSpace();
        if (this.leftQuota >= quota) {
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }
}
