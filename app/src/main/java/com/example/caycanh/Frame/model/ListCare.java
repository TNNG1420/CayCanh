package com.example.caycanh.Frame.model;

import java.util.List;

public class ListCare {
    List<Care> care;

    public ListCare(List<Care> care) {
        this.care = care;
    }

    public ListCare() {
    }

    public List<Care> getCare() {
        return care;
    }

    public void setCare(List<Care> care) {
        this.care = care;
    }
}
