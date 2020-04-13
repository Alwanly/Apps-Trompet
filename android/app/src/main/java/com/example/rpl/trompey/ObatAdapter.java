package com.example.rpl.trompey;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ObatAdapter extends RecyclerView.Adapter<ObatAdapter.ViewHolder> {
    private ArrayList<Obat> obat;
    private Context mContext;

    ObatAdapter(Context context, ArrayList<Obat> obat) {
        this.obat = obat;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ObatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.obat_hewan, parent, false));
    }

    @Override
    public void onBindViewHolder(ObatAdapter.ViewHolder holder, int position) {
        Obat currentObat = obat.get(position);
        holder.bind(currentObat);
    }

    @Override
    public int getItemCount() {
        return obat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nama, harga;
        private ImageView gambar;

        public ViewHolder(View itemView) {
            super(itemView);

            gambar = itemView.findViewById(R.id.gambar);
            nama = itemView.findViewById(R.id.nama);
            harga = itemView.findViewById(R.id.harga);
            itemView.setOnClickListener(this);
        }

        public void bind (Obat obat){
            nama.setText(obat.getNama());
            harga.setText(obat.getHarga());

            Glide.with(mContext).load(
                    obat.getGambar()).into(gambar);
        }

        @Override
        public void onClick(View view) {
            Obat currentObat = obat.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, ObatHewanBayar.class);
            detailIntent.putExtra("nama", currentObat.getNama());
            detailIntent.putExtra("harga", currentObat.getHarga());
            mContext.startActivity(detailIntent);
        }
    }
}
