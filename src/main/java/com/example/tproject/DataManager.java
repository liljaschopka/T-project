package com.example.tproject;

import model.User;

public class DataManager {
    private static DataManager instance = new DataManager();
    private User currentUser;

    private DataManager() {}

    public static DataManager getInstance() {
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
