import java.util.ArrayList;
import java.util.List;

public class MovieBookingSystem {
    List<Screen> screens;
    List<Show> shows;
    List<Movie> movies;
    List<User> users;

    private static MovieBookingSystem instance;
    
    private MovieBookingSystem() {
    this.screens = new ArrayList<>();
    this.shows = new ArrayList<>();
    this.movies = new ArrayList<>();
    this.users = new ArrayList<>();
}

    public static MovieBookingSystem getInstance() {
        if (instance == null) {
            instance = new MovieBookingSystem();
        }
        return instance;
    }

    public void addMovie(Movie movie) {
         this.movies.add(movie);
    }

    public Show addShow(int id, int time, Movie movie, Screen screen) {
        Show show = new Show(id, time, movie, screen);
        shows.add(show);
        return show;
    }

    public User createUser(int id, String name) {
        User user = new User(id, name);
        users.add(user);
        return user;
    }

}
