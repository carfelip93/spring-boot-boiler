package com.rocketicg.app.domain.user.port;

import com.rocketicg.app.domain.user.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String username, String email, String password, String fullName);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

    User updateProfile(Long id, String fullName, String email);

    User changePassword(Long id, String newPassword);

    User deactivateUser(Long id);

    User activateUser(Long id);

    void deleteUser(Long id);
}