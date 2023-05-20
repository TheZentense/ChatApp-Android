package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.chatapp.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class Chat extends AppCompatActivity {

    ActivityChatBinding binding;

    String receiverId;
    String enviar,recibir;

    MensajeAdapter mensajeAdapter;


    DatabaseReference databaseReferenceEnviar, databaseReferenceRecibir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        receiverId=getIntent().getStringExtra("id");

        enviar= FirebaseAuth.getInstance().getUid()+enviar;
        recibir= receiverId+FirebaseAuth.getInstance().getUid();

        mensajeAdapter=new MensajeAdapter(this);
        binding.recycler.setAdapter(mensajeAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));


        databaseReferenceEnviar= FirebaseDatabase.getInstance().getReference("chats").child(enviar);
        databaseReferenceRecibir= FirebaseDatabase.getInstance().getReference("chats").child(recibir);


        databaseReferenceEnviar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mensajeAdapter.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MensajeModel mensajeModel= dataSnapshot.getValue(MensajeModel.class);
                    mensajeAdapter.add(mensajeModel);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.enviarMensaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=binding.mensajeE.getText().toString();
                if(message.trim().length()>0){
                    enviar(message);
                }
            }
        });
    }

    private void enviar(String message) {
        String mensajeId= UUID.randomUUID().toString();
        MensajeModel mensajeModel=new MensajeModel(mensajeId,FirebaseAuth.getInstance().getUid(),message);

        mensajeAdapter.add(mensajeModel);


        databaseReferenceEnviar
                .child(mensajeId)
                .setValue(mensajeModel);
        databaseReferenceRecibir
                .child(mensajeId)
                .setValue(mensajeModel);

    }
}