package com.tp.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_add_todo);

        Spinner list = findViewById(R.id.list);

        // Permet de remplir la liste déroulante
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.importance));
        listAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        list.setAdapter(listAdapter);

       // SaveManager saveManager = new SaveManager(this);

        //saveManager.displayAllSaveData();
      //  try {
         //   saveManager.saveData();
       // } catch (IOException | JSONException e) {
        //    e.printStackTrace();
       // }

    }
}