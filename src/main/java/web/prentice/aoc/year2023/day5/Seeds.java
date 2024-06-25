package web.prentice.aoc.year2023.day5;

import lombok.extern.slf4j.Slf4j;
import web.prentice.aoc.utils.FileUtils;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
public class Seeds {


    private static final String SEED_TO_SOIL_PATH = "src/test/java/year2023/day5/maps/seed-to-soil";
    private static final String SOIL_TO_FERTILIZER_PATH = "src/test/java/year2023/day5/maps/soil-to-fertilizer";
    private static final String FERTILIZER_TO_WATER_PATH = "src/test/java/year2023/day5/maps/fertilizer-to-water";
    private static final String WATER_TO_LIGHT_PATH = "src/test/java/year2023/day5/maps/water-to-light";
    private static final String LIGHT_TO_TEMPERATURE_PATH = "src/test/java/year2023/day5/maps/light-to-temperature";
    private static final String TEMPERATURE_TO_HUMIDITY_PATH = "src/test/java/year2023/day5/maps/temperature-to-humidity";
    private static final String HUMIDITY_TO_LOCATION_PATH = "src/test/java/year2023/day5/maps/humidity-to-location";

    public static final List<Long> SEEDS = List.of(2906422699L, 6916147L, 3075226163L, 146720986L, 689152391L, 244427042L, 279234546L, 382175449L, 1105311711L, 2036236L, 3650753915L, 127044950L, 3994686181L, 93904335L, 1450749684L, 123906789L, 2044765513L, 620379445L, 1609835129L, 6005095L);

    private static HashMap<Long, Long> seedToSoil;
    private static HashMap<Long, Long> soilToFertilizer;
    private static HashMap<Long, Long> fertilizerToWater;
    private static HashMap<Long, Long> waterToLight;
    private static HashMap<Long, Long> lightToTemperature;
    private static HashMap<Long, Long> temperatureToHumidity;
    private static HashMap<Long, Long> humidityToLocation;


    public static Long printSeedToLocationAndFindMin(List<Long> seeds) {
        List<Long> seedLocations = new ArrayList<>();

        seedToSoil = readLinesToCreateMap(FileUtils.readLines(SEED_TO_SOIL_PATH), seeds);
        soilToFertilizer = readLinesToCreateMap(FileUtils.readLines(SOIL_TO_FERTILIZER_PATH), seedToSoil.values());
        fertilizerToWater = readLinesToCreateMap(FileUtils.readLines(FERTILIZER_TO_WATER_PATH), soilToFertilizer.values());
        waterToLight = readLinesToCreateMap(FileUtils.readLines(WATER_TO_LIGHT_PATH), fertilizerToWater.values());
        lightToTemperature = readLinesToCreateMap(FileUtils.readLines(LIGHT_TO_TEMPERATURE_PATH), waterToLight.values());
        temperatureToHumidity = readLinesToCreateMap(FileUtils.readLines(TEMPERATURE_TO_HUMIDITY_PATH), lightToTemperature.values());
        humidityToLocation = readLinesToCreateMap(FileUtils.readLines(HUMIDITY_TO_LOCATION_PATH), temperatureToHumidity.values());

        SEEDS.forEach(seed -> seedLocations.add(getSeedToLocation(seed)));
        return Collections.min(seedLocations);
    }

    public static Long getSeedToLocation(Long seed) {
        Long soil = seedToSoil.getOrDefault(seed, seed);
        Long fertilizer = soilToFertilizer.getOrDefault(soil, soil);
        Long water = fertilizerToWater.getOrDefault(fertilizer, fertilizer);
        Long light = waterToLight.getOrDefault(water, fertilizer);
        Long temperature = lightToTemperature.getOrDefault(light, light);
        Long humidity = temperatureToHumidity.getOrDefault(temperature, temperature);
        Long location = humidityToLocation.getOrDefault(humidity, humidity);
        System.out.println(
                "Seed: " + seed +
                        ", Soil: " + soil +
                        ", Fertilizer: " + fertilizer +
                        ", Water: " + water +
                        ", Light: " + light +
                        ", Temperature: " + temperature +
                        ", Humidity: " + humidity +
                        ", Location: " + location
        );
        return location;
    }


    public static void readLineToCreateMap(HashMap<Long, Long> map, String line, Collection<Long> range) {
        String[] split = line.split(" ");
        Long destinationRange = Long.parseLong(split[0]);
        Long sourceRange = Long.parseLong(split[1]);
        Long lengthRange = Long.parseLong(split[2]);
        LongStream.range(0, lengthRange).forEach(val -> {
            if(range.contains(sourceRange + val)){
                map.put(sourceRange + val, destinationRange + val);
            }
        });
    }

    public static HashMap<Long, Long> readLinesToCreateMap(List<String> lines, Collection<Long> range) {
        final HashMap<Long, Long> map = new HashMap<>();
        lines.forEach(line -> readLineToCreateMap(map, line, range));
        range.forEach(value -> map.computeIfAbsent(value, Long::longValue));
        return map;
    }

    public static List<Long> getFullRange(List<Long> rangePairs){
       List<Long> fullRange = new ArrayList<>();
        for(int i = 0; i < rangePairs.size()-1; i+=2){
            Long start = rangePairs.get(i);
            Long length = rangePairs.get(i + 1);
            LongStream.range(start, start + length).forEach(fullRange::add);
            int j = 1;
        }
        return fullRange;
    }


}
