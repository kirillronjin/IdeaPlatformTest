import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        /**
         * create new object mapper
         */
        ObjectMapper objectMapper = new ObjectMapper();

        /**
         * read tickets
         */
        Tickets tickets = objectMapper.readValue(new File("src/main/resources/tickets.json"), Tickets.class);

        /**
         * origin city
         */
        String origin = "Владивосток";

        /**
         * destination city
         */
        String destination = "Тель-Авив";

        /**
         * calculate and print the average and 90th percentile of flight duration from one city to another
         */
        calculateAvgFlightDurationAnd90thPercentile(tickets, origin, destination).forEach((k,v) -> System.out.println(v));
    }

    /**
     * calculates the average and 90th percentile of flight duration from one city to another
     * @param tickets Array of tickets
     * @param origin Origin city
     * @param destination Destination city
     * @return result map
     * @throws ParseException
     */
    public static HashMap<String, String> calculateAvgFlightDurationAnd90thPercentile(Tickets tickets,
                                                                                      String origin,
                                                                                      String destination)
            throws ParseException {
        /**
         * create result map
         */
        HashMap<String, String> result = new HashMap<>();

        /**
         * create sum of flights duration
         */
        long sumDuration = 0;

        /**
         * create list of flights duration
         */
        ArrayList<Long> durations = new ArrayList<>();

        /**
         * checking cities on tickets
         */
        for (Ticket t : tickets.getTickets()) {
            if (t.getOrigin_name().equals("Владивосток") && t.getDestination_name().equals("Тель-Авив")) {
                long duration = t.getDuration();
                sumDuration += duration;
                durations.add(duration);
            }
        }

        /**
         * calculate average flight duration
         * and put it in the result map
         */
        long avgTime = sumDuration / durations.size();
        int hoursAvg = (int) (avgTime / 3600000);
        int minutesAvg = (int) ((avgTime % 3600000) / 60000);
        result.put("averageDuration", String.format("The average flight duration from %s to %s: %d hours %d minutes",
                origin, destination, hoursAvg, minutesAvg));

        /**
         * calculate 90th percentile of flight duration
         * and put it in the result map
         */
        Collections.sort(durations);
        long percentile90 = durations.get(90 * durations.size() / 100 - 1);
        int hoursPer90 = (int) (percentile90 / 3600000);
        int minutesPer90 = (int) ((percentile90 % 3600000) / 60000);
        result.put("90thPercentile", String.format("90th Percentile of flight duration from %s to %s: %d hours %d minutes",
                origin, destination, hoursPer90, minutesPer90));

        return result;
    }
}

