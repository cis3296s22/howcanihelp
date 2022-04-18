package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {

    TextView title, dt, u, r;
    Button makeRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        RequestInfoPost selected = (RequestInfoPost) intent.getExtras().getParcelable("WINDOW_POST");

        title = (TextView) findViewById(R.id.testText);
        title.setText(selected.title);

        dt = (TextView) findViewById(R.id.testDT);
        dt.setText(selected.dateTimePosted);

        u = (TextView) findViewById(R.id.testUrgent);
        u.setText((selected.urgent) ? "URGENT" : "");

        r = (TextView) findViewById(R.id.testRelocate);
        r.setText((selected.ableToRelocate) ? "ABLE TO RELOCATE" : "");

        makeRequest = (Button) findViewById(R.id.requestButton);
        makeRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
