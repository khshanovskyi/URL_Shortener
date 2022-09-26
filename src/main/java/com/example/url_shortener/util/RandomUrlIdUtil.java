package com.example.url_shortener.util;

import org.apache.commons.lang3.RandomStringUtils;

public final class RandomUrlIdUtil {
    private RandomUrlIdUtil() {
    }

    public static String generateId(){
        return RandomStringUtils.randomAlphabetic(7).trim();
    }
}
