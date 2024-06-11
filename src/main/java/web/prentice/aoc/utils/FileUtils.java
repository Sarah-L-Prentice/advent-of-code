package web.prentice.aoc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileUtils {

    private FileUtils() {
        //utility class
    }

    private static File create(String path) {
        return new File(path);
    }

    public static List<String> readLines(String filePath) {
        try {
            File file = new File(filePath);
            return Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<char[]> linesToListChar(List<String> lines){
        return lines.stream().map(str -> str.toCharArray()).toList();
    }

    public static List<char[]> fileToListChar(String filePath){
        return linesToListChar(readLines(filePath));
    }

}
