package com.sorular;

public class Runner {
    public static void main(String[] args) {
        /**
         * Entities
         * ----------
         * NOT: unvan enum olarak kullanılacak
         * Personel,
         * - id, ad, soyad, unvan, maas, departman, adresListesi
         * Departman,
         * - id, ad, aciklama, yetkiliId, personelListesi
         * Adres
         * - id, ad, il, ilce, acikAdres, telefon, personel
         *
         * 3 adet personel, 1 adet müdür personel, 2 departman ve her personel için en az 2 adres tanımlatyın.
         *
         * -
         *
         * Sorular
         * 1- Adı verilen bir departmanda çalışan personelleri listesi - (ad  > like  + ignoreCase)
         * 2- Belli bir ilde ikamet eden personellerin listesi
         * 3- Adını verdiğim Müdür ile çalışan personellerin listesi
         * 4- Belli bir ünvana sahip personellerin listesi.
         * 5- departmanlarda çalışan personellerin sayıları -> bilgiişlem: 5, insankaynakları: 3 gibi
         * 6- ünvanlarına göre personel sayıları
         * 7- personel Listesi;
         * id: 44
         * ad: Muhammet
         * ünvan: Eğitmen
         * departmanı: Yazılım
         * müdür: Ayhan
         * -------------
         * şeklinde bir çıktı verin.
         *
         * ÖRN:
         * public List<VwPersonel> findAllViewPersonel(){
         *     return null;
         * }
         *
         */
    }
}
