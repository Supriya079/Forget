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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyStuff extends AppCompatActivity {

    RecyclerView recyclerViewStuff;
    View viewBackStuff;
    FloatingActionButton floatingActionButton;
    List<String> itemName,itemLocation;
    List<Integer> images;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stuff);

        //view binding with id's
        floatingActionButton = findViewById(R.id.fab);
        viewBackStuff = findViewById(R.id.viewBackStuff);
        recyclerViewStuff = findViewById(R.id.stuffRecyclerview);

        //static data
        itemName = new ArrayList<>();
        itemLocation = new ArrayList<>();
        images = new ArrayList<>();

        itemName.add("Watches");
        itemName.add("Plants");
        itemName.add("Painting Brush");
        itemName.add("BroadBand Bill");
        itemName.add("Redecorating");
        itemName.add("Material");

        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        itemLocation.add("Location");
        
        images.add(R.drawable.watches);
        images.add(R.drawable.plants);
        images.add(R.drawable.paintingbrush);
        images.add(R.drawable.broadbandbill);
        images.add(R.drawable.redecorating);
        images.add(R.drawable.material);

        recyclerAdapter = new RecyclerAdapter(itemName,itemLocation,images,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        recyclerViewStuff.setLayoutManager(gridLayoutManager);
        recyclerViewStuff.setAdapter(recyclerAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Form.class);
                startActivity(i);
            }
        });

        viewBackStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

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
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.grid_design,parent,false);
            return new RecyclerAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
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
    }

}
