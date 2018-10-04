package asshohabah_borneo.cv.lapaksampit.Me;

public class Model {
    public String id_produk;
    public String kd_produk;
    public String kd_kategori;
    public String kd_pengguna;
    public String nm_pengguna;
    public String alamat;
    public String no_telp;
    public String no_wa;
    public String nm_kategori;
    public String nm_produk;
    public String gbr_produk;
    public String keterangan;
    public String harga;

    public Model(
            String id_produk,
            String kd_produk,
            String kd_kategori,
            String kd_pengguna,
            String nm_pengguna,
            String alamat,
            String no_telp,
            String no_wa,
            String nm_kategori,
            String nm_produk,
            String gbr_produk,
            String keterangan,
            String harga
    ){
        this.id_produk = id_produk;
        this.kd_produk = kd_produk;
        this.kd_kategori = kd_kategori;
        this.kd_pengguna = kd_pengguna;
        this.nm_pengguna = nm_pengguna;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.no_wa = no_wa;
        this.nm_kategori = nm_kategori;
        this.nm_produk = nm_produk;
        this.gbr_produk = gbr_produk;
        this.keterangan = keterangan;
        this.harga = harga;
    }

    public String getId_produk() {
        return id_produk;
    }

    public void setId_produk(String id_produk) {
        this.id_produk = id_produk;
    }

    public String getKd_produk() {
        return kd_produk;
    }

    public void setKd_produk(String kd_produk) {
        this.kd_produk = kd_produk;
    }

    public String getKd_kategori() {
        return kd_kategori;
    }

    public void setKd_kategori(String kd_kategori) {
        this.kd_kategori = kd_kategori;
    }

    public String getKd_pengguna() {
        return kd_pengguna;
    }

    public void setKd_pengguna(String kd_pengguna) {
        this.kd_pengguna = kd_pengguna;
    }

    public String getNm_pengguna() {
        return nm_pengguna;
    }

    public void setNm_pengguna(String nm_pengguna) {
        this.nm_pengguna = nm_pengguna;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getNo_wa() {
        return no_wa;
    }

    public void setNo_wa(String no_wa) {
        this.no_wa = no_wa;
    }

    public String getNm_kategori() {
        return nm_kategori;
    }

    public void setNm_kategori(String nm_kategori) {
        this.nm_kategori = nm_kategori;
    }

    public String getNm_produk() {
        return nm_produk;
    }

    public void setNm_produk(String nm_produk) {
        this.nm_produk = nm_produk;
    }

    public String getGbr_produk() {
        return gbr_produk;
    }

    public void setGbr_produk(String gbr_produk) {
        this.gbr_produk = gbr_produk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
