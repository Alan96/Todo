package com.tp.todo;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class TODO {


    static int ID = 5000000;
    static ArrayList<TODO> todos = new ArrayList<TODO>();
    int id;
    String nom;
    String urgency;


    public TODO() {
        this.id = ID;
        ID++;
    }

    public TODO(String nom, String urgency) {
        this.id = ID;
        this.nom = nom;
        this.urgency = urgency;
        ID++;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public static void addTodo(TODO todo) {
        todos.add(todo);
    }

    public static void deleteTodo(int ID) {

        for (TODO todo : TODO.todos) {

            if (todo.getId() == ID) {
                TODO.todos.remove(todo);
                return;
            }
        }

    }

    public int getUrgencyColor() {
        int colorName = R.color.importance1;

        switch (this.urgency) {
            case "Pas urgent":
                colorName = R.color.importance1;
                break;
            case "Urgent":
                colorName = R.color.importance2;
                break;
            case "Très urgent":
                colorName = R.color.importance3;
                break;
        }

        return colorName;
    }
//
//    public void generateTextView(){
//        ConstraintLayout container = (ConstraintLayout) findViewById(R.id.VTodo);
//
//        TextView todo = new TextView(this);
//        todo.setText(getNom());
//        todo.setBackgroundColor(getResources().getColor(getUrgencyColor()));
//
//        container.addView(todo);
//    }

}
