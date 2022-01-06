package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    View viewBackForm;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapter;
    Button doneBtn;
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
        doneBtn = findViewById(R.id.button);


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(autoCompleteTextView.getText().toString().trim().equals(strings[0])){
                    Toast.makeText(Form.this, "Data Added in: "+(strings[0]), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Form.this, Bills.class);
                    startActivity(i);
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[1])){
                    Toast.makeText(Form.this, "Data Added in: "+(strings[1]), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Form.this, Bills.class);
                    startActivity(i);
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[2])){
                    Toast.makeText(Form.this, "Data Added in: "+(strings[2]), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Form.this, Bills.class);
                    startActivity(i);
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[3])){
                    Toast.makeText(Form.this, "Data Added in: "+(strings[3]), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Form.this, Bills.class);
                    startActivity(i);
                }
                else if (autoCompleteTextView.getText().toString().trim().equals(strings[4])){
                    Toast.makeText(Form.this, "Data Added in: "+(strings[4]), Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Form.this, MyStuff.class);
                    startActivity(i);
                }
                else {
                    autoCompleteTextView.setError("Enter Valid Category.");
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

