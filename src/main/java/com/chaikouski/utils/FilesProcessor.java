package com.chaikouski.utils;

import lombok.Getter;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class FilesProcessor {

    private static final Pattern FILE = Pattern.compile("^(.+?)(\\..+?)$");

    private static String name;
    private static String extension;

    public static void processFile(long id, String fileName) throws NoSuchAlgorithmException {
        Matcher matcher = FILE.matcher(fileName);
        if (matcher.find()) {
            name = Encoder.sha1(matcher.group(1) + id + new Random().nextInt(10000));
            extension = matcher.group(2);
        }
    }

    public static String getName() {
        return name;
    }

    public static String getExtension() {
        return extension;
    }
}
