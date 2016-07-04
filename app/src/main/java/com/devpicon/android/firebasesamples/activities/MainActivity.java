package com.devpicon.android.firebasesamples.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devpicon.android.firebasesamples.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseDatabase database;

    private String MESSAGE_CHILD = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Se genera una referencia a la base de datos
        database = FirebaseDatabase.getInstance();

        // Se obtiene referencia a los elementos de la UI
        final Button button1 = (Button) findViewById(R.id.button1);
        final EditText editText1 = (EditText) findViewById(R.id.editText1);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);


        // Se genera una referencia hacia el nodo o elemento que guardará el dato que
        // enviaremos a la base de datos y desde el cual lo leeremos
        final DatabaseReference messageReference = database.getReference().child(MESSAGE_CHILD);

        messageReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    textView2.setText(dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Fallo al leer el valor : " + databaseError.toException());

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valor = editText1.getText().toString();
                Log.d(TAG,"El valor recibido es:" + valor);
                messageReference.setValue(valor);
                editText1.setText("");
            }
        });

    }

}
