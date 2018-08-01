package com.smartbox.diegotest.testdiegogarayandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import org.w3c.dom.Text;

import java.util.List;

public class PartidosAdapter extends RecyclerView.Adapter<PartidosAdapter.CustomRecyclerView>{


    private List<Partidos> itemList;
    private RequestQueue requestQueue;

    public PartidosAdapter(Context context, List<Partidos> itemList){
        this.itemList = itemList;
        requestQueue = SingletonPatron.getInstance(context).getRequestQueue();

    }


    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    @Override
    public CustomRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, null);
        CustomRecyclerView rcv = new CustomRecyclerView(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomRecyclerView holder, int position) {
        Partidos data = itemList.get(position);
        holder.txtNomEquipos.setText(data.getHomeTeam() + " " + data.getAwayTeam());
        holder.txtfecha.setText(data.getStartDate());
        holder.txtestadoPartido.setText(data.getEventStatus());
        holder.txtMarcador.setText(data.getHomeScore() + " " + data.getAwayScore());
    }

    public class CustomRecyclerView extends RecyclerView.ViewHolder{
        TextView txtNomEquipos;
        TextView txtMarcador;
        TextView txtestadoPartido;
        TextView txtfecha;

        CustomRecyclerView(View itemView){
            super(itemView);
            txtNomEquipos = itemView.findViewById(R.id.nomEquipos);
            txtMarcador = itemView.findViewById(R.id.marcador);
            txtestadoPartido = itemView.findViewById(R.id.estado);
            txtfecha = itemView.findViewById(R.id.fecha);
        }
    }

}
