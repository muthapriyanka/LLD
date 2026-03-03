public class Movie {
    String name;
    String genre;
    String language;
    int duration; // in minutes

    public Movie(String name, String genre, String language, int duration) {
        this.name = name;
        this.genre = genre;
        this.language = language;
        this.duration = duration;
    }
    public String getname() {
        return name;
    }
    public int getduration() {
        return duration;
    }
}
