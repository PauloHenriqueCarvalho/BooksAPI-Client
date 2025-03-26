package org.example.services;

import org.example.entitys.user.User;
import org.example.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //CRUD
    // CREATE - Criar um novo usuário
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ - Encontrar um usuário por ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // READ - Encontrar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // UPDATE - Atualizar informações de um usuário
    public User updateUser(Integer id, User userDetails) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(userDetails.getName());
            user.setLogin(userDetails.getLogin());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        }
        return null; // Ou você pode lançar uma exceção
    }

    // DELETE - Deletar um usuário por ID
    public boolean deleteUser(Integer id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false; // Ou você pode lançar uma exceção
    }

    // READ - Encontrar usuário por login
    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }



}
