public class Timespan {
    private int start;
    private int end;
    private String startString;
    private String endString;

    Timespan(String start, String end) {
        // TODO: Dodać sprawdzanie poprawności stringów
        String[] startSplit = start.trim().split(":");
        String[] endSplit = end.trim().split(":");

        this.start = (Integer.parseInt(startSplit[0]) * 60) + Integer.parseInt(startSplit[1]);
        this.end = (Integer.parseInt(endSplit[0]) * 60) + Integer.parseInt(endSplit[1]);
        this.startString = start;
        this.endString = end;
    }

    Timespan(int start, int end) {
        this.start = start;
        this.end = end;

        int startMins = start % 60;
        int endMins = end % 60;

        int startHours = (start - startMins) / 60;
        int endHours = (end - endMins) / 60;

        this.startString = String.format("%02d:%02d", startHours, startMins);
        this.endString = String.format("%02d:%02d", endHours, endMins);
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
}
