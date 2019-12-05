package com.photogram.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photogram.Modelo.Foto;
import com.photogram.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FotoFeedAdapter extends RecyclerView.Adapter<FotoFeedAdapter.ViewHolder> implements View.OnClickListener {

    private List<Foto> mDataSet;
    private View.OnClickListener listener;

    public FotoFeedAdapter(List<Foto> mDataSet, View.OnClickListener listener) {
        this.mDataSet = mDataSet;
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }

    @NonNull
    @Override
    public FotoFeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_foto, parent, false);
        v.setOnClickListener(this);
        FotoFeedAdapter.ViewHolder vh = new FotoFeedAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUsername.setText(mDataSet.get(position).getUsuario());
        //holder.txtNumReacciones.setText(mDataSet.get(position).getReacciones().size());
        Picasso.get().load("http://10.0.2.2:7777/static/" + mDataSet.get(position).getPath()).into(holder.imgView);
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtUsername;
        private TextView txtNumReacciones;
        private TextView txtComentarios;
        private ImageView imgView;
        private ImageButton reaccion;
        private ImageButton comentario;
        private ImageButton opciones;


        public ViewHolder(View v){
            super(v);
            this.txtComentarios = v.findViewById(R.id.txtComentarios);
            this.txtNumReacciones = v.findViewById(R.id.txtNumReacciones);
            this.txtUsername = v.findViewById(R.id.txtUsername);
            this.imgView = v.findViewById(R.id.imageView);
            this.comentario = v.findViewById(R.id.btnComentar);
            this.reaccion = v.findViewById(R.id.btnReaccionar);
            this.opciones = v.findViewById(R.id.btn_opciones_foto);
            this.txtComentarios.setText("comentarios");
        }
    }
}
