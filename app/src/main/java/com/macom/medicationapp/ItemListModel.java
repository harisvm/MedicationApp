package com.macom.medicationapp;

public class ItemListModel {
    private String title, description;

    public ItemListModel(String title, String description) {
        this.title = title;
        this.description = description;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
