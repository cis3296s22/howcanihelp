package edu.temple.howcanihelpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {
    EditText  number , email,pass,userName;
    TextView login;
    DBHelper dbHelper;
    private DBHelperF dbHelperFS ;
    private DatabaseReference myRef;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName=findViewById(R.id.textName);
        number=findViewById(R.id.textNumber);
        email=findViewById(R.id.textEmail);
        pass=findViewById(R.id.textPass);

        Button signUpAcc = findViewById(R.id.btnSignUpAcc);
        dbHelper = new DBHelper(this);
        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName1 = userName.getText().toString();
                String number1 = number.getText().toString();
                String email1 = email.getText().toString();
                String pass1 = pass.getText().toString();
                long password = Long.parseLong(pass1)  ;
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("User");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (checkUser(snapshot,userName1)){
                            DBHelperF dbHelperFS = new DBHelperF(userName1,number1,email1,password);
                            myRef.child(userName1.toString()).setValue(dbHelperFS);
                            userName.setText("");
                            number.setText("");
                            email.setText("");
                            pass.setText("");
                            Toast.makeText(SignUp.this,"Data inserted",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUp.this,Login.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(SignUp.this,"User Name Already Exists",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        login=findViewById(R.id.loginAcc);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
            }
        });
    }

    private boolean checkUser(DataSnapshot snapshot,String user) {
        String user1;
        for (DataSnapshot ds: snapshot.getChildren()){
            user1 =ds.child("userName").getValue(String.class);
            if (user.equals(user1)){
                return false;
            }
        }
        return true;
    }
}