package com.example.springbootjwt.Controller;

import com.example.springbootjwt.Service.UserService;
import com.example.springbootjwt.Model.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // creating a user
    @PostMapping(value = "/add-user")
    public String Signup(@RequestBody User user) {
        return userService.signup(user);
    }

    //login user
    @PostMapping(value = "/login")
    public String login(@RequestBody Map<String,Object> map) {
        return userService.login(map.get("email").toString(), map.get("password").toString());
        // alternately we can do userService.login(user.getEmail, user.getPassword), args
        // would be @RequestBody User user
    }

    //get user
    @GetMapping(value = "/get-user")
    public User getUser(HttpServletRequest request) {
        ObjectId userId = (ObjectId) request.getAttribute("userId");
        return userService.getUser(userId);
    }
}
