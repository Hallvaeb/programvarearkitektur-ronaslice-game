package com.mygdx.game;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.platforminfo.GlobalLibraryVersionRegistrar;
import com.mygdx.game.states.GameStateManager;

import static android.content.ContentValues.TAG;

import java.util.LinkedList;
import java.util.List;

public class AndroidInterfaceClass implements FireBaseInterface{


    FirebaseDatabase database;
    DatabaseReference myRef;
    String key;

    public static List<String> nameList;
    public static List<Float> scoreList;

    public AndroidInterfaceClass() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("");

    }

    @Override
    public void SomeFunction() {
        Query query = myRef.child("users").orderByChild("score").limitToLast(10);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> names = new LinkedList<String>();
                List<Float> scores = new LinkedList<Float>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    String name = postSnapshot.child("name").getValue(String.class);
                    float score = postSnapshot.child("score").getValue(Float.class);
                    names.add(0, name);
                    scores.add(0, score);
                }
                //nameList = names;
                nameList = names;
                scoreList = scores;
                //System.out.println(nameList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public List<String> GetTopNames() {
        return nameList;
    }

    @Override
    public List<Float> GetTopScores() {
        return scoreList;
    }

    @Override
    public void SetOnValueChangedListener() {
        myRef.child("users").addValueEventListener(new ValueEventListener() {
            // Read from the database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot item_snapshot:dataSnapshot.getChildren()) {

                    Log.d("item id ",item_snapshot.child("name").getValue().toString());
                    //Log.d("item desc",item_snapshot.child("item_desc").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void SetValueInDB(String target, float value) {
        String ID = myRef.child("users").push().getKey();

        myRef.child("users").child(ID).child("name").setValue(target);
        myRef.child("users").child(ID).child("score").setValue(value);

    }
}
