package com.example.springbootjwt.Controller;

import com.example.springbootjwt.ExceptionHandler.IdException;
import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Model.PayloadValidation;
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
    public String saveMovie(@RequestBody Movie movie) throws IdException{
        if(!PayloadValidation.payloadVal(movie)) {
            throw new IdException("ObjectId not defined");
        }
            return movieService.createMovie(movie);
    }

    // get all movies
    @GetMapping("get-movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PutMapping("/update/{id}")
    public String updateEmployee(@RequestBody Movie movie, @PathVariable String id) {return movieService.updateMovie(id,movie);}

    // remove all movies
    @GetMapping("del-movies")
    public void removeAllMovies() {
        movieService.removeAllMovies();
    }
}
