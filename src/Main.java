import controller.Convert;
import controller.Open;
import view.View;

public class Main {

    public static void main(String[] args) {
        View view = new View();
        Open open = new Open(view);
        Convert convert = new Convert(view);
    }
}
