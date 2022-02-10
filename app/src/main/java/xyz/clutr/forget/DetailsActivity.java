package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class DetailsActivity extends AppCompatActivity {

    View viewBackDetails;
    TextView itemName,itemLocation,itemDate,itemDescription;
    ImageView itemImageView;
    Bitmap bmImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //view binding with id's
        viewBackDetails = findViewById(R.id.viewBackDetails);
        itemName = findViewById(R.id.categories);
        itemLocation = findViewById(R.id.details_location);
        itemImageView = findViewById(R.id.detailImage);
        itemDate = findViewById(R.id.details_date);
        itemDescription = findViewById(R.id.details_txt);

        //get data through intent
        Intent intent = getIntent();
        String receivedName =  intent.getStringExtra("name");
        String receivedLocation =  intent.getStringExtra("location");
        String receivedImage = intent.getStringExtra("image");
        String receivedDescription = intent.getStringExtra("description");
        String receivedDate = intent.getStringExtra("dob");

        //setting received data to views
        itemName.setText(receivedName);
        itemLocation.setText("Location : "+receivedLocation);

        if (receivedImage.endsWith(".pdf"))
        {
            bmImg  = AccessStorage.pdfToBitmap(new File(receivedImage));
        }
        else if (receivedImage.endsWith(".jpg")){
            bmImg = BitmapFactory.decodeFile(receivedImage);
        }

        itemImageView.setImageBitmap(bmImg);
        itemDescription.setText(receivedDescription);
        itemDate.setText(receivedDate);

        viewBackDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}