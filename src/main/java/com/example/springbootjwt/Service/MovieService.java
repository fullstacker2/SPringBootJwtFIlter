package com.example.springbootjwt.Service;

import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    //create movie
    public String createMovie(Movie movie) {
        Movie savedMovie = movieRepo.save(movie);
        return "{" +
                "\"message\":"+"Successfully created movie\",\n"+
                "\"data\":"+savedMovie+",\n"+
                "}";
    }

    //get all movies
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    public void removeAllMovies() { movieRepo.deleteAll();}

    // get number of movies
    public int countMovies() {
        return movieRepo.findAll().size();
    }

}
