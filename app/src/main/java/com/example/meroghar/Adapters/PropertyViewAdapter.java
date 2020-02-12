package com.example.meroghar.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meroghar.Models.Property;
import com.example.meroghar.R;
import com.example.meroghar.URL.Url;
import com.example.meroghar.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PropertyViewAdapter extends RecyclerView.Adapter<PropertyViewAdapter.PropertyViewHolder>{

    private List<Property> properties;
    private Context context;

    public PropertyViewAdapter(List<Property> properties, Context context) {
        this.properties = properties;
        this.context = context;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.property_card, parent, false);

        return new PropertyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        StrictModeClass.StrictMode();

        Property property = properties.get(position);
        String path = Url.imagePath + property.getImage();
        try{
            URL url = new URL(path);
            holder.imgProperty.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.propertyTitle.setText(property.getTitle());
        holder.propertyPrice.setText(property.getPrice());
        holder.propertyLocation.setText(property.getAddress());
        holder.propertyTitle.setText(property.getTitle());
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public void FilterAddress(ArrayList<Property> filteredList){
        properties = filteredList;
        notifyDataSetChanged();
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder{
        TextView propertyTitle, propertyPrice, propertyLocation;
        ImageView imgProperty;
        ImageView idLove;

        public PropertyViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);
            propertyTitle = itemView.findViewById(R.id.propertyTitle);
            propertyPrice = itemView.findViewById(R.id.propertyPrice);
            propertyLocation = itemView.findViewById(R.id.propertyLocation);
            imgProperty = itemView.findViewById(R.id.imgProperty);
            idLove = itemView.findViewById(R.id.idLove);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, ""+ propertyTitle.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
