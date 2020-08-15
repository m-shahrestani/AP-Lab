public class Main {
    public static void main(String[] args) {
        ClockDisplay clock = new ClockDisplay();
        clock.setTime(14,59,59);
        clock.timeTick();
        System.out.println(clock.getTime());
    }
}
