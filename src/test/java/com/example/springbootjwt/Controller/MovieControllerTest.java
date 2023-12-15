package com.example.springbootjwt.Controller;

import com.example.springbootjwt.Model.Movie;
import com.example.springbootjwt.SPringBootJwtApplication;
import com.example.springbootjwt.SPringBootJwtApplicationTests;
import com.example.springbootjwt.Service.MovieServiceTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SPringBootJwtApplicationTests.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieControllerTest {
    // For mocking the web layer
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext movieContext;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(movieContext).build();
    }

    public static String asJson(final Object object) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final String jsonContent = objectMapper.writeValueAsString(object);
            System.out.println(jsonContent);
            return jsonContent;
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void verifyGetMovieById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/movie/get-movie-by-id/636e22864327035c5f02187b")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("636e22864327035c5f02187b"))
                .andDo(print());
    }

    @Test
    public void verfiySaveMovie_EXCEPTION() throws Exception{
        Movie movie = new Movie(new ObjectId("636dcf444b7e8832baeb2607"), "abc", "2002");

        mockMvc.perform(MockMvcRequestBuilders.post("/movie/add-movie")
                        .content(asJson(movie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("PAYLOAD MALFORMED. OBJECT ID MUST NOT BE DEFINED"))
                .andDo(print());
    }



    @Test
    public void verifyUpdateMovie() throws Exception{
        Movie movie = new Movie(new ObjectId("636dce61bfff8e1d3db94ef1"), "Mission Impossible 1", "2002-10-10");

        mockMvc.perform(MockMvcRequestBuilders.patch("/movie/update-movie")
                        .content(asJson(movie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value("636dce61bfff8e1d3db94ef1"))
                .andExpect(jsonPath("$.name").value("Mission Impossible 1"))
                .andDo(print());
    }

    @Test
    public void verifyUpdateMovie_EXCEPTION() throws Exception{
        Movie movie = new Movie(new ObjectId("636dce61bfff8e1d3db94ef1"), "Mission Impossible 1", "2002-10-10");

        mockMvc.perform(MockMvcRequestBuilders.patch("/movie/update-movie")
                        .content(asJson(movie))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Movie DOESN'T EXISTS"))
                .andDo(print());
    }



    @Test
    public void verifyDeleteById() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/movie/delete-movie-by-id/636dcf444b7e8832baeb2607")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Successfully Deleted !!"))
                .andDo(print());
    }

    @Test
    public void verifyDeleteById_EXCEPTION() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/movie/delete-movie-by-id/636dcf444b7e8832baeb2607")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Movie DOESN'T Exists"))
                .andDo(print());
    }
}
