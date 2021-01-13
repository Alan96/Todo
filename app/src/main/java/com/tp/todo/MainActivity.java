package com.tp.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    public static int lastTodoIdCreated = 0;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            Intent intent = new Intent(this, AddTodoActivity.class);
            startActivity(intent);
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTodos();
    }


    @SuppressLint("ResourceType")
    public void displayTodos() {

        try {
            TODO.restoreTodos(this);
        } catch (IOException e) {

        }

        final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.Vlist);
        TextView title = findViewById(R.id.tvtitle);
        title.setText("Liste de tâches (" + TODO.todos.size() + ") : ");
        lastTodoIdCreated = 0;

        for (TODO task : TODO.todos) {

            final TextView todo = new TextView(this);
            todo.setText(task.getNom() + " // " + task.getUrgency());
            todo.setBackgroundColor(getResources().getColor(task.getUrgencyColor()));
            todo.setId(task.getId());
            todo.setPadding(300, 15, 300, 15);
            todo.setTextColor(getResources().getColor(R.color.black));

            constraintLayout.addView(todo);
            centerTodo(task, constraintLayout);
            //lastTodoIdCreated = TODO.todos.get((TODO.todos.size() - 1)).getId();

            lastTodoIdCreated = task.getId();

            final Context context = this;

            todo.setOnClickListener(new View.OnClickListener() { // Supprime la tache quand on clique dessus
                @Override
                public void onClick(View v) {
                    TODO.deleteTodo(todo.getId());
                    TODO.saveTodos(context);
                    recreate();
                }
            });
        }
    }


    private void centerTodo(TODO task, ConstraintLayout constraintLayout) {
        int lastId;

        if (lastTodoIdCreated == 0) {
            lastId = R.id.tvtitle;
        } else {
            lastId = lastTodoIdCreated;
        }

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(task.getId(), ConstraintSet.TOP, lastId, ConstraintSet.BOTTOM, 60);
        constraintSet.connect(task.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START, 0);
        constraintSet.connect(task.getId(), ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END, 0);

        constraintSet.applyTo(constraintLayout);
    }
}