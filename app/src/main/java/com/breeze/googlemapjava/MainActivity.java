package com.breeze.googlemapjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.breeze.googlemapjava.roomdb.DatabaseClient;
import com.breeze.googlemapjava.roomdb.FormEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView addForm,viewMap;
    String sname,semail,saddress,slat,slon;

    RecyclerView recyclerView;
    private String ePattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addForm=findViewById(R.id.addForm);
        viewMap=findViewById(R.id.map);

        recyclerView=findViewById(R.id.recyclerview_form);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<FormEntity> allDetails=new ArrayList<>(DatabaseClient.getInstance(MainActivity.this).formDao().getFormDetails());

        RecyclerViewAdapter adapter=new RecyclerViewAdapter(allDetails);
        recyclerView.setAdapter(adapter);


        addForm.setOnClickListener(goHere->{
            addForm();

        });

        viewMap.setOnClickListener(goHere->{
            startActivity(new Intent(MainActivity.this,MapsActivity.class));
        });


    }


    private void addForm() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.activity_add_form, null);
        EditText name = subView.findViewById(R.id.et_name);
        EditText email = subView.findViewById(R.id.et_email);
        EditText latitude = subView.findViewById(R.id.et_latitude);
        EditText longitude=subView.findViewById(R.id.et_longitude);
        EditText address = subView.findViewById(R.id.et_address);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Details");
        builder.setView(subView);
        builder.setPositiveButton("Add Details", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });


        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean error = false;

                sname = name.getText().toString();
                semail = email.getText().toString();
                slat = latitude.getText().toString();
                slon = longitude.getText().toString();
                saddress = address.getText().toString();

                error = true;

                //Validating all the inputs
                if (sname.isEmpty()) {
                    error = true;
                    name.setError("Name Field Cannot be Empty");
                } else if (!semail.matches(ePattern)) {
                    error = true;
                    email.setError("Invalid Email Id");
                } else if (saddress.isEmpty()) {
                    error = true;
                    address.setError("Address Field Cannot be Empty ");
                } else if (!slat.isEmpty() & !slon.isEmpty()){
                    error = false;

                    new saveForm().execute();
                    finish();
                    startActivity(getIntent());

                } else {
                    error = true;
                    longitude.setError("Invalid Longitude");
                    latitude.setError("Invalid Latitude");
                }
                if (!error) {
                    alertDialog.dismiss();
                }


            }
        });
    }

    class saveForm extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            //creating a task
            FormEntity form = new FormEntity(sname,semail,saddress,slat,slon);
            //adding to database
            DatabaseClient.getInstance(MainActivity.this).formDao().insert(form);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
        }
    }

}