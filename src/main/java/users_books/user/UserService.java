package users_books.user;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();

    UserEntity getUser(Long id);

    void createUser(UserEntity user);

    void deleteUser(Long id);

    UserEntity updateUser(Long id, UserEntity user);

    void deleteUsers();
}
