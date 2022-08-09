package users_books.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{id}")
    public UserEntity getUser(@PathVariable(value = "id") long id) {
        return userService.getUser(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
    }

    @PostMapping
    public Long createUser(@RequestBody UserEntity user) {
        userService.createUser(user);
        return user.getId();
    }

    @PutMapping(path = "{id}")
    public UserEntity updateUser(@PathVariable(value = "id") long id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping
    public void deleteUsers() {
        userService.deleteUsers();
    }
}
