package farrel.tugasakhir.apkmenumakanan.listpesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import farrel.tugasakhir.apkmenumakanan.R;

import farrel.tugasakhir.apkmenumakanan.adapter.ListPesananAdapter;
import farrel.tugasakhir.apkmenumakanan.apiclient.APIClient;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesanan;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesananInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailListPesananActivity extends AppCompatActivity {
    TextView txtNamaMakanan, txtDetailList, txtDetailjmlharga;
    ListPesanan listPesanan;

    ListPesananInterface listPesananInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_pesanan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtNamaMakanan = findViewById(R.id.txtNamaMakanan);
        txtDetailList = findViewById(R.id.txtDetailList);
        txtDetailjmlharga = findViewById(R.id.txtDetailjmlharga);

        Intent it = getIntent();
        listPesanan = (ListPesanan) it.getSerializableExtra(ListPesananAdapter.KEY_CURRENT);
        txtNamaMakanan.setText(listPesanan.getNamaMakanan());
        txtDetailList.setText(listPesanan.toString());
        txtDetailjmlharga.setText(listPesanan.getJmlHarga());
    }

    public void deleteList(View v){
        listPesananInterface = APIClient.getClient().create(ListPesananInterface.class);
        Call<ListPesanan> delList = listPesananInterface.delListPesanan(listPesanan.getId());
        delList.enqueue(new Callback<ListPesanan>() {
            @Override
            public void onResponse(Call<ListPesanan> call, Response<ListPesanan> response) {
//                Log.d("delete_list", response.toString());
                Toast.makeText(DetailListPesananActivity.this, "Berhasil menghapus " + listPesanan.getNamaMakanan() + " (" + listPesanan.getJumlahMakanan() + " x " + listPesanan.getHargaMakanan() + ')', Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ListPesanan> call, Throwable t) {
                Log.d("detail_error", t.getMessage());
            }
        });
        finish();
    }

}