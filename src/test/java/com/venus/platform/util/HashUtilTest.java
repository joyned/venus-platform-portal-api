package com.venus.platform.util;

import org.junit.jupiter.api.Test;

public class HashUtilTest {

    @Test
    public void testHash() {
        String hash = HashUtils.hash("123mudar");
        System.out.println(hash);
    }
}
