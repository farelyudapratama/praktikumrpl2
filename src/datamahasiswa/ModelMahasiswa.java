package datamahasiswa;

import javax.persistence.*;

@Entity
@Table(name = "mahasiswa")
public class ModelMahasiswa {
    @Id
    private String npm;
    
    private String nama;
    private int tinggi;
    
    @Transient
    private String pindahanText;
    
    @Column(name = "pindahan")
    private boolean pindahan;

    public ModelMahasiswa() {
        // Required empty constructor for Hibernate
    }

    public ModelMahasiswa(String npm, String nama, int tinggi, String pindahanText) {
        this.npm = npm;
        this.nama = nama;
        this.tinggi = tinggi;
        this.pindahanText = pindahanText;
        this.pindahan = pindahanText.equals("Ya");
    }

    // Getters
    public String getNPM() {
        return npm;
    }

    public String getNama() {
        return nama;
    }

    public int getTinggi() {
        return tinggi;
    }

    public String getPindahanText() {
        return pindahan ? "Ya" : "Tidak";
    }

    public boolean isPindahan() {
        return pindahan;
    }

    // Setters
    public void setNpm(String npm) {
        this.npm = npm;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTinggi(int tinggi) {
        this.tinggi = tinggi;
    }

    public void setPindahan(boolean pindahan) {
        this.pindahan = pindahan;
        this.pindahanText = pindahan ? "Ya" : "Tidak";
    }
}