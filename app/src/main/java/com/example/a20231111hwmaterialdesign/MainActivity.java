package com.example.a20231111hwmaterialdesign;

import android.app.Activity;
import android.graphics.Point;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends Activity {



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Point outSz = new Point();
        //noinspection deprecation
        getWindowManager().getDefaultDisplay().getSize(outSz);
        Bitmap image0 = DbBitmapUtility.retrieveResource(getApplication(), R.drawable.malina, outSz);
        Bitmap image1 = DbBitmapUtility.retrieveResource(getApplication(), R.drawable.uzi, outSz);
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

            RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
            rv.setHasFixedSize(true);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(layoutManager);


            rv.setAdapter(customAdapter);


        Menu m = ((NavigationView) findViewById(R.id.navigation)).getMenu();
        m.findItem(R.id.nav_sort_title).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                customAdapter.sortByTitle();
                rv.setAdapter(customAdapter);
                return true;
            }
        });
        m.findItem(R.id.nav_sort_artist).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                customAdapter.sortByArtist();
                rv.setAdapter(customAdapter);
                    return true;
            }
        });
        m.findItem(R.id.nav_sort_year).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                customAdapter.sortByArtist();
                rv.setAdapter(customAdapter);
                return true;
            }
        });
    }

}