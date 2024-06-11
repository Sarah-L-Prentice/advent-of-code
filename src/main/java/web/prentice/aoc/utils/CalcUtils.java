package web.prentice.aoc.utils;

import java.util.List;

public class CalcUtils {


    private CalcUtils() {
        //Utility Class
    }

    public static int sum(List<Integer> ints){
        return ints.stream().reduce(0, Integer::sum);
    }
}
