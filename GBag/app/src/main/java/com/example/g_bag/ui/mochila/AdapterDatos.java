package com.example.g_bag.ui.mochila;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.g_bag.Preferences;
import com.example.g_bag.R;
import com.example.g_bag.Usuario;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos>{


    ArrayList<String> listaDatos ;
    Context context;
    final Usuario usuario;

    public AdapterDatos(ArrayList<String> listaDatos, Context context) {
        this.listaDatos = listaDatos;
        this.context = context;
        usuario = Preferences.getUsuario(context,"obusuario");
    }


    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layout = LayoutInflater.from(context);
        View view = layout.inflate(R.layout.items_list,parent,false);
        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDato(listaDatos.get(position));

    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView dato ;
        Switch switchon_off;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = (TextView) itemView.findViewById(R.id.idDato);
            switchon_off = (Switch) itemView.findViewById(R.id.switchOnOff);
            //Cambia el modo de la mochila correspondiente
            switchon_off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(usuario!=null){
                        if(switchon_off.isChecked()){
                            usuario.setModoMochila(getAdapterPosition(),"on");
                        }else{
                            usuario.setModoMochila(getAdapterPosition(),"off");
                        }
                        Preferences.save(view.getContext(),usuario,"obusuario");
                    }
                }
            });

        }


        public void asignarDato(String s) {
            dato.setText(s);
        }
    }
}
