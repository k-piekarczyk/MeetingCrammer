public class Timespan {
    private int start;
    private int end;

    Timespan(String start, String end) {
        // TODO: Dodać sprawdzanie poprawności stringów
        String[] startSplit = start.trim().split(":");
        String[] endSplit = end.trim().split(":");

        this.start = (Integer.parseInt(startSplit[0]) * 60) + Integer.parseInt(startSplit[1]);
        this.end = (Integer.parseInt(endSplit[0]) * 60) + Integer.parseInt(endSplit[1]);
    }

    Timespan(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
