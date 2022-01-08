package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.Serializable;
 class Receipt implements Serializable{
    private int serialNumber;
    private String issuedBy;
    private LocalDateTime dateTime;
    private DateTimeFormatter formatter;
    private List<Item> ReceiptItems;
    private FileWriter outputFile;
    private PrintWriter printer;
    private File file;

    Receipt(int var1, Cashier var2) {
        this.serialNumber = var1;
        this.issuedBy = var2.GetName();
        this.dateTime = LocalDateTime.now();
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.ReceiptItems = new ArrayList();
    }

    Receipt(int var1) {
        String var2 = Integer.toString(var1);
        this.file = new File(var2 + ".txt");

        try {
            FileReader var3 = new FileReader(this.file);
            BufferedReader var4 = new BufferedReader(var3);
            this.serialNumber = Integer.parseInt(var4.readLine());
            System.out.println(this.serialNumber);
            this.issuedBy = var4.readLine();
            System.out.println(this.issuedBy);
            this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            this.dateTime = LocalDateTime.parse(var4.readLine(), this.formatter);
            System.out.println(this.dateTime);

            String var5;
            while((var5 = var4.readLine()) != null) {
                Item var6 = new Item();
                var6.SetID(Integer.parseInt(var5));
                System.out.println(var6.GetID());
                var6.SetName(var4.readLine());
                System.out.println(var6.GetName());
                var6.SetPrice((double)Float.valueOf(var4.readLine()));
                System.out.println(var6.GetPrice());
            }
        } catch (FileNotFoundException var7) {
            System.out.println("The recepit " + var2 + " was not found.");
        } catch (IOException var8) {
            System.out.println("Error reading the file.");
        }

    }

    public void AddItemToReceipt(Item var1) {
        this.ReceiptItems.add(var1);
    }

    private double PrintReceiptToStandardOutput() {
        double var1 = 0.0D;
        System.out.println("--------------------");
        System.out.println("Receipt #: " + this.serialNumber);
        System.out.println("Cashier: " + this.issuedBy);
        System.out.println("Date: " + this.dateTime.format(this.formatter));

        Item var4;
        for(Iterator var3 = this.ReceiptItems.iterator(); var3.hasNext(); var1 += Double.parseDouble(var4.GetPrice())) {
            var4 = (Item)var3.next();
            System.out.println("Item ID: " + var4.GetID());
            System.out.println("Item: " + var4.GetName());
            System.out.println("Price: " + var4.GetPrice());
            System.out.println();
        }

        System.out.println(var1);
        System.out.println("--------------------");
        return var1;
    }

    private void PrintReceiptToFile(double var1) {
        try {
            FileWriter var3 = new FileWriter(new File(Integer.toString(this.serialNumber) + ".txt"), false);
            Throwable var4 = null;

            try {
                var3.write(Integer.toString(this.serialNumber) + '\n');
                var3.write(this.issuedBy + '\n');
                var3.write(this.dateTime.format(this.formatter) + '\n');
                var3.write(10);
                Iterator var5 = this.ReceiptItems.iterator();

                while(var5.hasNext()) {
                    Item var6 = (Item)var5.next();
                    var3.write(Integer.toString(var6.GetID()) + "\n");
                    var3.write(var6.GetName() + "\n");
                    var3.write(var6.GetPrice());
                    var3.write(10);
                }

                var3.write("Total: " + Double.toString(var1));
            } catch (Throwable var15) {
                var4 = var15;
                throw var15;
            } finally {
                if (var3 != null) {
                    if (var4 != null) {
                        try {
                            var3.close();
                        } catch (Throwable var14) {
                            var4.addSuppressed(var14);
                        }
                    } else {
                        var3.close();
                    }
                }

            }
        } catch (IOException var17) {
            System.out.println("Error writing the receipt!");
        }

    }

    public double PrintReceipt() {
        double var1 = this.PrintReceiptToStandardOutput();
        this.PrintReceiptToFile(var1);
        System.out.println(var1);
        return var1;
    }
}
