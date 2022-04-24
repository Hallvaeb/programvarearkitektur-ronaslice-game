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
    public void SetTop10Lists() {
        /*
         * Getting names and score from the 10 users with highest score.
         * Initializing "nameList" and "scoreList".
         */
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /** Getters for top 10 names and scores */
    @Override
    public List<String> GetTopNames() {
        return nameList;
    }

    @Override
    public List<Float> GetTopScores() {
        return scoreList;
    }

    /** Setting new user with name and score in the Firebase database */
    @Override
    public void SetValueInDB(String target, float value) {
        String ID = myRef.child("users").push().getKey();

        myRef.child("users").child(ID).child("name").setValue(target);
        myRef.child("users").child(ID).child("score").setValue(value);

    }
}
