package com.example.ayush.educile_merchant;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ProductViewHolder> {


    private Context mCtx;

    Intent it,it1;
    Context cc;
    Integer i;

    private List<Products> productList;

    public Myadapter(Context mCtx, List<Products> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Products product = productList.get(position);

        holder.name.setText(product.getName());
        holder.phone.setText(product.getTime());
        holder.time.setText(product.getPhone());
        holder.tv5.setText(product.getChapter());
        holder.textview4.setText(product.getObjectId());
        holder.tv4.setText(product.getPayment());
        holder.tutor.setText(product.getTutor());
        holder.rupees.setText(product.getRupees());
        holder.address.setText(product.getAddress());
        holder.ac.setText(product.getAc());






    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, time,tv4,tv5,textview4,tutor,rupees,address,ac;
        Button bt;

        public ProductViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv1);
            phone = itemView.findViewById(R.id.tv2);
            time = itemView.findViewById(R.id.tv3);
            bt=itemView.findViewById(R.id.button2);
            rupees=itemView.findViewById(R.id.rupees);
            tv4=itemView.findViewById(R.id.tv4);
            ac=itemView.findViewById(R.id.ac);
            textview4=itemView.findViewById(R.id.textView4);
            address=itemView.findViewById(R.id.address);

            tutor=itemView.findViewById(R.id.tutor);
            tv5=itemView.findViewById(R.id.tv5);
            cc=itemView.getContext();
            Backendless.setUrl(Defaults.SERVER_URL);
            Backendless.initApp(cc, Defaults.APPLICATION_ID, Defaults.API_KEY);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    Products mypage1 = productList.get(getAdapterPosition());
                    it = new Intent(mCtx, Main2Activity.class);
                    it.putExtra("Title", mypage1.getName());
                    it.putExtra("Cost", mypage1.getPhone());
                    it.putExtra("Topic",mypage1.getChapter());
                    it.putExtra("Time",mypage1.getTime());
                    it.putExtra("objectId",mypage1.getObjectId());
                    it.putExtra("Address",mypage1.getAddress());



                    //it.putExtra(sgd1,mypage1.getCost());
                    cc.startActivity(it);




                }
            });

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        AlertDialog alertDialog = new AlertDialog.Builder(cc)
                                    //set icon

                                    //set title
                                    .setTitle("Are you sure?")
                                    //set message
                                    .setMessage("Payment will be marked as 'PAID'")
                                    //set positive button
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                            Map changes = new HashMap<>();
                                            Products mypage2 = productList.get(getAdapterPosition());
                                            it1 = new Intent(mCtx, Main2Activity.class);

                                            changes.put("objectId",mypage2.getObjectId());
                                            changes.put( "Payment", "Paid" );

                                            Backendless.Persistence.of("Mybookings").save(changes, new AsyncCallback<Map>() {
                                                @Override
                                                public void handleResponse(Map response) {
                                                    tv4.setText("Paid");
                                                    Toast.makeText(cc,"Payment Marked",Toast.LENGTH_LONG).show();
                                                }

                                                @Override
                                                public void handleFault(BackendlessFault fault) {
                                                    Toast.makeText(cc,fault.getMessage(),Toast.LENGTH_LONG).show();
                                                }
                                            });

                                        }
                                    })
                                    //set negative button
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //set what should happen when negative button is clicked
                                            Toast.makeText(cc,"Payment is not marked",Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .show();
                        }
            });
        }

        public void filterList(ArrayList<Products> filterdNames) {
            productList = filterdNames;
            notifyDataSetChanged();
        }


    }
}



