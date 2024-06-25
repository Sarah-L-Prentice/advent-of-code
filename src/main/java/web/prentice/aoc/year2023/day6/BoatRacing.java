package web.prentice.aoc.year2023.day6;

import java.util.List;

public class BoatRacing {


    public record Race(long timeRaceLastsMs, long distanceMm) {
    }

    public static int calculateRaces(List<Race> races) {
        return races.stream().map(BoatRacing::calculateNumberWaysToBeatRecord).reduce(1, (a, b) -> a*b);
    }

    public static int calculateNumberWaysToBeatRecord(Race race) {
        long timeRaceLastsMs = race.timeRaceLastsMs;
        long recordDistanceMm = race.distanceMm;
        int count = 0;
        for(long hold = 1; hold < timeRaceLastsMs; hold++){
            long timeLeft = timeRaceLastsMs - hold;
            long distanceTravelled = hold * timeLeft;
            if(distanceTravelled > recordDistanceMm){
                count++;
            }
        }
        return count;
    }
}
