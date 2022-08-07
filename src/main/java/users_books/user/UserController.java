package users_books.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/user/{id}")
    public UserEntity getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping(path = "/user/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping(path = "/user")
    public Long createUser(@RequestBody UserEntity user) {
        userService.createUser(user);
        return user.getId();
    }

    @PutMapping(path = "/user/{id}")
    public UserEntity updateUser(@PathVariable(value = "id") Long id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }
}
