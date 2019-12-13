package com.photogram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photogram.R;
import com.photogram.modelo.Usuario;

import java.util.List;

public class UsuariosListAdapter extends RecyclerView.Adapter<UsuariosListAdapter.ViewHolder> implements View.OnClickListener {

    private List<Usuario> mDataSet;
    private View.OnClickListener listener;

    public UsuariosListAdapter(List<Usuario> mDataSet, View.OnClickListener listener) {
        this.mDataSet = mDataSet;
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(this.listener != null){
            listener.onClick(view);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_usuarios, parent, false);
        v.setOnClickListener(this);
        UsuariosListAdapter.ViewHolder vh = new UsuariosListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtUsername.setText(mDataSet.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtUsername;
        public ViewHolder(View v){
            super(v);
            txtUsername = v.findViewById(R.id.txt_usuario);
        }
    }
}
