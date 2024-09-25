package com.muhammet.repository;

import com.muhammet.entity.Urun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class Sorgular {
    private EntityManagerFactory emf;
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;

    public Sorgular() {
        emf = Persistence.createEntityManagerFactory("URUN");
        em = emf.createEntityManager();
        criteriaBuilder = em.getCriteriaBuilder();
    }

    public void findAll(){
        /**
         * select * from tblurun
         */
        CriteriaQuery<Urun> criteriaQuery = criteriaBuilder.createQuery(Urun.class); // return type
        Root<Urun> root = criteriaQuery.from(Urun.class); // from
        criteriaQuery.select(root); // select * [tablo kolon seçimi]
        List<Urun> urunListesi = em.createQuery(criteriaQuery).getResultList(); // result list
        urunListesi.forEach(urun -> {
            System.out.println(urun.getId()+" - "+ urun.getAd());
        });
    }

    /**
     * bir tablodaki tek bir kolonun seçilerek sonuç olarak dönülmesi
     * select ad from tblurun
     */
    public void selectOneColumn(){
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        criteriaQuery.select(root.get("ad"));
        List<String> urunAdListesi = em.createQuery(criteriaQuery).getResultList();
        urunAdListesi.forEach(System.out::println);
    }

    /**
     * bir tabloda birden fazla alanın return edilmesi gerekirse
     * select id,ad from tblurun
     */
    public void selectManyColumn(){
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        Path<Long> idPath = root.get("id");
        Path<String> adPath = root.get("ad");
        criteriaQuery.select(criteriaBuilder.array(idPath,adPath));
        List<Object[]> urunIdAdListesi = em.createQuery(criteriaQuery).getResultList();
        urunIdAdListesi.forEach(u->{
            for(Object o : u){
                System.out.print(o+ " - ");
            }
            System.out.println();
        });

    }

}
