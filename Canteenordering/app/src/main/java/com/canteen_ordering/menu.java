package com.canteen_ordering;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class menu extends AppCompatActivity {

    ImageView samosa;
    ImageView bread_pakoda;
    ImageView aaloo_poha;
    ImageView sprite;
    ImageView coke;
    ImageView sting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);



        samosa = findViewById(R.id.samosa);
        bread_pakoda = findViewById(R.id.bread_pakoda);
        aaloo_poha = findViewById(R.id.aaloo_poha);
        sprite= findViewById(R.id.sprite);
        coke = findViewById(R.id.coke);
        sting = findViewById(R.id.sting);


        samosa.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent(menu.this, samosa_orders.class);
                        startActivity(intent);


                    }
                }
        );


        bread_pakoda.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bread= new Intent(menu.this, bread_pakoda_activity.class);
                        startActivity(bread);

                    }
                }
        );

        aaloo_poha.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent poha = new Intent(menu.this, aaloo_poha_activity.class);
                        startActivity(poha);
                    }
                }
        );

        sprite.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sprite = new Intent(menu.this, sprite_activity.class);
                        startActivity(sprite);
                    }
                }
        );

        coke.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent coke = new Intent(menu.this, coke_activity.class);
                        startActivity(coke);
                    }
                }
        );

        sting.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent sting = new Intent(menu.this, sting_activity.class);
                        startActivity(sting);
                    }
                }
        );








    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_samosa", "0");
        editor.putString("amount_pakoda", "0");
        editor.putString("amount_poha", "0");
        editor.putString("amount_sprite", "0");
        editor.putString("amount_coke", "0");
        editor.putString("amount_sting", "0");
        editor.apply();
    }
}