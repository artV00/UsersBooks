package users_books.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public void createUser(UserEntity user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserEntity updateUser(Long id, UserEntity user) {
        UserEntity update = userRepository.findById(id).orElseThrow(() -> new IllegalStateException("User not found"));
        update.setFirstName(user.getFirstName());
        update.setLastName(user.getLastName());
        update.setEmail(user.getEmail());
        update.setDescription(user.getDescription());

        return userRepository.save(update);
    }
}
