package com.example.euro_pc.mypapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import static com.example.euro_pc.mypapplication.MainActivity.picked_color;

public class SecondActivity extends AppCompatActivity {

    private Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Button OkButton = findViewById(R.id.buttonOk);
        final Button CancelButton = findViewById(R.id.buttonCancel);


        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = findViewById(R.id.spinner);
                Intent data = new Intent();
                data.putExtra(MainActivity.COLOR_ID, String.valueOf(s.getSelectedItem()));
                setResult(RESULT_OK, data);
                finish();
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });


    }

}
