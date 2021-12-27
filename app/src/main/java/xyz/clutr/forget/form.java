package xyz.clutr.forget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new objectsfragment()).commit();

    }
}