package com.mygdx.game;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.content.ContentValues.TAG;

public class AndroidInterfaceClass implements FireBaseInterface{


    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndroidInterfaceClass() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
    }

    @Override
    public void SomeFunction() {

    }

    @Override
    public void FirstFireBaseTest() {
        if(myRef != null){
            myRef.setValue("Hello, World!");
        }
        else {
            System.out.println("Databasereference was not set");
        }
    }

    @Override
    public void SetOnValueChangedListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            // Read from the database
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void SetValueInDB(String target, String value) {
        myRef = database.getReference(target);
        myRef.setValue(value);
    }
}
