package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Form extends AppCompatActivity {

    View viewBackForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //view binding with id's
        viewBackForm = findViewById(R.id.viewFormBack);

        viewBackForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        //set fragment layout (ObjectsFragment) to frame layout of form.
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ObjectsFragment()).commit();

    }
}

