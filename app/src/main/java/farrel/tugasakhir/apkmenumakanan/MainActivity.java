package farrel.tugasakhir.apkmenumakanan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import farrel.tugasakhir.apkmenumakanan.R;

import java.util.ArrayList;

import farrel.tugasakhir.apkmenumakanan.listmakanan.Makanan;
import farrel.tugasakhir.apkmenumakanan.listmakanan.MakananAdapter;
import farrel.tugasakhir.apkmenumakanan.listpesanan.DashboardListPesanan;
import farrel.tugasakhir.apkmenumakanan.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPref;

    RecyclerView recMakanan;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Makanan> listMakanan;

    void dataMakanan(){
        listMakanan = new ArrayList<>();
        listMakanan.add(new Makanan("Pecel lele","Pecel lele kriuk mantep",15000, R.drawable.pecel));
        listMakanan.add(new Makanan("Nasi goreng setan","Nasi goreng pedes e nampol",14500,R.drawable.nsgoreng));
        listMakanan.add(new Makanan("Ayam geprek keju","Ayam geprek ditambah taburan keju moza",20000,R.drawable.geprek));
        listMakanan.add(new Makanan("Kari ayam","Kari ayam sedep pol",17500,R.drawable.kari));
        listMakanan.add(new Makanan("Tahu bulat","Tahu bulat garansi anti kempes",500,R.drawable.tahu));
        listMakanan.add(new Makanan("Salad buah","Salad buah-buahan langsung dipetik",12000,R.drawable.salad));
        data();
    }
    void data(){
        recMakanan = findViewById(R.id.recMenu);
        adapter = new MakananAdapter(this,listMakanan);
        layoutManager = new LinearLayoutManager(this);
        recMakanan.setLayoutManager(new GridLayoutManager(this,2));
        recMakanan.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataMakanan();

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu( Menu menu) {
        final MenuItem menuItem = menu.findItem(R.id.menu_namauser);
        String namaUser = sharedPref.getString(LoginActivity.KEY_USER, null);
        menuItem.setTitle(namaUser);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_namauser:
                break;
            case R.id.menu_list:
                Intent it = new Intent(getApplicationContext(), DashboardListPesanan.class);
                startActivity(it);
                break;
            case R.id.menu_logout:
                Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove(LoginActivity.KEY_USER);
                editor.apply();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}