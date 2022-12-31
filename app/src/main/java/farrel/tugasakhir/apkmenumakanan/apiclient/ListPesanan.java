package farrel.tugasakhir.apkmenumakanan.apiclient;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class ListPesanan implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("namaMakanan")
    private String namaMakanan;
    @SerializedName("hargaMakanan")
    private int hargaMakanan;
    @SerializedName("jumlahMakanan")
    private int jumlahMakanan;


    public ListPesanan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public void setNamaMakanan(String namaMakanan) {
        this.namaMakanan = namaMakanan;
    }

    public int getHargaMakanan() {
        return hargaMakanan;
    }

    public void setHargaMakanan(int hargaMakanan) {
        this.hargaMakanan = hargaMakanan;
    }

    public int getJumlahMakanan() {
        return jumlahMakanan;
    }

    public void setJumlahMakanan(int jumlahMakanan) {
        this.jumlahMakanan = jumlahMakanan;
    }

    @Override
    public String toString() {
        return namaMakanan + " (" + jumlahMakanan + " x " + hargaMakanan + ')';
    }

    public int getJmlHargaint(){
        return hargaMakanan*jumlahMakanan;
    }
    public String getJmlHarga(){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        int harga = getJmlHargaint();
        return format.format(harga); // Integer.toString(harga * jumlah);
    }
}
