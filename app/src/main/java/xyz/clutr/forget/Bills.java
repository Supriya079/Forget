package xyz.clutr.forget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import xyz.clutr.forget.DetailsActivity;
import xyz.clutr.forget.Form;
import xyz.clutr.forget.MainActivity;
import xyz.clutr.forget.R;

public class Bills extends AppCompatActivity {

    RecyclerView recyclerViewBill;
    View viewBackBills;
    FloatingActionButton floatingActionButton;
    List<String> itemName,itemLocation;
    List<Integer> images;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        //view binding with id's
        floatingActionButton = findViewById(R.id.fabBill);
        viewBackBills = findViewById(R.id.viewBackBills);
        recyclerViewBill = findViewById(R.id.billsRecyclerview);

        //static data
        itemName = new ArrayList<>();
        itemLocation = new ArrayList<>();
        images = new ArrayList<>();

        itemName.add("Mobile Bill");
        itemName.add("Bills");
        itemName.add("Electricity Bill");
        itemName.add("BroadBand Bill");
        itemName.add("Shopping Bill");
        itemName.add("Clothes Bill");

        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");

        images.add(R.drawable.broadbandbill);
        images.add(R.drawable.broadbandbill);
        images.add(R.drawable.broadbandbill);
        images.add(R.drawable.broadbandbill);
        images.add(R.drawable.broadbandbill);
        images.add(R.drawable.broadbandbill);

        recyclerAdapter = new RecyclerAdapter(itemName,itemLocation,images,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerViewBill.setLayoutManager(gridLayoutManager);
        recyclerViewBill.setAdapter(recyclerAdapter);

//        recyclerViewBill.findViewHolderForAdapterPosition(0).itemView.performClick();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Form.class);
                startActivity(i);
            }
        });

        viewBackBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>  {

        List<String> itemName,itemLocation;
        List<Integer> image;
        LayoutInflater inflater;

        public RecyclerAdapter(List<String> itemName, List<String> itemLocation, List<Integer> image, Context context) {
            this.itemName = itemName;
            this.itemLocation = itemLocation;
            this.image = image;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.grid_design,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
            holder.itemName.setText(itemName.get(position));
            holder.itemLocation.setText(itemLocation.get(position));
            holder.gridImage.setImageResource(image.get(position));
            holder.recyclerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.putExtra("name", itemName.get(position));
                    intent.putExtra("location", itemLocation.get(position));
                    intent.putExtra("image", images.get(position));
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return itemName.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            TextView itemName,itemLocation;
            ImageView gridImage;
            LinearLayout recyclerLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemName = itemView.findViewById(R.id.item_name);
                itemLocation = itemView.findViewById(R.id.item_location);
                gridImage = itemView.findViewById(R.id.grid_image);
                recyclerLayout = itemView.findViewById(R.id.recyclerlayout);
            }
        }
    }}

