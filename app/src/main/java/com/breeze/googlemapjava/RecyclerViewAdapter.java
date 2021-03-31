package com.breeze.googlemapjava;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.breeze.googlemapjava.databinding.FormRecyclerViewLayoutBinding;
import com.breeze.googlemapjava.roomdb.FormEntity;
import java.util.ArrayList;

public class RecyclerViewAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    ArrayList<FormEntity> formEntities;

    public RecyclerViewAdapter(ArrayList<FormEntity> formEntities) {
        this.formEntities = formEntities;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        FormRecyclerViewLayoutBinding binding=FormRecyclerViewLayoutBinding.inflate(layoutInflater,parent,false);

        return new RecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        final FormEntity formEntity=formEntities.get(position);
        holder.binding.setForm(formEntity);


    }

    @Override
    public int getItemCount() {
        return formEntities.size();
    }

    public class RecyclerViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder{

        FormRecyclerViewLayoutBinding binding;
        public RecyclerViewHolder(@NonNull FormRecyclerViewLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
