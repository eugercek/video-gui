import com.google.gson.Gson;
import com.umut.videostream.controller.Controller;
import com.umut.videostream.model.Model;
import com.umut.videostream.model.User;
import com.umut.videostream.model.enums.EMovieGenre;
import com.umut.videostream.model.repository.tmdb.MovieTMDBRepository;
import com.umut.videostream.model.repository.tmdb.TMDBResponseModel;
import com.umut.videostream.model.services.NetworkOperations;
import com.umut.videostream.view.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        Model model = new Model("./Secret/users.txt");
        View view = new View();
        Controller controller = new Controller(model, view);
    }
}