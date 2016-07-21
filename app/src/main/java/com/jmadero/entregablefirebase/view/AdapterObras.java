package com.jmadero.entregablefirebase.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejemplo.mercadoLibre.R;
import com.ejemplo.mercadoLibre.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andres on 22/06/16.
 */
public class AdapterObras extends RecyclerView.Adapter{

    private List<Product> productList;
    private Context context;

    public AdapterObras(Context context, List<Product> productList) {

        this.context = context;
        this.productList = new ArrayList<>();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_detalle, parent, false);
        ProductViewHolder unTitularViewHolder = new ProductViewHolder(itemView);
        return unTitularViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product aProduct = productList.get(position);
        ProductViewHolder unTitularViewHolder = (ProductViewHolder) holder;
        unTitularViewHolder.bindProduct(aProduct, context);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private static class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitulo;
        private TextView textViewPrice;
        private ImageView imageViewThumbnail;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitulo = (TextView) itemView.findViewById(R.id.textViewTitulo);
            textViewPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            imageViewThumbnail = (ImageView) itemView.findViewById(R.id.imageViewThumbnail);
        }

        public void bindProduct(Product aProduct, Context context){
            textViewTitulo.setText(aProduct.getTitle());
            textViewPrice.setText(aProduct.getPrice().toString());
            Picasso.with(context).load(aProduct.getThumbnail()).into(imageViewThumbnail);
        }
    }
}
