package org.kpiekarczyk;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kpiekarczyk.Calendar;

import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Incorrect number of arguments! Correct number is 3.");
            return;
        }

        JSONParser parser = new JSONParser();
        JSONObject parsedCalendar1 = null, parsedCalendar2 = null;
        try {
            parsedCalendar1 = (JSONObject) parser.parse(new FileReader(args[0]));
            parsedCalendar2 = (JSONObject) parser.parse(new FileReader(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String[] meetingDurationSplit = args[2].trim().split(":");
        int meetingDuration = (Integer.parseInt(meetingDurationSplit[0]) * 60) + Integer.parseInt(meetingDurationSplit[1]);

        Calendar calendar1 = new Calendar(parsedCalendar1);
        Calendar calendar2 = new Calendar(parsedCalendar2);

        List<Timespan> proposedTimes = Calendar.cramAMeetingIn(calendar1, calendar2, meetingDuration);

        System.out.println(Arrays.toString(proposedTimes.toArray()));
    }
}
