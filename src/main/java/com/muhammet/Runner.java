package com.muhammet;

import com.muhammet.entity.Urun;
import com.muhammet.utility.UrunImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        /**
         * gerekli kayıtları oluşması için başlangıç komutları
         */
      //  new UrunImpl().createUrun();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("URUN");
        EntityManager em = emf.createEntityManager();
        /**
         * tablodan veri çekmek için; SQL
         * select * from tblurun
         * 1. Adım hangi tablo seçilecek
         */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Urun> criteria = cb.createQuery(Urun.class); // return type seçimi.
        /**
         * 2. Adım
         * select [kolonlar] from
         */
        Root<Urun> root = criteria.from(Urun.class); // ürün içerisindeki alanların analizi için kullanılır.
        criteria.select(root);
//        criteria.select(root.get("ad")); // select ad from
        List<Urun> urunListesi =  em.createQuery(criteria).getResultList();
        urunListesi.forEach(urun->{
            System.out.println("id....: "+urun.getId());
            System.out.println("ad....: "+urun.getAd());
            System.out.println("fiyat....: "+urun.getFiyat());
           // System.out.println("resimler....: "+urun.getResimler());
        });
        em.close();
        emf.close();
        System.out.println("UYGULAMA BİTTİ.");
    }
}
