import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Incorrect number of arguments! Correct number is 3.");
            return;
        }

        JSONParser parser = new JSONParser();
        JSONObject calendar1 = null, calendar2 = null;
        try {
            calendar1 = (JSONObject) parser.parse(new FileReader(args[0]));
            calendar2 = (JSONObject) parser.parse(new FileReader(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String[] meetingDurationSplit = args[2].trim().split(":");
        int meetingDuration = (Integer.parseInt(meetingDurationSplit[0]) * 60) + Integer.parseInt(meetingDurationSplit[1]);


    }
}
