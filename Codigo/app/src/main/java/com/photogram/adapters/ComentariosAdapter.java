package com.photogram.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photogram.modelo.Comentario;
import com.photogram.R;

import java.util.List;

public class ComentariosAdapter extends  RecyclerView.Adapter<ComentariosAdapter.ViewHolder> implements View.OnClickListener {

    private List<Comentario> mDataSet;
    private View.OnClickListener listener;

    public ComentariosAdapter(List<Comentario> mDataSet, View.OnClickListener listener) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comentarios, parent, false);
        v.setOnClickListener(this);
        ComentariosAdapter.ViewHolder vh = new ComentariosAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUsername.setText(mDataSet.get(position).getUsuario());
        holder.txtComentario.setText(mDataSet.get(position).getContenido());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtUsername;
        private TextView txtComentario;

        public ViewHolder(View v) {
            super(v);
            txtUsername = v.findViewById(R.id.txt_username_comentario);
            txtComentario = v.findViewById(R.id.txt_comentario_lista);
        }
    }
}
