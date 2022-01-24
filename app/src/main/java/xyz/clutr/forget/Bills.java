package xyz.clutr.forget;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import xyz.clutr.forget.Room.BillDAO;
import xyz.clutr.forget.Room.BillDB;
import xyz.clutr.forget.Room.BillEntity;

public class Bills extends AppCompatActivity {

    RecyclerView recyclerViewBill;
    View viewBackBills;
    FloatingActionButton floatingActionButton;
    BillDAO billDAO;
    ImageView empty_imageview;
    TextView no_data;
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
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        billDAO = BillDB.getBillDBInstance(getApplicationContext()).billDAO();
        recyclerAdapter = new RecyclerAdapter(billDAO.getAllBills());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        if (recyclerAdapter.getItemCount() !=0 ){
            recyclerViewBill.setLayoutManager(gridLayoutManager);
            recyclerViewBill.setAdapter(recyclerAdapter);
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }else {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }

/*        //static data
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
        recyclerViewBill.setAdapter(recyclerAdapter);   */

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

//        List<String> itemName,itemLocation;
//        List<Integer> image;
//        LayoutInflater inflater;
        List<BillEntity> data;

//        public RecyclerAdapter(List<String> itemName, List<String> itemLocation, List<Integer> image, Context context) {
//            this.itemName = itemName;
//            this.itemLocation = itemLocation;
//            this.image = image;
//            this.inflater = LayoutInflater.from(context);
//        }
        public RecyclerAdapter(List<BillEntity> billEntities) {
            this.data = billEntities;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = inflater.inflate(R.layout.grid_design,parent,false);
//            return new ViewHolder(view);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_design,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//            holder.itemName.setText(itemName.get(position));
//            holder.itemLocation.setText(itemLocation.get(position));
//            holder.gridImage.setImageResource(image.get(position));

            BillEntity billEntity = data.get(position);
//            holder.category.setText(billEntity.getCategory());
            holder.obj_name.setText(billEntity.getObject_Name());
            holder.location.setText(billEntity.getLocation());
            Bitmap bmImg = BitmapFactory.decodeFile(billEntity.getImagePath());
            holder.imageViewBill.setImageBitmap(bmImg);

            holder.recyclerLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.putExtra("name", billEntity.getObject_Name());
                    intent.putExtra("location", billEntity.getLocation());
                    intent.putExtra("image", billEntity.getImagePath());
                    intent.putExtra("description",billEntity.getDescription());
                    intent.putExtra("dob",billEntity.getDate().toString());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

//            TextView itemName,itemLocation;
//            ImageView gridImage;
            LinearLayout recyclerLayout;
            ImageView imageViewBill;
            TextView category, obj_name, description,location,dob;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                //category = itemView.findViewById(R.id.);
                location = itemView.findViewById(R.id.item_location);
                obj_name = itemView.findViewById(R.id.item_name);
                imageViewBill = itemView.findViewById(R.id.grid_image);
                recyclerLayout = itemView.findViewById(R.id.recyclerlayout);
//                itemName = itemView.findViewById(R.id.item_name);
//                itemLocation = itemView.findViewById(R.id.item_location);
//                gridImage = itemView.findViewById(R.id.grid_image);
            }
        }

    }}
