package com.canteen_ordering;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText entered_table;
    Button save_table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entered_table = findViewById(R.id.entered_table);
        save_table = findViewById(R.id.sub_table);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("amount_samosa", "0");
        editor.putString("amount_pakoda", "0");
        editor.putString("amount_poha", "0");
        editor.putString("amount_sprite", "0");
        editor.putString("amount_coke", "0");
        editor.putString("amount_sting", "0");
        editor.apply();



        save_table.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String hhhhh = entered_table.getText().toString();
                        if(hhhhh.isEmpty()){
                            Toast.makeText(MainActivity.this, "Please enter table number", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            int entered = Integer.parseInt(entered_table.getText().toString());
                            if (entered == 0 || entered > 6) {
                                Toast.makeText(MainActivity.this, "Please enter a valid table number", Toast.LENGTH_SHORT).show();
                            } else {
                                String tab_num = entered_table.getText().toString();
                                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("table_number", tab_num);
                                editor.apply();

                                Intent menu = new Intent(MainActivity.this, menu.class);
                                startActivity(menu);


                            }
                        }
                    }
                }
        );


    }

}