package com.example.caycanh.Frame.model;

public class Search {
    int id;
    String nameSearch;

    public Search(int id, String nameSearch) {
        this.id = id;
        this.nameSearch = nameSearch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }
}
