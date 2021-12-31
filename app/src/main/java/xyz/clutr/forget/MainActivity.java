package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    CardView cardBill,cardMedicalReport,cardObjects,cardDocuments,cardViewMyItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view binding with id's
        floatingActionButton = findViewById(R.id.fab);
        cardViewMyItem = findViewById(R.id.myItemCard);
        cardBill = findViewById(R.id.billCard);
        cardMedicalReport = findViewById(R.id.medicalReportCard);
        cardObjects = findViewById(R.id.objectsCard);
        cardDocuments = findViewById(R.id.documentsCard);

        cardDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyStuff.class);
                startActivity(i);
            }
        });

        cardObjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyStuff.class);
                startActivity(i);
            }
        });

        cardMedicalReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyStuff.class);
                startActivity(i);
            }
        });

        cardBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyStuff.class);
                startActivity(i);
            }
        });

        cardViewMyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MyStuff.class);
                startActivity(i);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Form.class);
                startActivity(i);
            }
        });

    }
}