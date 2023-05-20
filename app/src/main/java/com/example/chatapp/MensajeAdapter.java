package com.example.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebHistoryItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.MyViewHolder>{
    private Context context;
    private List<MensajeModel> mensajeModelList;

    public MensajeAdapter(Context context){
        this.context = context;
        mensajeModelList=new ArrayList<>();


    }

    public void add(MensajeModel usersModel ){
        mensajeModelList.add(usersModel);
        notifyDataSetChanged();
    }

    public void clear(){
        mensajeModelList.clear();
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MensajeModel mensajeModel=mensajeModelList.get(position);
        holder.msg.setText(mensajeModel.getMensaje());

     /* if (mensajeModel.getMensajeId().equals(FirebaseAuth.getInstance().getUid())){
            holder.main.setBackgroundColor(context.getColor(R.color.teal_700));
            holder.msg.setTextColor(context.getColor(R.color.white));
        }else {
            holder.main.setBackgroundColor(context.getColor(R.color.black));
            holder.msg.setTextColor(context.getColor(R.color.white));
        }*/
    }

    @Override
    public int getItemCount() {
        return mensajeModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView msg;
        private LinearLayout main;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            msg=itemView.findViewById(R.id.mensajePrincipal);
            main=itemView.findViewById(R.id.enviarMensajeLayout);

        }
    }

}
