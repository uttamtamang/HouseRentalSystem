package com.example.meroghar.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meroghar.Models.Property;
import com.example.meroghar.Models.User;
import com.example.meroghar.R;
import com.example.meroghar.URL.Url;
import com.example.meroghar.UpdatePropertyActivity;
import com.example.meroghar.strictmode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class MyPropertyAdapter extends RecyclerView.Adapter<MyPropertyAdapter.MyPropertyViewHolder> {

    private List<Property> myProperties;
    private Context context;

    public MyPropertyAdapter(List<Property> myProperties, Context context) {
        this.myProperties = myProperties;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_property_card, parent, false);

        return new MyPropertyAdapter.MyPropertyViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPropertyViewHolder holder, int position) {

        StrictModeClass.StrictMode();

        final Property property = myProperties.get(position);
        final User user= property.getOwner();
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
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePropertyActivity.class);
                intent.putExtra("proId", property.get_id());
                intent.putExtra("proTitle", property.getTitle());
                intent.putExtra("proImage", property.getImage());
                intent.putExtra("proAddress", property.getAddress());
                intent.putExtra("proCategory", property.getCategory());
                intent.putExtra("proPurpose", property.getPurpose());
                intent.putExtra("proPrice", property.getPrice());
                intent.putExtra("proDesc", property.getDescription());
                intent.putExtra("proF1", property.getFacility1());
                intent.putExtra("proF2", property.getFacility2());
                intent.putExtra("proF3", property.getFacility3());
                intent.putExtra("proF4", property.getFacility4());
                intent.putExtra("proBed", property.getBedroom());
                intent.putExtra("proKitchen", property.getKitchen());
                intent.putExtra("proLiving", property.getLivingroom());
                intent.putExtra("proBath", property.getBathroom());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myProperties.size();
    }

    public class MyPropertyViewHolder extends RecyclerView.ViewHolder {
        TextView propertyTitle, propertyPrice, propertyLocation;
        ImageView imgProperty;

        ConstraintLayout constraintLayout;
        public MyPropertyViewHolder(View itemView, final Context context) {
            super(itemView);
            propertyTitle = itemView.findViewById(R.id.propertyTitle);
            propertyPrice = itemView.findViewById(R.id.propertyPrice);
            propertyLocation = itemView.findViewById(R.id.propertyLocation);
            imgProperty = itemView.findViewById(R.id.imgProperty);
            constraintLayout = itemView.findViewById(R.id.my_constProperty);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context, ""+ propertyTitle.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });

        }
    }
}
