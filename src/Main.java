import com.umut.videostream.controller.Controller;
import com.umut.videostream.model.Model;
import com.umut.videostream.view.View;

public class Main {

    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}