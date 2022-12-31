package farrel.tugasakhir.apkmenumakanan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import farrel.tugasakhir.apkmenumakanan.R;

import java.util.ArrayList;

import farrel.tugasakhir.apkmenumakanan.listpesanan.DetailListPesananActivity;
import farrel.tugasakhir.apkmenumakanan.apiclient.ListPesanan;

class holder extends RecyclerView.ViewHolder{
    TextView txtListPesanan,txtJmlharga;
    ConstraintLayout itemLayout;

    public holder(@NonNull View itemView) {
        super(itemView);
        txtListPesanan = itemView.findViewById(R.id.txtListPesanan);
        txtJmlharga = itemView.findViewById(R.id.txtJmlharga);
        itemLayout = itemView.findViewById(R.id.itemLayout);
    }
}

public class ListPesananAdapter extends RecyclerView.Adapter<holder> {
    Context context;
    ArrayList<ListPesanan> listPesananArray;
    public static String KEY_CURRENT = "current_list";
    public ListPesananAdapter(Context context, ArrayList<ListPesanan> listPesananArray) {
        this.context = context;
        this.listPesananArray = listPesananArray;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_pesanan, parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        ListPesanan listPesanan = listPesananArray.get(position);
        holder.txtListPesanan.setText(listPesanan.toString());
        holder.txtJmlharga.setText(listPesanan.getJmlHarga());
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemLayout.getContext();
                Intent it = new Intent(context, DetailListPesananActivity.class);
                it.putExtra(KEY_CURRENT, listPesanan);
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPesananArray.size();
    }
}
