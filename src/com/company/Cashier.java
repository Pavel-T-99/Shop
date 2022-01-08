package com.company;

class Cashier {
    private int ID;
    private String Name;

    Cashier(int var1, String var2) {
        this.ID = var1;
        this.Name = var2;
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
}

