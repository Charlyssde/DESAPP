package com.photogram.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.photogram.modelo.Mensaje;
import com.photogram.R;

import java.util.List;

public class MensajesAdapter extends BaseAdapter {
    List<Mensaje> mensajes;
    private View.OnLongClickListener listener;
    private Context context;

    public MensajesAdapter(List<Mensaje> mensajes, View.OnLongClickListener listener, Context context) {
        this.mensajes = mensajes;
        this.listener = listener;
        this.context = context;



    }

    @Override
    public int getCount() {
        return mensajes.size();
    }

    @Override
    public Object getItem(int i) {
        return mensajes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MensajeViewHolder holder = new MensajeViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Mensaje m = mensajes.get(i);

        if (m.isMine()) { // this message was sent by us so let's create a basic chat bubble on the right
            view = inflater.inflate(R.layout.list_mensaje, null);
            holder.messageBody = view.findViewById(R.id.message_body);
            view.setTag(holder);
            holder.messageBody.setText(m.getContenido());
        } else { // this message was sent by someone else so let's create an advanced chat bubble on the left
            view = inflater.inflate(R.layout.list_mensaje_recibido, null);
            holder.messageBody = view.findViewById(R.id.message_body_recibido);
            view.setTag(holder);
            holder.messageBody.setText(m.getContenido());
        }

        return view;
     }

    class MensajeViewHolder {
        public TextView messageBody;
    }
}
