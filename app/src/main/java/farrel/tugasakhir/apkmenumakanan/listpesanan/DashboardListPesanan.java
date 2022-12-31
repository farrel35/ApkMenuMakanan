package farrel.tugasakhir.apkmenumakanan.listpesanan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import farrel.tugasakhir.apkmenumakanan.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import farrel.tugasakhir.apkmenumakanan.adapter.ListPesananAdapter;
import farrel.tugasakhir.apkmenumakanan.apiclient.APIClient;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesanan;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesananInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardListPesanan extends AppCompatActivity {
    TextView txtTotalbayar;
    ListPesananInterface listPesananInterface;
    RecyclerView recListPesanan;
    ListPesananAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesanan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recListPesanan = findViewById(R.id.rec_listpesanan);
        recListPesanan.setLayoutManager(new LinearLayoutManager(this));
        listPesananInterface = APIClient.getClient().create(ListPesananInterface.class);
        txtTotalbayar = findViewById(R.id.txtTotalbayar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllListPesanan();
    }

    private void getAllListPesanan(){

        Call<List<ListPesanan>> getListPesanan = listPesananInterface.getListPesanan();
        getListPesanan.enqueue(new Callback<List<ListPesanan>>() {
            @Override
            public void onResponse(Call<List<ListPesanan>> call, Response<List<ListPesanan>> response) {
                ArrayList<ListPesanan> listPesanan = (ArrayList<ListPesanan>) response.body();
//                Log.d("cobaaa", response.raw().toString());
//                Log.d("cobaaa", listPesanan.toString());

                int total = 0;
                for(ListPesanan pesanan:listPesanan){
                    total += pesanan.getJmlHargaint();
                }

                adapter = new ListPesananAdapter(DashboardListPesanan.this,listPesanan);
                recListPesanan.setAdapter(adapter);
                Locale locale = new Locale("in", "ID");
                NumberFormat format = NumberFormat.getCurrencyInstance(locale);
                format.setMaximumFractionDigits(0);
                txtTotalbayar.setText(format.format(total));
            }

            @Override
            public void onFailure(Call<List<ListPesanan>> call, Throwable t) {
                Log.d("dashboard_error", t.getMessage());
            }
        });

    }
}