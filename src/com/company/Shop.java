package com.company;
import java.util.ArrayList;
import java.util.List;

class Shop {
    private int totalReceipts = 0;
    private double totalTurnover = 0.0D;
    private List<Item> availableItems = new ArrayList();
    private List<Cashier> employees = new ArrayList();
    public Paydesk paydesk1;
    public Paydesk paydesk2;

    Shop(Cashier var1, Cashier var2) {
        this.employees.add(var1);
        this.paydesk1 = new Paydesk(var1);
        this.employees.add(var2);
        this.paydesk2 = new Paydesk(var2);
    }

    public void AddItem(int var1, String var2, double var3, String var5) {
        Item var6 = new Item(var1, var2, var3, var5);
        this.availableItems.add(var6);
    }

    public void AddItem(Item var1) {
        this.availableItems.add(var1);
    }

    public List<Item> GetAvailableItems() {
        return this.availableItems;
    }

    public void RemoveFromAvailableItems(Item var1) {
        this.availableItems.remove(this.availableItems.indexOf(var1));
    }

    public void NewEmployee(int var1, String var2) {
        Cashier var3 = new Cashier(var1, var2);
        this.employees.add(var3);
    }

    public double GetTurnover() {
        return this.totalTurnover;
    }

    public void AddTurnover(double var1) {
        this.totalTurnover += var1;
    }

    public void IncrementReceipts() {
        ++this.totalReceipts;
    }

    public int GetTotalReceipts() {
        return this.totalReceipts;
    }
}
