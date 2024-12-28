package com.mycompany.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 // import org.springframework.data.mongodb.repository.MongoRepository;
/**
 *
 * @author Ghani
 */



// public interface UserRepository extends MongoRepository{
public class UserRepository{
     private List<User> users = new ArrayList<>();

   // @Override
    public List<User> findAll() {
     //   return Collections.unmodifiableList(users); // Return an unmodifiable copy to prevent accidental modification
            return users;
    }

   // @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    // Implement other CRUD operations (findById, update, delete) as needed
}
