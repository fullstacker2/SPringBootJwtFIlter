package com.example.springbootjwt.Service;

import com.example.springbootjwt.Model.User;
import com.example.springbootjwt.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;
    private TokenService tokenService;
    @Autowired
    public UserService(UserRepo userRepo, TokenService tokenService) {
        this.userRepo = userRepo;
        this.tokenService = tokenService;
    }

    // Get a user
    public User getUser(ObjectId id) {
        Optional<User> optionalUser = userRepo.findById(id);
        return optionalUser.orElseGet(optionalUser::get);
    }

    // list users
    public List<User> getUsers() {
        List<User> getUsers = userRepo.findAll();
        return getUsers;
    }

    // Signup a user
    public String signup(User user) {
        User savedUser = userRepo.save(user);
        return "{" +
                "\"message\":"+"Successfully created user\",\n"+
                "\"data\":"+savedUser+",\n"+
            "}";
    }
    
    // login a user
    public String login(String email, String password) {
        List<User> foundUsers = userRepo.getUserByEmail(email);
        if(foundUsers.isEmpty()) {
            return "Authentication failed: User not found";
        } else if (!foundUsers.get(0).getPassword().equals(password)) {
            return "Password incorrect";
        }
        return "{\n" +
                "\"message\":"+"\" Successfully Logged-in\",\n"+
                "\"data\": {\n"+" Name : "+foundUsers.get(0).getName()+",\n"+
                "Email : "+foundUsers.get(0).getEmail()+"\n"+
                "\"token\":\""+tokenService.createToken(foundUsers.get(0).getId())+"\"" +
                "}";
    }

    public String getUserByAgeLessThan(int age)
    {
        var optUser = userRepo.getUserLessThanAge(age);
        if (optUser.isEmpty())
        {
            return "No users found";
        }

        return "{'message': User Found.\n \t 'data':"+ optUser.toString()+" }";
    }

    public String getUserByNameOrAge(String name, String email)
    {
        var optUser = userRepo.getUserByNameOrEmail(name,email);
        if (optUser.isEmpty())
        {
            return "No users found";
        }
        return "{'message': User Found.\n \t 'data':"+ optUser.toString()+" }";
    }

}
