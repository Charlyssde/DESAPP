package com.photogram.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photogram.modelo.Conversacion;
import com.photogram.R;

import java.util.List;

public class ChatsListAdapter extends RecyclerView.Adapter<ChatsListAdapter.ViewHolder> implements View.OnClickListener {

    private List<Conversacion> mDataSet;
    private View.OnClickListener listener;

    public ChatsListAdapter(List<Conversacion> mDataSet, View.OnClickListener listener) {
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
    public ChatsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chat, parent, false);
        v.setOnClickListener(this);
        ChatsListAdapter.ViewHolder vh = new ChatsListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatsListAdapter.ViewHolder holder, int position) {
        holder.txtUsername.setText(mDataSet.get(position).getDestinatario().getUsername());
        holder.txtMensaje.setText(mDataSet.get(position).getLastMensaje());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtUsername;
        private TextView txtMensaje;
        public ViewHolder(View v){
            super(v);
            txtUsername = v.findViewById(R.id.tv_username);
            txtMensaje = v.findViewById(R.id.tv_mess_preview);
        }
    }
}
