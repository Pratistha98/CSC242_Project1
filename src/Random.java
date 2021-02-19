import java.util.Scanner;

public class Random implements Player{
    public Random() {
    }

    public String get_action(State s) {
        Actions alist = new Actions(s);
        return alist.getActions().get(0);
    }
}
