package com.example.caycanh.Frame.model;

import java.util.List;

public class Tree {
    List<Product> tree;



    public Tree(List<Product> tree) {
        this.tree = tree;
    }
    public List<Product> getTree() {
        return tree;
    }

    public void setTree(List<Product> tree) {
        this.tree = tree;
    }
}
