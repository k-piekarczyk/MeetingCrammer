import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Calendar {
    private Timespan workHours;

    private List<Timespan> meetings = new ArrayList<Timespan>();

    @SuppressWarnings("unchecked")
    Calendar(JSONObject cal) {
        JSONObject workHours = (JSONObject) cal.get("working_hours");
        this.workHours = new Timespan((String) workHours.get("start"), (String) workHours.get("end"));

        JSONArray meetings = (JSONArray) cal.get("planned_meeting");

        for (JSONObject m : (Iterable<JSONObject>) meetings) {
            this.meetings.add(new Timespan((String) m.get("start"), (String) m.get("end")));
        }
    }

    public static List<Timespan> cramAMeetingIn(Calendar c1, Calendar c2, final int meetingDuration) {
        List<Timespan> closedTimes = new ArrayList<Timespan>();

        closedTimes.addAll(c1.meetings);
        closedTimes.addAll(c2.meetings);

        Collections.sort(closedTimes);

        boolean done = false;
        int i = 0;
        while (!done) {
            if (i >= closedTimes.size() - 1) {
                done = true;
                break;
            }
            while (Timespan.checkOverlap(closedTimes.get(i), closedTimes.get(i + 1))) {
                closedTimes.set(i, Timespan.merge(closedTimes.get(i), closedTimes.get(i + 1)));
                closedTimes.remove(i + 1);

                if (i == closedTimes.size() - 1) {
                    done = true;
                    break;
                }
            }
            i++;
        }

        List<Timespan> openTimes = new ArrayList<Timespan>();

        int earliestTime = Math.max(c1.workHours.getStart(), c2.workHours.getStart());
        int latestTime = Math.min(c1.workHours.getStart(), c2.workHours.getStart());

        int nStart = earliestTime;
        int nEnd;
        for (Timespan t : closedTimes) {
            nEnd = t.getStart();
            openTimes.add(new Timespan(nStart, nEnd));
            nStart = t.getEnd();
        }
        openTimes.add(new Timespan(nStart, latestTime));

        openTimes.removeIf(t -> t.getDuration() < meetingDuration);

        return openTimes;
    }
}
