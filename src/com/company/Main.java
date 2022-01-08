package com.company;

import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] var0) {
        Cashier var1 = new Cashier(1000, "Pavel Tinev");
        Cashier var2 = new Cashier(3000, "Ivan Georgiev");
        Shop var3 = new Shop(var1, var2);
        AddThread var4 = new AddThread(var3, var3.paydesk1);
        AddThread var5 = new AddThread(var3, var3.paydesk2);

        while(true) {
            var4.run();
            var5.run();
        }
    }
//   
}
