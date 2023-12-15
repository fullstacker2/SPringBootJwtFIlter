package com.example.springbootjwt.Service;

import com.example.springbootjwt.Configuration.StatusResponse;
import com.example.springbootjwt.ExceptionHandler.MovieException;
import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.Repository.MovieRepo;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
public class MovieServiceTest {
    @Mock
    private MovieRepo movieRepo;
    @InjectMocks
    private MovieService movieService;
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllMovies() {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(new ObjectId("6577e2dab2c0522674b97f39"), "Avengers","1/4/19"));
        movieList.add(new Movie(new ObjectId("6577e2dab2c0512674b97f39"), "Arrival","31/8/16"));
        movieList.add(new Movie(new ObjectId("6537e2dab2c0522674b97f29"), "Oldboy","15/6/13"));

        //mocks
        when(movieRepo.findAll()).thenReturn(movieList);
        List<Movie> movieResult = movieService.getAllMovies();

        assertEquals(3, movieResult.size());
    }

    public void getMovieById() throws MovieException {
        List<Movie> movieList = new ArrayList<Movie>();
        movieList.add(new Movie(new ObjectId("6577e2dab2c0522674b97f39"), "Avengers","1/4/19"));
        movieList.add(new Movie(new ObjectId("6577e2dab2c0512674b97f39"), "Arrival","31/8/16"));
        movieList.add(new Movie(new ObjectId("6537e2dab2c0522674b97f29"), "Oldboy","15/6/13"));

        ObjectId id = new ObjectId("6577e2dab2c0522674b97f39");

        //mocks
        when(movieRepo.findById(id)).thenReturn(Optional.ofNullable(movieList.get(movieList.size()-3)));
        Movie movieResult = movieService.getMovieById(id);

        // assertions
        assertEquals(id, movieResult.getId());
        assertEquals("avengers", movieResult.getName());
        assertEquals(2008,movieResult.getReleaseDate());
    }

    @Test
    public void saveMovie(){

        Movie movie = new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        when(movieRepo.save(movie)).thenReturn(movie);

        Movie savedMovie = movieService.createMovie(movie);

        assertEquals(new ObjectId("636dcf444b7e8832baeb2607"), savedMovie.getId());
        assertEquals("MI1", savedMovie.getName());
        assertEquals("2002-10-10", savedMovie.getReleaseDate());
    }


    @Test
    public void deleteMovieById(){

        Movie movie = new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "MI1", "2002-10-10");

        movieService.deleteMovieById(movie.getId());

        verify(movieRepo, times(1)).deleteById(movie.getId());
    }

}
