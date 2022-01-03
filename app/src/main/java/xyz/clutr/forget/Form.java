package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Form extends AppCompatActivity {

    View viewBackForm, dropView;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    String[] strings={"Bills & Invoice","Medical Report","Objects","Documents","My Items"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //view binding with id's
        viewBackForm = findViewById(R.id.viewFormBack);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter);
        dropView = findViewById(R.id.dropView);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BillFragment()).commit();

        dropView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoCompleteTextView.getText().toString().trim().equals(strings[0])){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new BillFragment()).commit();
                    autoCompleteTextView.clearFocus();
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[1])){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ObjectsFragment()).commit();
                    autoCompleteTextView.clearFocus();
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[2])){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ObjectsFragment()).commit();
                    autoCompleteTextView.clearFocus();
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[3])){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ObjectsFragment()).commit();
                    autoCompleteTextView.clearFocus();
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[4])){
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ObjectsFragment()).commit();
                    autoCompleteTextView.clearFocus();
                }

            }
        });


        viewBackForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }

}

