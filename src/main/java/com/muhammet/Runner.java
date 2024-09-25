package com.muhammet;

import com.muhammet.entity.Resim;
import com.muhammet.entity.Urun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("URUN");
        EntityManager em = emf.createEntityManager();

        Resim resim1 = Resim.builder().ad("asus_1").url("https://picsum.photos/100/100").build();
        Resim resim2 = Resim.builder().ad("asus_2").url("https://picsum.photos/100/100").build();

        Urun urun = Urun.builder()

                        .ad("Asus Laptop")
                        .fiyat(125999d)
                        .resimler(List.of(resim1,resim2))
                .build();

        em.getTransaction().begin();
//        em.persist(resim1);
//        em.persist(resim2);
        em.persist(urun);
        em.getTransaction().commit();
        em.close();


        System.out.println("UYGULAMA BİTTİ.");
    }
}
