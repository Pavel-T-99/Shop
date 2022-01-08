package com.company;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Paydesk {
    private Cashier currentCashier;
    private List<Item> currentCartItems;

    Paydesk(Cashier var1) {
        this.currentCashier = var1;
        this.currentCartItems = new ArrayList();
    }

    public Cashier GetCashier() {
        return this.currentCashier;
    }

    public List<Item> GetItemsInCart() {
        return this.currentCartItems;
    }

    public int AddItemToCart(Shop var1, String var2) throws ItemNotAvailableException {
        List var3 = var1.GetAvailableItems();
        Iterator var4 = var3.iterator();

        Item var5;
        do {
            if (!var4.hasNext()) {
                throw new ItemNotAvailableException("The item " + var2 + " is not available.");
            }

            var5 = (Item)var4.next();
        } while(!var5.GetName().equals(var2));

        this.currentCartItems.add(var5);
        var1.RemoveFromAvailableItems(var5);
        return 0;
    }

    public int RemoveItemFromCart(Shop var1, String var2) throws ItemNotAvailableException {
        Iterator var3 = this.currentCartItems.iterator();

        Item var4;
        do {
            if (!var3.hasNext()) {
                throw new ItemNotAvailableException("The item " + var2 + " is not available.");
            }

            var4 = (Item)var3.next();
        } while(!var4.GetName().equals(var2));

        this.currentCartItems.remove(var4);
        var1.AddItem(var4);
        return 0;
    }

    public void GenerateReceipt(Shop var1) {
        if (this.currentCartItems.size() != 0) {
            try {
                Receipt var2 = new Receipt(var1.GetTotalReceipts() + 1, this.currentCashier);
                Iterator var3 = this.currentCartItems.iterator();

                while(var3.hasNext()) {
                    Item var4 = (Item)var3.next();
                    var2.AddItemToReceipt(var4);
                }

                var1.AddTurnover(var2.PrintReceipt());
                var1.IncrementReceipts();
                this.currentCartItems.clear();
            } catch (NullPointerException var5) {
                System.out.println("There isn't an employee on duty to generate the receipt.");
            }
        } else {
            System.out.println("There aren't any items added to the cart.");
        }

        System.out.println();
    }
}
