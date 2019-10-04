package com.example.abhishek.blood;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;



/**
 * Created by Mitch on 2016-05-13.
 */
public class ViewListContents extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private ListView mUserList;
    private ArrayList<String> mUserNames = new ArrayList<>();

    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
     RecyclerView recyclerView;
    private static ArrayList<String> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);


        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<String>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserList = (ListView)findViewById(R.id.listView);
        /*final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUserNames);
        mUserList.setAdapter(arrayAdapter);*/
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //String value = dataSnapshot.getValue(String.class);
                for (DataSnapshot ds: dataSnapshot.getChildren()) {
                    User u = ds.getValue(User.class);
                    /*mUserNames.add(u.Name + " " + u.City + " "+u.BloodGrp + " "+ u.Mobile);
                    mUserNames.add("-------------------------------------------------------");*/

                            /*mUserNames.add("Name: " + u.Name);
                            mUserNames.add("City: " + u.City);
                            mUserNames.add("BloodGroup: " + u.BloodGrp);
                            mUserNames.add("Mobile: " + u.Mobile);
                            mUserNames.add("Gender: " + u.Gender);
                            mUserNames.add("==============================");

                            arrayAdapter.notifyDataSetChanged();*/
                            try{data.add("Name: " +u.Name);
                                data.add("City: " + u.City);
                                data.add("BloodGroup: " + u.BloodGrp);
                                data.add("Mobile: " + u.Mobile);
                                data.add("Gender: " + u.Gender);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }

                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}

