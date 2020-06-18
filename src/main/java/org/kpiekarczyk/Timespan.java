package org.kpiekarczyk;

import org.json.simple.JSONObject;

public class Timespan implements Comparable<Timespan> {
    private int start;
    private int end;
    private int duration;
    private String startString;
    private String endString;

    Timespan(String start, String end) {
        // TODO: Dodać sprawdzanie poprawności stringów
        String[] startSplit = start.trim().split(":");
        String[] endSplit = end.trim().split(":");

        this.start = (Integer.parseInt(startSplit[0]) * 60) + Integer.parseInt(startSplit[1]);
        this.end = (Integer.parseInt(endSplit[0]) * 60) + Integer.parseInt(endSplit[1]);
        this.duration = this.end - this.start;
        this.startString = start;
        this.endString = end;
    }

    Timespan(int start, int end) {
        if (end < start) end = start;

        this.start = start;
        this.end = end;
        this.duration = this.end - this.start;
        int startMins = start % 60;
        int endMins = end % 60;

        int startHours = (start - startMins) / 60;
        int endHours = (end - endMins) / 60;

        this.startString = String.format("%02d:%02d", startHours, startMins);
        this.endString = String.format("%02d:%02d", endHours, endMins);
    }

    Timespan(JSONObject in) {
        this((String) in.get("start"), (String) in.get("end"));
    }

    public static boolean checkOverlap(Timespan t1, Timespan t2) {
        return t1.end >= t2.start;
    }

    public static Timespan merge(Timespan t1, Timespan t2) {
        return new Timespan(Math.min(t1.start, t2.start), Math.max(t1.end, t2.end));
    }

    public String toString() {
        return String.format("[\"%s\", \"%s\"]", startString, endString);
    }

    @Override
    public int compareTo(Timespan o) {
        if (this.start < o.start) return -1;
        if (this.start == o.start) {
            if (this.duration < o.duration) return -1;
            else if (this.duration == o.duration) return 0;
        }
        return 1;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public String getStartString() {
        return startString;
    }

    public String getEndString() {
        return endString;
    }

    public int getDuration() {
        return duration;
    }
}
