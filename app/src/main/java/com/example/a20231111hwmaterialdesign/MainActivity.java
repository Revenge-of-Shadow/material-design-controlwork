package com.example.a20231111hwmaterialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap image0 = BitmapFactory.decodeResource(getResources(), R.drawable.malina);
        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.uzi);
        byte [] bytes0 = DbBitmapUtility.getBytes(image0);
        byte [] bytes1 = DbBitmapUtility.getBytes(image1);
        Album album0 = new Album("Helltaker", "Mittsies",  bytes0, 2020);
        Album album1 = new Album("Murder Drones soundtrack", "Aj DiSpirito", bytes1, 2021);

        Album.deleteAll(Album.class);

            album0.save();
            album1.save();

        List<Album> albums = Album.listAll(Album.class);




            CustomAdapter customAdapter;
            customAdapter = new CustomAdapter(albums);

            RecyclerView rv = findViewById(R.id.rv);
            rv.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(layoutManager);


            rv.setAdapter(customAdapter);






    }
}