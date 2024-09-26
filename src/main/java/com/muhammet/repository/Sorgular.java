package com.muhammet.repository;

import com.muhammet.entity.Urun;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
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

    public void usingTuple(){
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createQuery(Tuple.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        Path<Long> idPath = root.get("id");
        Path<String> adPath = root.get("ad");
//        criteriaQuery.select(criteriaBuilder.array(idPath,adPath));
        criteriaQuery.multiselect(idPath,adPath);
        List<Tuple> urunListesiTuple = em.createQuery(criteriaQuery).getResultList();

        urunListesiTuple.forEach(ut->{
            System.out.println("id....: "+ ut.get(0));
            System.out.println("ad....: "+ ut.get(adPath));
            System.out.println("-------------------------");
        });
    }

    /**
     * select * from tblurun where ad like %a%
     */
    public void usingWhere(){
        CriteriaQuery<Urun> criteriaQuery = criteriaBuilder.createQuery(Urun.class);
        Root<Urun> root = criteriaQuery.from(Urun.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.like(root.get("ad"),"A%"));
        List<Urun> urunList = em.createQuery(criteriaQuery).getResultList();
        urunList.forEach(u->{
            System.out.println(u.getId()+" - "+ u.getAd()+ " - "+ u.getFiyat());
        });
    }


}
