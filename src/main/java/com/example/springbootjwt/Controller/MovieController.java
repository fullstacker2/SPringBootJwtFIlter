package com.example.springbootjwt.Controller;

import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // add a movie
    @PostMapping("/add")
    public String saveMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    // get all movies
    @GetMapping("get-movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // remove all movies
    @GetMapping("del-movies")
    public void removeAllMovies() {
        movieService.removeAllMovies();
    }
}
