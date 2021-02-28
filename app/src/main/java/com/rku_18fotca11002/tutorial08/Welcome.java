package com.rku_18fotca11002.tutorial08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {
    ListView lstData;
    ArrayList<Item> list;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        lstData = findViewById(R.id.lstData);
        list = generateData();
        MyAdapter myAdapter = new MyAdapter(this, list);
        lstData.setAdapter(myAdapter);

        preferences = getSharedPreferences("user", MODE_PRIVATE);
        String userPreference =preferences.getString("username","");
        if(userPreference.equals("")){
            startActivity(new Intent(Welcome.this,Login.class));
            finish();
        }
    }

    private ArrayList<Item> generateData() {
        ArrayList<Item> list = new ArrayList<Item>();

        list.add(new Item("Fenil Babariya"));

        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        switch (id){
            case R.id.logout:
                SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor =preferences.edit();
                editor.remove("username");
                editor.commit();

                startActivity(new Intent(Welcome.this, Login.class));
                finish();
                return true;

            case R.id.about:
                Toast.makeText(this, "No information available", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}