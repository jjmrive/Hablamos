package com.jjmrive.hablamos;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ImageViewHolder>{

    private final ElementItemClickListener elementItemClickListener;
    private ArrayList<Element> elementList;

    public HomeAdapter(ArrayList<Element> elementList, ElementItemClickListener elementItemClickListener){
        this.elementItemClickListener = elementItemClickListener;
        this.elementList = elementList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_card, parent, false));
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {
        final Element element = elementList.get(position);

        Picasso.with(holder.itemView.getContext())
                .load(element.getUrlPhoto())
                .into(holder.elementImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementItemClickListener.onElementItemClick(holder.getAdapterPosition());
            }
        });
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView elementImageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            elementImageView = (ImageView) itemView.findViewById(R.id.element_image);
        }
    }

}
