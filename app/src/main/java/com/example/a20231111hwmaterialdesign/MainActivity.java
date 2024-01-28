package com.example.a20231111hwmaterialdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap image0 = BitmapFactory.decodeResource(getResources(), R.drawable.malina);
        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.uzi);

        ArrayList<Album> albums = new ArrayList();
        albums.add(new Album("Title", "Artist",  DbBitmapUtility.getBytes(image0), 1999));
        albums.add(new Album("Title", "Artist",  DbBitmapUtility.getBytes(image1), 1999));
        CustomAdapter customAdapter;
        customAdapter = new CustomAdapter(albums);

        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);


        rv.setAdapter(customAdapter);
//        rv.invalidate();


        /**
         *   I have to use:
         **     Recycler View,
         **     CardView,
         **     NavigationView,
         **     CoordinatorLayout.
         *   I must store pictures in bitmaps. I have done the
         database part, I need to deal with bitmaps themselves.
         ** I could use some support now.
        **/

    }
}