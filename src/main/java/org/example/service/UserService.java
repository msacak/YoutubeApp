package org.example.service;

import org.example.dto.request.UserRequestDto;
import org.example.dto.response.UserResponseDTO;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.ICRUD;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }


    public Optional<UserResponseDTO> save(UserRequestDto userRequestDto) { //parametre olarak requestdto, geri dönüş olarak responseDto
        User user;
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        try {
            if (userRepository.isUsernameExist(userRequestDto.getUsername()) &&
                    userRepository.isMailExist(userRequestDto.getEmail())) {
                ConsoleTextUtils.printErrorMessage("Kullanıcı adı veya mail zaten mevcut.");
            }
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


    public void update(UserRequestDto userRequestDto) {
       try {
           if (userRepository.isUsernameAndMailExist(userRequestDto.getUsername(), userRequestDto.getEmail())) {
               Optional<User> userOpt = userRepository.findByUsername(userRequestDto.getUsername());
               if (userOpt.isPresent()) {
                   User user = userOpt.get();
                   user.setName(userRequestDto.getName());
                   user.setSurname(userRequestDto.getSurname());
                   user.setEmail(userRequestDto.getEmail());
                   user.setPassword(userRequestDto.getPassword());
                   user.setUsername(userRequestDto.getUsername());
                   userRepository.update(user);
                   ConsoleTextUtils.printSuccessMessage(user.getUsername()+" adlı kullanıcı başarıyla güncellendi.");
               }
           }
       }
       catch (Exception e) {
           ConsoleTextUtils.printErrorMessage("UserService: Kullanıcı update edilirken hata oluştu.");
       }
    }


    public void delete(Long id) {
       try{
           userRepository.delete(id);
           ConsoleTextUtils.printSuccessMessage("Kullanıcı başarıyla  silindi.");
       }
       catch (Exception e) {
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
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
