package com.muhammet;

import com.muhammet.utility.UrunImpl;

public class Runner {
    public static void main(String[] args) {
        /**
         * gerekli kayıtları oluşması için başlangıç komutları
         */
        new UrunImpl().createUrun();


        System.out.println("UYGULAMA BİTTİ.");
    }
}
