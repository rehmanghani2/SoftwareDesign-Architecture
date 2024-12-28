package main.java.com.mycompany.userservice;

// import org.springframework.beans.factory.annotation.Autowired;
 //import org.springframework.web.find.annotation.*;
// import com.mycompany.User.UserRepository;

import java.util.List;

// @RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public List getAllUsers(){
        return userRepository.findAll();
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    // additional CRUD operations
}
