package org.example.gui;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
//import org.example.controller.VideoController;
import org.example.controller.UserController;
import org.example.controller.VideoController;
import org.example.dto.request.UserRequestDTO;
import org.example.entity.User;
import org.example.utility.ConsoleTextUtils;
import org.example.utility.HibernateConnection;

import java.util.Optional;


public class MainGUI {
    VideoController videoController;
    UserGUI userGUI;
    private static MainGUI instance;


    private MainGUI() {
        videoController = VideoController.getInstance();
        userGUI = UserGUI.getInstance();

    }

    public static MainGUI getInstance() {
        if (instance == null) {
            instance = new MainGUI();
        }
        return instance;
    }


    public void mainGUI() {
        mainMenuOptions(mainMenu()); // Uygulama içindeki ana işlemler
    }


    private int mainMenu() {
        ConsoleTextUtils.printTitle("YOUTUBE ");
        ConsoleTextUtils.printMenuOptions("Videoları görüntüle", "Video Ara", "Giriş Yap"
                , "Kayıt Ol", "Çıkış");
        return ConsoleTextUtils.getIntUserInput("Seciminiz: ");
    }

    private void mainMenuOptions(int secim) {
        switch (secim) {
            case 1:
                videoController.getTrendVideos().forEach(System.out::println);
                break;
            case 2:
                String aranacakVideo = ConsoleTextUtils.getStringUserInput("Aramak istediğiniz video başlığını giriniz: ");
                videoController.findVideosByTitle(aranacakVideo).forEach(System.out::println);
                break;
            case 3:
                UserGUI.getInstance().userModuleMenuOptions(UserGUI.getInstance().userModuleMenu());
                mainMenuOptions(mainMenu());
                break;
            case 4:
                register();
                mainMenuOptions(mainMenu());
                break;
            case 5:
                System.out.println("Çıkış yapılıyor...");
                break;
            default:
                System.out.println("Lütfen geçerli bir seçim yapınız.");
        }
    }

    private User register() {
        String mail, username;
        String ad = ConsoleTextUtils.getStringUserInput("Lütfen adınızı giriniz : ");
        String soyad = ConsoleTextUtils.getStringUserInput("Lütfen soyadınızı giriniz : ");
        while (true) {
            mail = ConsoleTextUtils.getStringUserInput("Lütfen mail adresinizi giriniz : ");
            if (!UserController.getInstance().isMailExist(mail)) break;

        }
        while (true) {
            username = ConsoleTextUtils.getStringUserInput("Lütfen kullanıcı adınızı giriniz : ");
            if (!UserController.getInstance().isUsernameExist(username)) break;
        }
        String password = ConsoleTextUtils.getStringUserInput("Lütfen sifrenizi giriniz : ");
        UserController.getInstance().save(new UserRequestDTO(ad, soyad, mail, username, password));
        return UserController.getInstance().findByUsernameAndPassword(username, password).get();
    }
}
