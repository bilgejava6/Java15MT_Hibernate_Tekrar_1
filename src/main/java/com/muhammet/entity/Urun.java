package com.muhammet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_urun")
public class Urun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String ad;
    Double fiyat;
    /**
     * 1.Durum
     *
     * 2.Durum
     *
     */
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "urun_ile_resim_baglanti_tablosu",
            joinColumns = @JoinColumn(name = "urunun_idsi"),
            inverseJoinColumns = @JoinColumn(name = "resim_in_idsi")
    )
    List<Resim> resimler;

    @OneToMany(mappedBy = "urun")
//    @JoinColumn(name = "urun_id")
    List<Ozellik> ozellikler;
}
