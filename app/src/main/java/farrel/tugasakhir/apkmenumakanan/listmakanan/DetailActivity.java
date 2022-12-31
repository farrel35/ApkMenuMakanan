package farrel.tugasakhir.apkmenumakanan.listmakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import farrel.tugasakhir.apkmenumakanan.R;

import java.text.NumberFormat;
import java.util.Locale;

import farrel.tugasakhir.apkmenumakanan.apiclient.APIClient;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesanan;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesananInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    ImageView imageViewFoto, imageViewIncrement, imageViewDecrement;
    TextView textViewNama, textViewDeskripsi, textViewHarga, textViewCount, textViewTotal;
    ListPesananInterface listPesananInterface;
    int count = 1;
    int hargaMakanan1 = 0;
    String namamakanan;
    Integer hargamakanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imageViewFoto = findViewById(R.id.imgFotodtl);
        imageViewIncrement = findViewById(R.id.imgIncrement);
        imageViewDecrement = findViewById(R.id.imgDecrement);
        textViewNama = findViewById(R.id.txtNamadtl);
        textViewDeskripsi = findViewById(R.id.txtDeskripsidtl);
        textViewHarga = findViewById(R.id.txtHargadtl);
        textViewCount = findViewById(R.id.txtCount);
        textViewTotal = findViewById(R.id.txtTotal);
        listPesananInterface = APIClient.getClient().create(ListPesananInterface.class);

        imageViewIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });
        imageViewDecrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
        getIncomingExtra();
    }

    private void getIncomingExtra(){
        if(getIntent().hasExtra("nama_makanan") && getIntent().hasExtra("harga_makanan") && getIntent().hasExtra("gambar_makanan")){
            Integer fotoMakanan = getIntent().getIntExtra("gambar_makanan", 0);
            String deskripsiMakanan = getIntent().getStringExtra("deskripsi_makanan");
            String namaMakanan = getIntent().getStringExtra("nama_makanan");
            Integer hargaMakanan = getIntent().getIntExtra("harga_makanan",0);

            setDataActivity(fotoMakanan, namaMakanan, deskripsiMakanan, hargaMakanan);
            namamakanan = namaMakanan;
            hargamakanan = hargaMakanan;
        }
    }
    private void setDataActivity(Integer fotoMakanan, String namaMakanan, String deskripsiMakanan, Integer hargaMakanan){
        imageViewFoto.setImageResource(fotoMakanan);
        textViewNama.setText(namaMakanan);
        textViewDeskripsi.setText(deskripsiMakanan);

        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        textViewHarga.setText("Harga : \t" +format.format(hargaMakanan));
        hargaMakanan1 = hargaMakanan;
        int hargaTotal = hargaMakanan1*count;
        textViewTotal.setText("Total : " +format.format(hargaTotal));
    }
    public void increment(){
        count++;
        textViewCount.setText(""+count);

        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        int hargaTotal = hargaMakanan1*count;
        textViewTotal.setText("Total : " +format.format(hargaTotal));
    }
    public void decrement(){
        if(count<=1) count =1;
        else count--;
        textViewCount.setText(""+count);

        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        int hargaTotal = hargaMakanan1*count;
        textViewTotal.setText("Total : " +format.format(hargaTotal));
    }

    public void addPesanan(View v){
        String namaMakanan = namamakanan;
        Integer hargaMakanan = hargamakanan;
        Integer jumlahMakanan = count;
        Call<ListPesanan> postListPesanan = listPesananInterface.postListPesanan(namaMakanan, hargaMakanan, jumlahMakanan);

        postListPesanan.enqueue(new Callback<ListPesanan>() {
            @Override
            public void onResponse(Call<ListPesanan> call, Response<ListPesanan> response) {
//                Log.d("cobaa", response.toString());
                Toast.makeText(DetailActivity.this, "Berhasil menambahkan " + namaMakanan + " (" + jumlahMakanan + " x " + hargaMakanan + ')', Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ListPesanan> call, Throwable t) {
                Log.d("detail_error", t.getMessage());
            }
        });
        finish();
    }
}