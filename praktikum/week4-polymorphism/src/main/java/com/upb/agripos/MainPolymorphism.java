//main program polymorphism

package com.upb.agripos;

import com.upb.agripos.model.Produk;
import com.upb.agripos.model.Pupuk;
import com.upb.agripos.model.Benih;
import com.upb.agripos.model.AlatPertanian;
import com.upb.agripos.util.CreditBy;

public class MainPolymorphism {
    public static void main(String[] args) {
        
        System.out.println("===== Daftar Program Pertanian =====");
        System.out.println("-------------------------------------");
        System.out.println("Daftar Produk: ");
        
        Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
        };

        for (Produk p : daftarProduk) {
            System.out.println(p.getInfo()); // Dynamic Binding
        }

        CreditBy.print("Azril Rabbani Fawa", "240320566");
    }
}