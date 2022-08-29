package users_books.user;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();

    UserEntity getUser(long id);

    UserEntity createUser(UserEntity user);

    void deleteUser(long id);

    UserEntity updateUser(long id, UserEntity user);

    void deleteUsers();
}
