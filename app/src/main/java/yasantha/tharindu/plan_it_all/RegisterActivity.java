package yasantha.tharindu.plan_it_all;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.*;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText user_name,email,password;
    Button btn_register;
    FirebaseAuth mAuth;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();


        user_name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        password = findViewById(R.id.user_pw);
        btn_register = findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username=user_name.getText().toString();
                String txt_email=email.getText().toString();
                String txt_pw=password.getText().toString();

                if(TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_pw)){

                    Toast.makeText(RegisterActivity.this, "All Fields are Required!", Toast.LENGTH_SHORT).show();
                }else if (txt_pw.length()<6){
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6", Toast.LENGTH_SHORT).show();
                } else {
                    register(txt_username,txt_email,txt_pw);
                }
            }
        });

    }

    private void register(final String user_name, String email, String password){
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=mAuth.getCurrentUser();
                            assert firebaseUser!=null;
                            String user_id=firebaseUser.getUid();

                            reference= FirebaseDatabase.getInstance().getReference("Users").child(user_id);

                            HashMap<String, String> hashMap= new HashMap<>();
                            hashMap.put("id",user_id);
                            hashMap.put("username",user_name);
                            hashMap.put("imageURL","default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent= new Intent(RegisterActivity.this,MainView.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this, "You can register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }}
