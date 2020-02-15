package com.example.meroghar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meroghar.Models.Wishlist;
import com.example.meroghar.R;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WislistViewHolder> {

    Context mContext;
    List<Wishlist> wishlistList;

    public WishlistAdapter(Context mContext, List<Wishlist> wishlistList) {
        this.mContext = mContext;
        this.wishlistList = wishlistList;
    }

    @NonNull
    @Override
    public WislistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_card, parent, false);
        return new WislistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WislistViewHolder holder, final int i) {
        final Wishlist wishlist = wishlistList.get(i);
        holder.title.setText((wishlist.getTitle()));
        holder.price.setText((wishlist.getPrice()));
        holder.address.setText((wishlist.getLocation()));
        holder.image.setImageResource(wishlist.getImage());
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(), wishlist.getTitle()+" "+ "has been removed", Toast.LENGTH_LONG).show();
                wishlistList.remove(i);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return wishlistList.size();
    }

    public class WislistViewHolder extends RecyclerView.ViewHolder {
        TextView title, price, address;
        ImageView image, btnRemove;

        public WislistViewHolder(View wishview) {
            super(wishview);
            title = wishview.findViewById(R.id.propertyTitle);
            price = wishview.findViewById(R.id.propertyPrice);
            address = wishview.findViewById(R.id.propertyLocation);
            image = wishview.findViewById(R.id.imgProperty);
            btnRemove = wishview.findViewById(R.id.idLove);



        }
    }
}
