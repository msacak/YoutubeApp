package org.example.service;

import org.example.dto.request.UserRequestDTO;
import org.example.dto.response.UserResponseDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.HibernateConnection;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }


    public Optional<UserResponseDTO> save(UserRequestDTO userRequestDto) { //parametre olarak requestdto, geri dönüş olarak responseDto
        User user;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        try {
            user = new User();
            user.setUsername(userRequestDto.getUsername());
            user.setEmail(userRequestDto.getEmail());
            user.setPassword(userRequestDto.getPassword());
            user.setName(userRequestDto.getName());
            user.setSurname(userRequestDto.getSurname());
            userRepository.save(user);
            ConsoleTextUtils.printSuccessMessage(user.getUsername() + " adlı kullanıcı başarıyla kaydedildi.");
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setUsername(user.getUsername());


        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("Kullanıcı kaydedilirken bir hata oluştu.");
        }
        return Optional.of(userResponseDTO);
    }


    public void update(UserRequestDTO userRequestDto) {
        try {
            Optional<User> user = userRepository.findByUsername(userRequestDto.getUsername());
            System.out.println("User = "+user);
            if(user.isPresent()) {
                user.get().setName(userRequestDto.getName());
                user.get().setSurname(userRequestDto.getSurname());
                user.get().setSurname(userRequestDto.getSurname());
                user.get().setEmail(userRequestDto.getEmail());
                user.get().setPassword(userRequestDto.getPassword());
                userRepository.update(user.get());
            }
        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserService: Kullanıcı update edilirken hata oluştu.");
        }
    }


    public void delete(Long id) {
        try {
            boolean isDeleted = userRepository.delete(id);
            if(isDeleted){
                ConsoleTextUtils.printSuccessMessage("Kullanıcı başarıyla  silindi.");
            }

        } catch (Exception e) {
            ConsoleTextUtils.printErrorMessage("UserService: Kullanıcı silinirken hata oluştu.");
        }
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    public Optional<User> findByUsernameAndPassword(String username, String password) {

        Optional<User> optUser = userRepository.findByUsernameAndPassword(username, password);
        optUser.ifPresentOrElse(u -> System.out.println("Hoşgeldiniz " + u.getUsername()),
                () -> System.out.println("Kullanıcı adı veya şifre hatalı."));
        return optUser;
    }

    public boolean isUsernameExist(String username) {
        boolean usernameExist = userRepository.isUsernameExist(username);
        if (usernameExist) {
            ConsoleTextUtils.printErrorMessage("Kullanıcı adı zaten kayıtlı.");
        }
        return usernameExist;
    }

    public boolean isMailExist(String email) {
        boolean mailExist = userRepository.isMailExist(email);
        if (mailExist) {
            ConsoleTextUtils.printErrorMessage("Kullanıcı maili zaten kayıtlı.");
        }
        return mailExist;
    }
}
