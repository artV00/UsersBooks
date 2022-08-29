package users_books.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users/")
@Tag(name = " 'User Books' Controller",
        description = "This REST Controller provide services to manage Users and Books in the 'User Books' app")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides all users available in the 'User Books' app")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Provides user details for the supplied user id from the 'User Books' app")
    public UserEntity getUser(@PathVariable(value = "id") long id) {
        return userService.getUser(id);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Deletes the user details for the supplied user id from the 'User Books' app")
    public void deleteUser(@PathVariable(value = "id") long id) {
        userService.deleteUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Creates a new user in the 'User Books' app")
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.createUser(user);
    }

    @PutMapping(path = "{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Updates the user details in the 'User Books' app for the supplied user id")
    public UserEntity updateUser(@PathVariable(value = "id") long id, @RequestBody UserEntity user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Deletes all users from the 'User Books' app")
    public void deleteUsers() {
        userService.deleteUsers();
    }
}
