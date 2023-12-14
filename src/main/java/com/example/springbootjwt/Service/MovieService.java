package com.example.springbootjwt.Service;

import com.example.springbootjwt.Configuration.StatusResponse;
import com.example.springbootjwt.ExceptionHandler.MovieException;
import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Repository.MovieRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StatusResponse> createMovie(Movie movie) {
        Movie savedMovie = movieRepo.save(movie);
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setStatusCode(200);
        statusResponse.setStatusMsg("Movie created successfully");
        /*
        return "{" +
                "\"message\":"+"Successfully created movie\",\n"+
                "\"data\":"+savedMovie+",\n"+
                "}";

        */
        return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.CREATED);
    }

    //get movie by id
    public Movie getMovieById(ObjectId id) throws MovieException {
        Optional<Movie> optMovie = movieRepo.findById(id);
        if(optMovie.isEmpty() || optMovie == null) {
            throw new MovieException("Movie doesn't exist");
        }
        return optMovie.orElseGet(optMovie::get);
    }

    //get all movies
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    // update Movie
    public String updateMovie(String id, Movie movie) {
        ObjectId objectId = new ObjectId(id);
        Optional<Movie> optMovie = movieRepo.findById(objectId);
        if(optMovie.isEmpty()) {
            throw new RuntimeException("given course doesn't exist");
        }
        Movie movieRec = optMovie.get();
        if (movie.getName() != null)
            movieRec.setName(movie.getName());
        if (movie.getReleaseDate() != null)
            movieRec.setReleaseDate(movie.getReleaseDate());

        movieRepo.save(movieRec);
        return "{" +
                "\"message\":"+"Successfully updated movie\",\n"+
                "\"data\":"+movieRepo+",\n"+
                "}";
    }

    // delete movie by id
    public void deleteMovieById(ObjectId id) {
        Optional<Movie> optMovie = movieRepo.findById(id);
        if(optMovie.isEmpty()) {
            throw new RuntimeException("Movie id" + id + "doesn't exist");
        }
        movieRepo.deleteById(id);
    }

    // remove all movies
    public void removeAllMovies() { movieRepo.deleteAll();}

    // get number of movies
    public int countMovies() {
        return movieRepo.findAll().size();
    }

}
