import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("callendar2.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray meetings = (JSONArray) jsonObject.get("planned_meeting");

            Iterator<JSONObject> iterator = meetings.iterator();
            while (iterator.hasNext()) {
                JSONObject current = iterator.next();
                System.out.println(current.get("start") + " - " + current.get("end"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
