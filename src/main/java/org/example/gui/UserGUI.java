package org.example.gui;

public class UserGUI {
    private static UserGUI instance;

    private UserGUI(){

    }
    public static UserGUI getInstance(){
        if(instance == null){
            instance = new UserGUI();
        }
        return instance;
    }
}
