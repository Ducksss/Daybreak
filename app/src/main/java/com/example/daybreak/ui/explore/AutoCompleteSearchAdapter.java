package com.example.daybreak.ui.explore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.example.daybreak.InnerExploreActivity;
import com.example.daybreak.R;
import com.google.android.material.card.MaterialCardView;

public class AutoCompleteSearchAdapter extends ArrayAdapter<SearchItem> {
    private List<SearchItem> searchListFull;

    public AutoCompleteSearchAdapter(@NonNull Context context, @NonNull List<SearchItem> searchList) {
        super(context, 0, searchList);
        searchListFull = new ArrayList<>(searchList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.search_recycleritem, parent, false
            );
        }

        TextView textViewTitle = convertView.findViewById(R.id.search_title);
        TextView textViewDescription = convertView.findViewById(R.id.search_description);
        ImageView imageViewFlag = convertView.findViewById(R.id.search_image);

        SearchItem searchItem = getItem(position);

        if (searchItem != null) {
            textViewTitle.setText(searchItem.getTitle());
            textViewDescription.setText(searchItem.getSubtitle());
            imageViewFlag.setImageResource(searchItem.getImageResource());
        }
        // Set on click listener
        MaterialCardView btButton = (MaterialCardView) convertView.findViewById(R.id.search_card_view);
        // Cache row position inside the button using `setTag`
        btButton.setTag(position);
        // Attach the click event handler
        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = (Integer) view.getTag();
                Intent intent = new Intent(view.getContext(), InnerExploreActivity.class);
                intent.putExtra("Title", getItem(position).getTitle());
                intent.putExtra("Subtitle", getItem(position).getSubtitle());
                intent.putExtra("Background", getItem(position).getImageResource());
                intent.putExtra("Description", getItem(position).getDescription());
                view.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    private Filter searchFilter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<SearchItem> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(searchListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (SearchItem item : searchListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((SearchItem) resultValue).getTitle();
        }
    };
}