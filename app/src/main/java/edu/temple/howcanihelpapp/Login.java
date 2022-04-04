package edu.temple.howcanihelpapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.temple.howcanihelpapp.Sql.DBHelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.temple.howcanihelpapp.Firebase.DBHelperF;
import edu.temple.howcanihelpapp.Sql.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText userName, password;
    Button btnSubmit;
    TextView createAcc;
    FirebaseDatabase firebaseDatabase ;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName =findViewById(R.id.text_email);
        password=findViewById(R.id.text_pass);
        btnSubmit = findViewById(R.id.btnSubmit_login);
        DBHelper dbHelper = new DBHelper(this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName1 = userName.getText().toString();
                long  passCheck = Long.parseLong(password.getText().toString());
                firebaseDatabase = FirebaseDatabase.getInstance();
                reference =firebaseDatabase.getReference("User");
                Query query = reference.orderByChild("userName").equalTo(userName1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Long pass = snapshot.child(userName1).child("pass").getValue(Long.class);
                            if (pass.equals(passCheck)){
                                Intent intent = new Intent(Login.this,FinalPage.class);
                                intent.putExtra("userName",userName1);
                                startActivity(intent);
                                Toast.makeText(Login.this,"Right Pass ",Toast.LENGTH_SHORT).show();
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setCancelable(true);
                                builder.setTitle("Wrong Credential");
                                builder.setMessage("Wrong Credential");
                                builder.show();}

                        }else {
                            Toast.makeText(Login.this,"No data exists ",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        createAcc=findViewById(R.id.createAcc);
        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}