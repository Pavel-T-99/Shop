package com.company;

import java.util.Iterator;
import java.util.Scanner;

class AddThread extends Thread {
    private Thread thread;
    private Shop shop;
    private Paydesk paydesk;
    private Boolean samePaydesk = new Boolean(true);

    AddThread(Shop var1, Paydesk var2) {
        this.shop = var1;
        this.paydesk = var2;
    }

    private void PrintInterfacePrompt() {
        System.out.println("Current cashier: " + this.paydesk.GetCashier().GetID() + ", " + this.paydesk.GetCashier().GetName());
        System.out.println("Choose an action.");
        System.out.println("[1] Add an item to the shop inventory");
        System.out.println("[2] Retrieve a list of the shop inventory");
        System.out.println("[3] Add an item to the shopping cart");
        System.out.println("[4] Remove an item from the shopping cart");
        System.out.println("[5] Check items currently in the shopping cart");
        System.out.println("[6] Generate a receipt (clears the shopping cart)");
        System.out.println("[7] Get total turnover");
        System.out.println("[8] Use the other paydesk\n");
        System.out.println("Input: ");
    }

    private int GetInterfaceInput(Scanner var1) {
        int var2 = var1.nextInt();
        var1.nextLine();
        System.out.println();
        return var2;
    }

    private void InterfaceAddItemToShop(Scanner var1) {
        System.out.println("Please enter the required information.");
        System.out.println("Product ID: ");
        int var2 = var1.nextInt();
        var1.nextLine();
        System.out.println("Product name: ");
        String var3 = var1.nextLine();
        System.out.println("Product price: ");
        double var4 = var1.nextDouble();
        var1.nextLine();
        System.out.println("Product expiry date: ");
        String var6 = var1.nextLine();
        this.shop.AddItem(var2, var3, var4, var6);
        System.out.println("--- Done!");
    }

    private void InterfaceCheckItemsInShop() {
        System.out.println("ID\t\tName\t\tPrice\t\tExpiration Date");
        Iterator var1 = this.shop.GetAvailableItems().iterator();

        while(var1.hasNext()) {
            Item var2 = (Item)var1.next();
            System.out.println(var2.GetID() + "\t\t" + var2.GetName() + "\t\t" + var2.GetPrice() + "\t\t" + var2.GetExpirationDate());
        }

        System.out.println();
    }

    private void InterfaceAddItemToCart(Scanner var1) {
        System.out.println("Please enter the required information.");
        System.out.println("Product name (exactly as it is): ");
        String var2 = var1.nextLine();

        try {
            this.paydesk.AddItemToCart(this.shop, var2);
            System.out.println("--- Done!");
        } catch (ItemNotAvailableException var7) {
            System.out.println("The item doesn't appear to exist!");
        } finally {
            System.out.println();
        }

    }

    private void InterfaceRemoveItemFromCart(Scanner var1) {
        System.out.println("Please enter the required information.");
        System.out.println("Product name (exactly as it is): ");
        String var2 = var1.nextLine();

        try {
            this.paydesk.RemoveItemFromCart(this.shop, var2);
            System.out.println("--- Done!");
        } catch (ItemNotAvailableException var7) {
            System.out.println("The item doesn't appear to be added to the shopping cart!");
        } finally {
            System.out.println();
        }

    }

    private void InterfaceCheckItemsInCart() {
        System.out.println("ID\t\tName\t\tPrice\t\tExpiration Date");

        try {
            Iterator var1 = this.paydesk.GetItemsInCart().iterator();

            while(var1.hasNext()) {
                Item var2 = (Item)var1.next();
                System.out.println(var2.GetID() + "\t\t" + var2.GetName() + "\t\t" + var2.GetPrice() + "\t\t" + var2.GetExpirationDate());
            }
        } catch (NullPointerException var3) {
            System.out.println("There aren't any items in the shopping cart.");
        }

        System.out.println();
    }

    private void GenerateReceipt() {
        this.paydesk.GenerateReceipt(this.shop);
        this.shop.IncrementReceipts();
    }

    private void PerformInterfaceAction(Scanner var1, int var2) {
        switch(var2) {
            case 1:
                this.InterfaceAddItemToShop(var1);
                break;
            case 2:
                this.InterfaceCheckItemsInShop();
                break;
            case 3:
                this.InterfaceAddItemToCart(var1);
                break;
            case 4:
                this.InterfaceRemoveItemFromCart(var1);
                break;
            case 5:
                this.InterfaceCheckItemsInCart();
                break;
            case 6:
                this.paydesk.GenerateReceipt(this.shop);
                break;
            case 7:
                System.out.println(this.shop.GetTurnover());
                System.out.println();
                break;
            case 8:
                this.samePaydesk = false;
        }

    }

    public void run() {
        synchronized(this) {
            Scanner var2 = new Scanner(System.in);

            while(this.samePaydesk) {
                this.PrintInterfacePrompt();
                int var3 = this.GetInterfaceInput(var2);
                this.PerformInterfaceAction(var2, var3);
            }

        }
    }
}
