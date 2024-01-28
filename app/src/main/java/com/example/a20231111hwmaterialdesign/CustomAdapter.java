package com.example.a20231111hwmaterialdesign;


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

            cv = view.findViewById(R.id.cv);
            title = view.findViewById(R.id.title);
            artist = view.findViewById(R.id.artist);
            year = view.findViewById(R.id.year);
            image = view.findViewById(R.id.image);
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
        AlbumViewHolder avh = new AlbumViewHolder(view);
        return avh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AlbumViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.title.setText(albums.get(position).getTitle());
        viewHolder.artist.setText(albums.get(position).getArtist());
        viewHolder.year.setText(""+albums.get(position).getYear());
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
}

