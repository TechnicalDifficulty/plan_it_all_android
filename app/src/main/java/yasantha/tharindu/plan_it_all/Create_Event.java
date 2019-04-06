package yasantha.tharindu.plan_it_all;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Create_Event extends  AppCompatActivity {

    private Spinner event_spinner, service_spinner;

    private Button add_value;
    private TextView input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__event);

        Firebase.setAndroidContext(this);


        event_spinner=findViewById(R.id.event_spinner);
        service_spinner=findViewById(R.id.service_spinner);


        //Service Array Adapter config

        final ArrayList<Map<String, Object>> list1=new ArrayList<>();
        final ArrayAdapter<Map<String, Object>> adapter1= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list1);

        service_spinner.setAdapter(adapter1);


        //Event Array Adapter Config

        final ArrayList<Map<String, Object>> list2=new ArrayList<>();
        final ArrayAdapter<Map<String, Object>> adapter2= new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,list2);

        event_spinner.setAdapter(adapter2);



        //Service Spinner Fill


        FirebaseDatabase.getInstance().getReference().child("services").addChildEventListener(new ChildEventListener() {


            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Map<String,Object> map_serv=(Map<String, Object>) dataSnapshot.getValue();
                list1.add(map_serv);
                adapter1.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Event Spinner Fill
        FirebaseDatabase.getInstance().getReference().child("events").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                Map<String,Object> map_eve=(Map<String, Object>) dataSnapshot.getValue();
                list2.add(map_eve);
                adapter2.notifyDataSetChanged();

            }

          @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







}


}

















