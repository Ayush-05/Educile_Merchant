package com.example.ayush.educile_merchant;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Myadapter1 extends RecyclerView.Adapter<Myadapter1.ProductViewHolder> {


    private Context mCtx;
    Intent it;
    Context cc;
    Integer i;
    Button bt;
    ClipboardManager myClipboard;
    ClipData myClip;

    private List<Products1> productList;

    public Myadapter1(Context mCtx, List<Products1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_layout1, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        Products1 product = productList.get(position);



        holder.name.setText(product.getName());

        holder.time.setText(product.getPhone());
        holder.subjects.setText(product.getSubject());
        holder.chapters.setText(product.getChapters());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myClipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);


                myClip = ClipData.newPlainText("label","Name of tutor: "+holder.name.getText().toString()+"  "+"Phone no. "+holder.time.getText().toString());
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(v.getContext(), "Copied to clipboard" , Toast.LENGTH_SHORT ).show();


            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void filterList(ArrayList<Products1> filterdNames) {
        productList = filterdNames;
        notifyDataSetChanged();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, time,subjects,chapters;

        public ProductViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv12);

            time = itemView.findViewById(R.id.tv13);
            bt=itemView.findViewById(R.id.button);
            subjects=itemView.findViewById(R.id.tv14);
            chapters=itemView.findViewById(R.id.tv15);
            cc=itemView.getContext();

        }
     public void filterList(ArrayList<Products1> filterdNames) {
            productList = filterdNames;
            notifyDataSetChanged();
        }


    }
}



