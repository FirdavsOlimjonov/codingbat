package ai.ecma.codingbat.util;

import java.util.Arrays;
import java.util.regex.Pattern;

public class naaaa {

    public static void main(String[] args) {
        String a = " aaa    aa a aa a .";
        String[] split = a.split("[\\s|\\W]+${1,}");
        System.out.println(Arrays.toString(split));
    }
}
