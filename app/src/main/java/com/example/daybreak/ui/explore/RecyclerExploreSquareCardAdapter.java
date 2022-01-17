package com.example.daybreak.ui.explore;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daybreak.R;

import java.util.ArrayList;

public class RecyclerExploreSquareCardAdapter extends RecyclerView.Adapter<RecyclerExploreSquareCardAdapter.MyViewHolder> {
    private ArrayList<RecyclerExploreCardItem> exploreCardItems;
    private RecyclerExploreSquareCardAdapter.MyRecyclerViewItemClickListener mItemClickListener;

    public RecyclerExploreSquareCardAdapter(ArrayList<RecyclerExploreCardItem> countries, RecyclerExploreSquareCardAdapter.MyRecyclerViewItemClickListener itemClickListener) {
        this.exploreCardItems = countries;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerExploreSquareCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_recycleritem, parent, false);
        final RecyclerExploreSquareCardAdapter.MyViewHolder myViewHolder = new RecyclerExploreSquareCardAdapter.MyViewHolder(view);

        //Item Clicks
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClicked(exploreCardItems.get(myViewHolder.getLayoutPosition()));
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerExploreSquareCardAdapter.MyViewHolder holder, int position) {
        // Setting the title of the explore card
        holder.textViewTitle.setText(exploreCardItems.get(position).getTitle());

        // Setting the subtitle of the explore card
        holder.textViewSubtitle.setText(exploreCardItems.get(position).getSubtitle());

        // Setting the image of the explore card
        Drawable image = exploreCardItems.get(position).getImage();
        holder.imageViewName.setImageDrawable(image);
    }

    @Override
    public int getItemCount() {
        return exploreCardItems.size();
    }

    //RecyclerView View Holder
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewSubtitle;
        private ImageView imageViewName;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.explore_card_title);
            textViewSubtitle = itemView.findViewById(R.id.explore_card_subtitle);
            imageViewName = itemView.findViewById(R.id.explore_card_image);
        }
    }

    //RecyclerView Click Listener
    public interface MyRecyclerViewItemClickListener {
        void onItemClicked(RecyclerExploreCardItem title);
    }
}