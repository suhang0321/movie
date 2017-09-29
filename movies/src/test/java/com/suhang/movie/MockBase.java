package com.suhang.movie;

import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

/**
 * @author hang.su
 * @since 2017-04-26 下午7:03
 */
public abstract class MockBase {

    protected String mockUUID() {
        return UUID.randomUUID().toString();
    }

    protected String mockStr(int length) {
        return UUID.randomUUID().toString()
            .replaceAll("-", "")
            .substring(0, Math.min(32, length));
    }

    protected int mockInt() {
        return this.mockInt(10000);
    }

    protected int mockInt(int max) {
        return this.mockInt(0, max);
    }

    protected int mockInt(int min, int max) {
        return RandomUtils.nextInt(min, max);
    }

    protected long mockLong() {
        return this.mockLong(10000);
    }

    protected long mockLong(long max) {
        return this.mockLong(0, max);
    }

    protected long mockLong(long min, long max) {
        return RandomUtils.nextLong(min, max);
    }

    protected String mockEmail() {
        return mockStr(10) + "@" + mockStr(4) + ".com";
    }

    protected String mockMobile() {
        return "186" + mockLong(10000000, 99999999);
    }
}
