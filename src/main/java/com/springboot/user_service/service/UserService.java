package com.springboot.user_service.service;

import com.springboot.user_service.dto.UserDTO;
import com.springboot.user_service.entity.User;
import com.springboot.user_service.exception.UserNotFoundException;
import com.springboot.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create( UserDTO dto) {
        User user = new User(dto.getName(), dto.getEmail());
        return repo.save(user);
    }

    public User getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User update(Long id, UserDTO dto) {
        User existing = getById(id);
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
