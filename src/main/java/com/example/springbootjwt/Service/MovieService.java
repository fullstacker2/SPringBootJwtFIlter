package com.example.springbootjwt.Service;

import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Repository.MovieRepo;
import org.bson.types.ObjectId;
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

    //get movie by id
    public Movie getMovieById(ObjectId id) {
        Optional<Movie> optMovie = movieRepo.findById(id);
        return optMovie.orElseGet(optMovie::get);
    }

    //get all movies
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

    // delete movie by id
    public void deleteMovieById(ObjectId id) {
        Optional<Movie> optMovie = movieRepo.findById(id);
        if(optMovie.isEmpty()) {
            throw new RuntimeException("Movie id" + id + "doesn't exist");
        }
        movieRepo.deleteById(id);
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
    public void removeAllMovies() { movieRepo.deleteAll();}

    // get number of movies
    public int countMovies() {
        return movieRepo.findAll().size();
    }

}
