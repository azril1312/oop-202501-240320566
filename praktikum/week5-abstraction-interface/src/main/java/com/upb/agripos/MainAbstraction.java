package com.upb.agripos;

import com.upb.agripos.model.kontrak.Receiptable;
import com.upb.agripos.model.pembayaran.Cash;
import com.upb.agripos.model.pembayaran.EWallet;
import com.upb.agripos.model.pembayaran.Pembayaran;
import com.upb.agripos.model.pembayaran.TransferBank;
import com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 87000, 100000);
        Pembayaran ew = new EWallet("INV-002", 175000, "azrilrabbani@ewallet", "123456");
        Pembayaran transfer = new TransferBank("INV-003", 150000, "BCA", true);


        System.out.println(((Receiptable) cash).cetakStruk());
        System.out.println(((Receiptable) ew).cetakStruk());
        System.out.println(((Receiptable) transfer).cetakStruk());
    
        CreditBy.print("Azril Rabbani Fawa", "240320566");
    }
}