package edu.temple.howcanihelpapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/**
 * The purpose of this class is to create the post page that uses the activity_post.xml
 * for its layout
 */
public class PostActivity extends AppCompatActivity {
    /**
     * title is a TextView that displays the title from the RequestInfoPost that the marker contains
     * dt is the TextView that displays the date and time posted from RequestInfoPost
     * u is the TextView that displays whether the posting is urgent, from RequestInfoPost
     * r is the TextView that displays whether the owner is able to relocate, from RequestInfoPost
     * makeRequest is a Button with the function of confirming the request if user decides to proceed
     */
    TextView title, dt, u, r;
    Button makeRequest;

    @Override
    /**
     * onBackPressed() doesn't return anything. Its purpose it to go to the previous activity.
     */
    public void onBackPressed() {
        super.onBackPressed();
        PostActivity.this.finish();
    }

    @Override
    /**
     * onCreate(...) doesn't return anything. Its purpose is to build the post page.
     */
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
            /**
             * onClick(...) doesn't return anything.
             */
            public void onClick(View v) {

            }
        });
    }


}
