package com.canteen_ordering;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class billing_activity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
    TextView total_price;
    Button make_changes;
    TextView table;

    MyRecyclerViewAdapter adapter;
    MyRecyclerViewAdapter adapter_charges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing_activity);

        //getting data
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String table_number = sharedPreferences.getString("table_number", "");

        //samosa values
        int amount_sam = Integer.parseInt(sharedPreferences.getString("amount_samosa", ""));
        String full_plate_samosa = sharedPreferences.getString("full_plate_samosa","");
        //pakoda values
        int amount_pakoda = Integer.parseInt(sharedPreferences.getString("amount_pakoda",""));
        String full_plate_pakoda = sharedPreferences.getString("full_plate_pakoda","");
        //poha values
        int amount_poha = Integer.parseInt(sharedPreferences.getString("amount_poha", ""));
        String full_plate_poha = sharedPreferences.getString("full_plate_poha","");
        //sprite values
        int amount_sprite = Integer.parseInt(sharedPreferences.getString("amount_sprite",""));
        //coke values
        int amount_coke = Integer.parseInt(sharedPreferences.getString("amount_coke",""));
        //sting vlues
        int amount_sting = Integer.parseInt(sharedPreferences.getString("amount_sting",""));


        int sam_price = 0 ;
        if(full_plate_samosa.toLowerCase(Locale.ROOT).contains("full")){
            sam_price = 25*amount_sam;
        }
        else{
            sam_price = 15*amount_sam;
        }
        int pakoda_price = 0;
        if(full_plate_pakoda.toLowerCase(Locale.ROOT).contains("full")){
            pakoda_price = 25*amount_pakoda;
        }
        else{
            pakoda_price = 15*amount_pakoda;
        }
        int poha_price = 0;
        if(full_plate_poha.toLowerCase(Locale.ROOT).contains("full")){
            poha_price = 25*amount_poha;
        }
        else{
            poha_price = 15*amount_poha;
        }
        int sprite_price = 25*amount_sprite;
        int coke_price = 30*amount_coke;
        int sting_price = 25*amount_sting;

        int total= sam_price+pakoda_price+poha_price+sprite_price+coke_price+sting_price;
        total_price = findViewById(R.id.show_total);
        total_price.setText(Integer.toString(total));



        ArrayList<String> item_rates = new ArrayList<>();
        ArrayList<String> item_names = new ArrayList<>();
        if(amount_sam!=0){
            item_names.add("Samosa "+amount_sam+" "+full_plate_samosa);
            item_rates.add(Integer.toString(sam_price));
        }
        if(amount_pakoda!=0){
            item_names.add("Bread Pakoda "+amount_pakoda+" "+full_plate_pakoda);
            item_rates.add(Integer.toString(pakoda_price));
        }
        if(amount_poha!=0){
            item_names.add("Poha "+amount_poha+" "+full_plate_poha);
            item_rates.add(Integer.toString(poha_price));
        }
        if(amount_sprite!=0){
            item_names.add("Sprite "+amount_sprite+" Bottle");
            item_rates.add(Integer.toString(sprite_price));
        }
        if(amount_coke!=0){
            item_names.add("Coke "+amount_coke+" cans");
            item_rates.add(Integer.toString(coke_price));
        }
        if(amount_sting!=0){
            item_names.add("Sting "+amount_sting+" Bottle");
            item_rates.add(Integer.toString(sting_price));
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.items_recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, item_names);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        RecyclerView rates = findViewById(R.id.price_recycleview);
        rates.setLayoutManager(new LinearLayoutManager(this));
        adapter_charges = new MyRecyclerViewAdapter(this, item_rates);
        adapter_charges.setClickListener(this);
        rates.setAdapter(adapter_charges);

        table= findViewById(R.id.show_table);
        table.setText(table_number);


        make_changes = findViewById(R.id.make_changes);
        make_changes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                }
        );
    }



    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this,   adapter.getItem(position) , Toast.LENGTH_SHORT).show();
    }


}

