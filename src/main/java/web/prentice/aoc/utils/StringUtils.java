package web.prentice.aoc.utils;

public class StringUtils {

    private StringUtils(){
        //Utility Class
    }

    public static String reverse(String line) {
       return new StringBuilder(line).reverse().toString();
    }




}
