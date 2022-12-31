package farrel.tugasakhir.apkmenumakanan.listmakanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import farrel.tugasakhir.apkmenumakanan.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

class holder extends RecyclerView.ViewHolder{
    ImageView imgFoto;
    TextView txtNama, txtHarga;
    LinearLayout listMenu;
    public holder(@NonNull View itemView) {
        super(itemView);

        imgFoto = itemView.findViewById(R.id.imgFoto);
        txtNama = itemView.findViewById(R.id.txtNama);
        txtHarga = itemView.findViewById(R.id.txtHarga);
        listMenu = itemView.findViewById(R.id.listMenu);
    }
}
public class MakananAdapter extends RecyclerView.Adapter<holder> {
    Context context;

    ArrayList<Makanan> listMakanan;

    public MakananAdapter(Context context, ArrayList<Makanan> listMakanan) {
        this.context = context;
        this.listMakanan = listMakanan;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_menu,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        Makanan makanan = listMakanan.get(position);
        holder.txtNama.setText(makanan.getNamaMakanan());
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        holder.txtHarga.setText(format.format(makanan.getHarga()));
        holder.imgFoto.setImageResource(makanan.getImage());
        holder.listMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("nama_makanan", makanan.getNamaMakanan());
                intent.putExtra("deskripsi_makanan", makanan.getDeskripsiMakanan());
                intent.putExtra("harga_makanan", makanan.getHarga());
                intent.putExtra("gambar_makanan", makanan.getImage());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMakanan.size();
    }
}
