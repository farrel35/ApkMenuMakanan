package farrel.tugasakhir.apkmenumakanan.listmakanan;

public class Makanan {
    private String namaMakanan,deskripsiMakanan;
    private Integer harga,image;

    public Makanan(String namaMakanan, String deskripsiMakanan, Integer harga, Integer image) {
        this.namaMakanan = namaMakanan;
        this.deskripsiMakanan = deskripsiMakanan;
        this.harga = harga;
        this.image = image;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public String getDeskripsiMakanan() {
        return deskripsiMakanan;
    }

    public void setDeskripsiMakanan(String deskripsiMakanan) {
        this.deskripsiMakanan = deskripsiMakanan;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
