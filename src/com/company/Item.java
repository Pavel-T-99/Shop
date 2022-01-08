package com.company;

public class Item {
    private int ID;
    private String Name;
    private double Price;
    private String ExpirationDate;

    Item(int var1, String var2, double var3, String var5) {
        this.ID = var1;
        this.Name = var2;
        this.Price = var3;
        this.ExpirationDate = var5;
    }

    Item() {
        this.ExpirationDate = "N/A";
    }

    public void SetID(int var1) {
        this.ID = var1;
    }

    public int GetID() {
        return this.ID;
    }

    public void SetName(String var1) {
        this.Name = var1;
    }

    public String GetName() {
        return this.Name;
    }

    public void SetPrice(double var1) {
        this.Price = var1;
    }

    public String GetPrice() {
        return String.format("%.2f", this.Price);
    }

    public void SetExpirationDate(String var1) {
        this.ExpirationDate = var1;
    }

    public String GetExpirationDate() {
        return this.ExpirationDate;
    }
}
