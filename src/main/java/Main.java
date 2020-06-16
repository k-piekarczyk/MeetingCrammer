public class Main {
    public static void main(String[] args) {
        Timespan t = new Timespan("03:15", "11:12");
        System.out.println(t.getStartString() + " - " + t.getStart());
        System.out.println(t.getEndString() + " - " + t.getEnd());
    }
}
