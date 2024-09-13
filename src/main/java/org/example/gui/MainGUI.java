package org.example.gui;

import org.example.controller.VideoController;
import org.example.utility.ConsoleTextUtils;

import java.util.Scanner;

public class MainGUI {
    private Scanner sc = new Scanner(System.in);
    VideoController videoController = VideoController.getInstance();
    UserGUI userGUI = UserGUI.getInstance();

    public void mainGUI(){
        mainMenuOptions(mainMenu());
    }


    private int mainMenu(){
        ConsoleTextUtils.printTitle("YOUTUBE ");
        ConsoleTextUtils.printMenuOptions("Videoları görüntüle","Video Ara","Giriş Yap"
        ,"Kayıt Ol","Çıkış");
        return ConsoleTextUtils.getIntUserInput("Seciminiz: ");
    }

    private void mainMenuOptions(int secim){
        switch (secim){
            case 1:
                videoController.getTrendVideos().forEach(System.out::println);
                break;
            case 2:
                String aranacakVideo = ConsoleTextUtils.getStringUserInput("Aramak istediğiniz video başlığını giriniz: ");
                videoController.findVideosByTitle(aranacakVideo).forEach(System.out::println);
                break;
            case 3:
                System.out.println("Secim 1");
                break;
            case 4:
                System.out.println("Secim 1");
                break;
            case 5:
                System.out.println("Çıkış yapılıyor...");
                break;
            default:
                System.out.println("Lütfene geçerli seçim yapınız.");
        }
    }
}
