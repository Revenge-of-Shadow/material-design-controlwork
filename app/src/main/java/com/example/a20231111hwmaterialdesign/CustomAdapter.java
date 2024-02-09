package com.example.a20231111hwmaterialdesign;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/** https://developer.android.com/develop/ui/views/layout/recyclerview#java **/
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.AlbumViewHolder> {

    List<Album> albums;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class AlbumViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView title, artist, year;
        ImageView image;
        public AlbumViewHolder(View view) {
            super(view);

            cv = (CardView) view.findViewById(R.id.cv);
            title = (TextView) view.findViewById(R.id.title);
            artist = (TextView) view.findViewById(R.id.artist);
            year = (TextView) view.findViewById(R.id.year);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param albums List<Album> containing the data to populate views to be used
     * by RecyclerView
     */
    public CustomAdapter(List<Album> albums) {
        this.albums = albums;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_item, viewGroup, false);
        return new AlbumViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(AlbumViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.setText(albums.get(position).getTitle());
        viewHolder.artist.setText(albums.get(position).getArtist());
        viewHolder.year.setText(String.format("%d", albums.get(position).getYear()));
        viewHolder.image.setImageBitmap(DbBitmapUtility.getImage(albums.get(position).getImage()));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView){
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void sortByTitle(){
            for(int i = 0; i<albums.size()-1; ++i){
                if( albums.get(i).getTitle().compareTo(albums.get(i+1).getTitle()) > 0) {
                    Album first = albums.get(i);
                    albums.set(i, albums.get(i+1));
                    albums.set(i + 1, first);
                    sortByTitle();
                }
            }
    }
    public void sortByArtist(){
        for(int i = 0; i<albums.size()-1; ++i){
            if( albums.get(i).getArtist().compareTo(albums.get(i+1).getArtist()) > 0) {
                Album first = albums.get(i);
                albums.set(i, albums.get(i + 1));
                albums.set(i + 1, first);
                sortByArtist();
            }
        }
    }
    public void sortByYear(){
        for(int i = 0; i<albums.size()-1; ++i){
            if( albums.get(i).getYear() > albums.get(i+1).getYear()) {
                Album first = albums.get(i);
                albums.set(i, albums.get(i + 1));
                albums.set(i + 1, first);
                sortByYear();
            }
        }
    }
}

