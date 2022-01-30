package com.example.daybreak.ui.explore;

import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daybreak.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class RecyclerExploreLongCardAdapter extends RecyclerView.Adapter<RecyclerExploreLongCardAdapter.MyViewHolder> {
    private ArrayList<RecyclerExploreLongCardItem> exploreLongCardItem;
    private RecyclerExploreLongCardAdapter.MyRecyclerViewItemClickListener mItemClickListener;

    public RecyclerExploreLongCardAdapter(ArrayList<RecyclerExploreLongCardItem> countries, RecyclerExploreLongCardAdapter.MyRecyclerViewItemClickListener itemClickListener) {
        this.exploreLongCardItem = countries;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerExploreLongCardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_recycleritem2, parent, false);
        final RecyclerExploreLongCardAdapter.MyViewHolder myViewHolder = new RecyclerExploreLongCardAdapter.MyViewHolder(view);

        //Item Clicks
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClicked(exploreLongCardItem.get(myViewHolder.getLayoutPosition()));
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerExploreLongCardAdapter.MyViewHolder holder, int position) {
        // Setting the title of the explore card
        holder.textViewTitle.setText(exploreLongCardItem.get(position).getTitle());

        // Setting the subtitle of the explore card
        holder.textViewSubtitle.setText(exploreLongCardItem.get(position).getSubtitle());

        // Setting the image of the explore card
        Drawable image = exploreLongCardItem.get(position).getImage();
        holder.imageViewName.setImageDrawable(image);

        DisplayMetrics displayMetrics = holder.chipGroup.getContext().getResources().getDisplayMetrics();
        int Px = Math.round(38 * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));

        for (String genre : exploreLongCardItem.get(position).getCardChips()) {
            Chip chip = new Chip(holder.chipGroup.getContext());
            chip.setText(genre);
            chip.setHeight(40);
            holder.chipGroup.addView(chip);
        }
    }

    @Override
    public int getItemCount() {
        return exploreLongCardItem.size();
    }

    //RecyclerView View Holder
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewSubtitle;
        private ImageView imageViewName;
        private ChipGroup chipGroup;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.explore_card_title);
            textViewSubtitle = itemView.findViewById(R.id.explore_card_subtitle);
            imageViewName = itemView.findViewById(R.id.explore_card_image);
            chipGroup = itemView.findViewById(R.id.explore_card_chip);
        }
    }

    //RecyclerView Click Listener
    public interface MyRecyclerViewItemClickListener {
        void onItemClicked(RecyclerExploreLongCardItem title);
    }
}
