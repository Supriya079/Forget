package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    View viewBackDetails;
    TextView itemName,itemLocation;
    ImageView itemImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //view binding with id's
        viewBackDetails = findViewById(R.id.viewBackDetails);
        itemName = findViewById(R.id.categories);
        itemLocation = findViewById(R.id.details_location);
        itemImageView = findViewById(R.id.detailImage);

        //get data through intent (MyStuff.java Activity)
        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("name");
        String receivedLocation =  intent.getStringExtra("location");
        int receivedImage = intent.getIntExtra("image",0);

        //setting received data to views
        itemName.setText(receivedName);
        itemLocation.setText(receivedLocation);
        itemImageView.setImageResource(receivedImage);

        viewBackDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyStuff.class);
                startActivity(i);
            }
        });

    }
}