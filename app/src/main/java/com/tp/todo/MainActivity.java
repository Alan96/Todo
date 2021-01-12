package com.tp.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int lastTodoIdCreated = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner list = findViewById(R.id.list);

        Button test = findViewById(R.id.retour);


        final Intent intent = new Intent(this, AddTodoActivity.class);

        test.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        displayTodos();
    }

    @SuppressLint("ResourceType")
    public void displayTodos() {

        final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.Vlist);

        for (TODO task : TODO.todos) {

            if (TODO.todos.size() < 1) {
                Toast toast = Toast.makeText(this, "Rien a afficher", Toast.LENGTH_SHORT);
                toast.show();
            }

            final TextView todo = new TextView(this);
            todo.setText(task.getNom());
            todo.setBackgroundColor(getResources().getColor(task.getUrgencyColor()));
            todo.setId(task.getId());
            todo.setPadding(150, 8, 150, 8);

//            final Button btn = new Button(this);
//            btn.setText("Supprimer");
//            btn.setId(task.getId() + 1000);
//            btn.setTextSize(12);
//            btn.setHeight(todo.getHeight());

            constraintLayout.addView(todo);
            //constraintLayout.addView(btn);

            todo.setOnClickListener(new View.OnClickListener() { // Supprime la tache quand on clique dessus
                @Override
                public void onClick(View v) {
                    TODO.deleteTodo(todo.getId());
                    constraintLayout.removeView(todo);
                }
            });

//
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("Button : ", String.valueOf((btn.getId() - 1000)));
//                }
//            });

           // centerTodo(task, btn, constraintLayout);
           centerTodo(task, constraintLayout);
            this.lastTodoIdCreated = task.getId();
        }


    }


    private void centerTodo(TODO task, ConstraintLayout constraintLayout) {
        int lastId;

        if (this.lastTodoIdCreated == 0) {
            lastId = R.id.tvtitle;
        } else {
            lastId = this.lastTodoIdCreated;
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(task.getId(), ConstraintSet.TOP, lastId, ConstraintSet.BOTTOM, 50);
        constraintSet.connect(task.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 0);
        constraintSet.connect(task.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);
//
//        constraintSet.connect(btn.getId(), ConstraintSet.START, task.getId(), ConstraintSet.END, 30);
//        constraintSet.connect(btn.getId(), ConstraintSet.TOP, task.getId(), ConstraintSet.TOP, 0);
//        constraintSet.connect(btn.getId(), ConstraintSet.BOTTOM, task.getId(), ConstraintSet.BOTTOM, 0);
//        constraintSet.applyTo(constraintLayout);
    }


}