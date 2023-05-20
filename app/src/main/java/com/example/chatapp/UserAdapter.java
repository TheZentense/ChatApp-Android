package com.example.chatapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private Context context;
    private List<UsersModel> usersModelList;

    public UserAdapter(Context context){
        this.context = context;
        usersModelList=new ArrayList<>();


    }

    public void add(UsersModel usersModel ){
        usersModelList.add(usersModel);
        notifyDataSetChanged();
    }

    public void clear(){
        usersModelList.clear();
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UsersModel usersModel=usersModelList.get(position);
        holder.nombre.setText(usersModel.getUserName());
        holder.correo.setText(usersModel.getUserEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Chat.class);
                intent.putExtra("id",usersModel.getUserId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre,correo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.userName);
            correo=itemView.findViewById(R.id.userEmail);

        }
    }

}
