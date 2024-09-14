package org.example.gui;

import org.example.controller.UserController;
import org.example.controller.VideoController;
import org.example.dto.request.UserRequestDTO;
import org.example.dto.request.VideoSaveRequestDTO;
import org.example.entity.User;
import org.example.entity.Video;
import org.example.service.UserService;
import org.example.utility.ConsoleTextUtils;

import java.util.Optional;

public class UserGUI {
    private static UserGUI instance;
    private UserController userController;
    private User user;

    private UserGUI() {
        userController = UserController.getInstance();
    }

    public static UserGUI getInstance() {
        if (instance == null) {
            instance = new UserGUI();
        }
        return instance;
    }

    public int userModuleMenu() {
        ConsoleTextUtils.printMenuOptions("Giriş Yap", "Ana menüye geri dön");
        return ConsoleTextUtils.getIntUserInput("Seçiminiz: ");
    }

    public int userModuleMenuOptions(int secim) {
        switch (secim) {
            case 1:
                user = doLogin();
                if (user != null) {
                    userMainMenuOptions(userMainMenu());
                }
                break;
            case 2:
                ConsoleTextUtils.printSuccessMessage("Ana menüye dönüyorsunuz");
                break;
        }
        return 0; // ???
    }


    private int userMainMenu() {
        ConsoleTextUtils.printTitle("KULLANICI MENÜSÜ");
        ConsoleTextUtils.printMenuOptions("Video ara", "Video kaydet", "Videolarımı görüntüle",
                "Trend videoları göster", "Hesap ayarlarım", "Hesabımdan çık");
        return ConsoleTextUtils.getIntUserInput("Seçiminiz: ");
    }

    private int userMainMenuOptions(int secim) {
        switch (secim) {
            case 1:
                VideoController.getInstance()
                        .findVideosByTitle(ConsoleTextUtils.getStringUserInput("Video başlığı giriniz: "));
                userMainMenuOptions(userMainMenu());
                break;
            case 2:
                VideoController.getInstance().save(saveVideo());
                userMainMenuOptions(userMainMenu());
                break;
            case 3:
                VideoController.getInstance().getVideosOfUser(user).forEach(System.out::println);
                userMainMenuOptions(userMainMenu());
                break;
            case 4:
                VideoController.getInstance().getTrendVideos().forEach(System.out::println);
                userMainMenuOptions(userMainMenu());
                break;
            case 5:
                //Kullanıcı hesap ayarları ve düzenlemeleri menüsü();
                userMainMenuOptions(userMainMenu());
                break;
            case 6:
                user = null;
                return userModuleMenuOptions(2);

        }
        return secim;
    }

    private VideoSaveRequestDTO saveVideo() {
        Video video = new Video();
        String title = ConsoleTextUtils.getStringUserInput("Video başlık: ");
        String description = ConsoleTextUtils.getStringUserInput("Video açıklaması: ");
        video.setTitle(title);
        video.setDescription(description);
        video.setUser(user);
        return new VideoSaveRequestDTO(user.getUsername(), user.getPassword(), title, description);
    }


    private User doLogin() {
        String username = ConsoleTextUtils.getStringUserInput("Kullanıcı adı: ");
        String password = ConsoleTextUtils.getStringUserInput("Şifre: ");
        Optional<User> userOptional = userController.findByUsernameAndPassword(username, password);
        return userOptional.orElse(null);
    }


}
