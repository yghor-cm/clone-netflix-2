package api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class MovieAPI {
    private static final String API_KEY = "2613fe13d2a82fa339970e4a44bc886f";
    private static final String API_DNS = "https://api.themoviedb.org/3";

    private final Category[] categories = {
            new Category("trending", "Em Alta", "/trending/all/week?api_key=" + API_KEY + "&language=pt-BR", true),
            new Category("netflixOriginals", "Originais Netflix", "/discover/tv?api_key=" + API_KEY + "&with_networks=213", false),
            new Category("topRated", "Populares", "/movie/top_rated?api_key=" + API_KEY + "&language=pt-BR", false),
            new Category("comedy", "Comédias", "/discover/tv?api_key=" + API_KEY + "&with_genres=35", false),
            new Category("romances", "Romances", "/discover/tv?api_key=" + API_KEY + "&with_genres=10749", false),
            new Category("documentaries", "Documentários", "/discover/tv?api_key=" + API_KEY + "&with_genres=99", false)
    };

    private static MovieAPI instance;

    private MovieAPI() { }

    public static synchronized MovieAPI getInstance() {
        if (instance == null) {
            instance = new MovieAPI();
        }
        return instance;
    }

    public Category[] getCategories() {
        return categories;
    }

    public String getMovies(String path) {
        try {
            String url = API_DNS + path;
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return jsonResponse;
        } catch (IOException e) {
            System.out.println("Error getMovies: " + e.getMessage());
        }
        return null;
    }

    public static class Category {
        public String name;
        public String title;
        public String path;
        public boolean isLarge;

        public Category(String name, String title, String path, boolean isLarge) {
            this.name = name;
            this.title = title;
            this.path = path;
            this.isLarge = isLarge;
        }
    }
}
