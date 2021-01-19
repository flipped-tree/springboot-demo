package com.example.springboot.chain;

public interface Ordered {
    /**
     * Useful constant for the highest precedence value.
     *
     * @see Integer#MIN_VALUE
     */
    int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;

    /**
     * Useful constant for the lowest precedence value.
     *
     * @see Integer#MAX_VALUE
     */
    int LOWEST_PRECEDENCE = Integer.MAX_VALUE;

    /**
     * 获取优先级
     *
     * @return
     */
    default int getOrder() {
        return 0;
    }
}
