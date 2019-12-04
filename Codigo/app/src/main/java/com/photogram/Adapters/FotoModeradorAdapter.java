package com.photogram.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photogram.R;
import com.photogram.Modelo.FotoModerador;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FotoModeradorAdapter extends RecyclerView.Adapter<FotoModeradorAdapter.ViewHolder> implements View.OnClickListener  {

    private List<FotoModerador> mDataSet;
    private View.OnClickListener listener;


    public FotoModeradorAdapter(List<FotoModerador> mDataSet, View.OnClickListener listener){
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
    public FotoModeradorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_foto_moderador, parent, false);
        v.setOnClickListener(this);
         ViewHolder vh = new ViewHolder(v);
         return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FotoModeradorAdapter.ViewHolder holder, int position) {

        holder.lblUsername.setText(mDataSet.get(position).getUsuario());
        Picasso.get().load("http://10.0.2.2:7777/static/" + mDataSet.get(position).getPath()).into(holder.imgView);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView lblUsername;
        public ImageView imgView;
        public ViewHolder(View v){
            super(v);
            lblUsername = v.findViewById(R.id.lblUsernameMod);
            imgView = v.findViewById(R.id.imageViewMod);
        }
    }
}
