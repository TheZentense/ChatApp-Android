package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatapp.databinding.ActivityAutenticacionBinding;
import com.example.chatapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Autenticacion extends AppCompatActivity {
ActivityAutenticacionBinding binding;
String nombre,correo,password;

DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityAutenticacionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo=binding.correo.getText().toString();
                password=binding.password.getText().toString();

                login();

            }
        }); binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre=binding.nombre.getText().toString();
                correo=binding.correo.getText().toString();
                password=binding.password.getText().toString();

                signUp();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

            startActivity(new Intent(Autenticacion.this,MainActivity.class));
            finish();
        }
    }

    private void login() {
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(correo.trim(),password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(Autenticacion.this,MainActivity.class));
                        finish();
                    }
                });
    }
    private void signUp() {
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(correo.trim(),password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        UserProfileChangeRequest userProfileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(nombre).build();
                        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        UsersModel userModel=new UsersModel(FirebaseAuth.getInstance().getUid(),nombre,correo,password);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);

                        startActivity(new Intent(Autenticacion.this,MainActivity.class));
                        finish();
                    }
                });
    }
}