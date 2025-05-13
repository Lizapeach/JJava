package com.example.final_project;

public class Item {
    private int id;
    private String name;
    private int quantity;
    private boolean bought;
    private int categoryId;
    private String categoryName;

    public Item() {}

    public Item(int id, String name, int quantity, boolean bought, int categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.bought = bought;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public boolean isBought() { return bought; }
    public void setBought(boolean bought) { this.bought = bought; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
