package com.photogram.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photogram.R;
import com.photogram.modelo.Foto;
import com.photogram.servicesnetwork.ApiEndPoint;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProfilePhotosAdapter extends RecyclerView.Adapter<ProfilePhotosAdapter.ViewHolder> {

    private List<Foto> mDataSet;

    public ProfilePhotosAdapter(List<Foto> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public ProfilePhotosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_foto_profile, parent, false);
        ProfilePhotosAdapter.ViewHolder vh = new ProfilePhotosAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(ApiEndPoint.hostDownloads + mDataSet.get(position).getPath()).into(holder.imgView);
        holder.txtUsername.setText(mDataSet.get(position).getUsuario());
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtUsername;
        private ImageView imgView;


        public ViewHolder(View v){
            super(v);
            this.txtUsername = v.findViewById(R.id.txtUsername_foto_profile);
            this.imgView = v.findViewById(R.id.imageView_profile);
        }
    }
}
