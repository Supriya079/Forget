package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Bills extends AppCompatActivity {

    GridView gridView;
    View viewBackBills;
    FloatingActionButton floatingActionButton;
    //static data
    int[] itemImage = {R.drawable.broadbandbill,R.drawable.broadbandbill,R.drawable.broadbandbill,R.drawable.broadbandbill,R.drawable.broadbandbill,R.drawable.broadbandbill};
    String[] itemName = {"Mobile Bill","Electricity Bill","Bills","Broadband Bill","Clothes Bill","Shopping Bill"};
    String[] itemLocation = {"Location","Location","Location","Location","Location","Location"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);

        //view binding with id's
        floatingActionButton = findViewById(R.id.fabBill);
        viewBackBills = findViewById(R.id.viewBackBills);
        gridView = findViewById(R.id.billsGrid_layout);

        GridAdapter gridAdapter = new GridAdapter();
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //send data through intent (Bills.java Activity)
                Intent intent = new Intent(getApplicationContext(),DetailsActivity.class);
                intent.putExtra("name",itemName[position]);
                intent.putExtra("location",itemLocation[position]);
                intent.putExtra("image",itemImage[position]);
                startActivity(intent);
            }
        });

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

    private class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemName.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.grid_design,null);
            //getting view in grid_design binding with id's
            TextView name = view.findViewById(R.id.item_name);
            TextView location = view.findViewById(R.id.item_location);
            ImageView image = view.findViewById(R.id.grid_image);

            //setting data to views
            name.setText(itemName[position]);
            location.setText(itemLocation[position]);
            image.setImageResource(itemImage[position]);
            return view;

        }
    }

}
