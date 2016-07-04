package com.devpicon.android.firebasesamples.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devpicon.android.firebasesamples.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private FirebaseDatabase database;

    private String FRUIT_CHILD = "fruit";

    private String frutas = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        database = FirebaseDatabase.getInstance();

        final Button button2 = (Button) findViewById(R.id.button2);
        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);

        final DatabaseReference fruitReference = database.getReference().child(FRUIT_CHILD);

        fruitReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG, "Fruta añadida:" + dataSnapshot.getValue().toString());
                frutas += (dataSnapshot.getValue().toString() + "\n");
                textView4.setText(frutas);

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
                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fruta = editText2.getText().toString();
                Log.d(TAG, "La fruta recibida fue: " + fruta);
                fruitReference.push().setValue(fruta);
                editText2.setText("");
            }
        });

    }

}
