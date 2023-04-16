package com.canteen_ordering;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class sprite_activity  extends AppCompatActivity {
    Button add_more;
    Button minus;
    Button plus;
    Button place_order;

    EditText eneter;


    public void savedata(){

        String amount_sprite = eneter.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_sprite", amount_sprite);
        editor.apply();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sprite_order);



        plus = findViewById(R.id.sprite_plus);
        minus = findViewById(R.id.sprite_minus);
        eneter = findViewById(R.id.sprite_amount_entered);
        place_order = findViewById(R.id.sprite_final_click);
        add_more = findViewById(R.id.sprite_add_more);




        eneter.setText("0");


        minus.setOnClickListener(
                v -> {
                    int curr = Integer.parseInt(eneter.getText().toString());
                    int cur = curr - 1;
                    String curstr = Integer.toString(cur);
                    if(cur<0){
                        eneter.setText("0");
                    }
                    else {
                        eneter.setText(curstr);
                    }

                }
        );


        plus.setOnClickListener(
                v -> {
                    int curr = Integer.parseInt(eneter.getText().toString());
                    int cur;
                    if(curr >=10){
                        Toast.makeText(sprite_activity.this, "Itna kon order krta hai be", Toast.LENGTH_SHORT).show();
                        cur = 10;
                    }
                    else {
                        cur = curr + 1;
                    }
                    String curstr = Integer.toString(cur);
                    eneter.setText(curstr);
                }
        );


        //sending data to main activity
        add_more.setOnClickListener(
                v -> {
                    savedata();
                    onBackPressed();
                }
        );



        place_order.setOnClickListener(
                v -> {
                    savedata();
                    Intent billing= new Intent(sprite_activity.this, billing_activity.class);
                    startActivity(billing);
                }
        );



    }
}
