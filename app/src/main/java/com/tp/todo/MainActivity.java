package com.tp.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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

    public void addTodo(View view) {

       EditText input = findViewById(R.id.getTask);
       Spinner list = findViewById(R.id.list);

       String name = input.getText().toString();
       String urgency = list.getSelectedItem().toString();

       TODO task = new TODO(name,urgency);

      // task.generateTextView();

        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.VTodo);

        TextView todo = new TextView(this);
        todo.setText(task.getNom());
        todo.setBackgroundColor(getResources().getColor(task.getUrgencyColor()));

        container.addView(todo);

    }


}