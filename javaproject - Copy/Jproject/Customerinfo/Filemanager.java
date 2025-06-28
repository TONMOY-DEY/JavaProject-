package Jproject.Customerinfo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class Filemanager {
    public static void saveCustomer(String name, String email, String id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("E:/java/Jproject/customer.txt", true))) {
            writer.write(name + "," + email + "," + id);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
