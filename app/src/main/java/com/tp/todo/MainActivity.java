package com.tp.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
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

    private int lastTodoIdCreated = 0;

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

    @SuppressLint("ResourceType")
    public void addTodo(View view) {

        EditText input = findViewById(R.id.getTask);
        Spinner list = findViewById(R.id.list);

        String name = input.getText().toString();
        String urgency = list.getSelectedItem().toString();

        TODO task = new TODO(name, urgency);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.VTodo);

        TextView todo = new TextView(this);
        todo.setText(task.getNom());
        todo.setBackgroundColor(getResources().getColor(task.getUrgencyColor()));
        todo.setId(task.getId());
        todo.setPadding(150,8, 150, 8);

        constraintLayout.addView(todo);

        centerTodo(task, constraintLayout);
        this.lastTodoIdCreated = task.getId();
    }


    private void centerTodo(TODO task, ConstraintLayout constraintLayout) {
        int lastId;

        if (this.lastTodoIdCreated == 0) {
            lastId = R.id.buttonCancel;
        } else {
            lastId = this.lastTodoIdCreated;
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(task.getId(), ConstraintSet.TOP, lastId, ConstraintSet.BOTTOM, 15);
        constraintSet.connect(task.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 0);
        constraintSet.connect(task.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
        constraintSet.applyTo(constraintLayout);
    }


}