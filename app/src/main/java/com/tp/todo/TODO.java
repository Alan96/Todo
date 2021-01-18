package com.tp.todo;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


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

    public void setId(int id) {
        this.id = id;
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

    public static void saveTodos(Context context) {

        Gson gson = new Gson();

        String jsonTodos = gson.toJson(TODO.todos);

        File dir = new File(context.getFilesDir(), "files");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            File saveFile = new File(dir, "saveTodo.json");
            FileWriter writer = new FileWriter(saveFile);
            writer.write(jsonTodos);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restoreTodos(Context context) throws IOException {
        Gson gson = new Gson();
        File dir = new File(context.getFilesDir(), "files");
        File saveFile = new File(dir, "saveTodo.json");

        BufferedReader reader = new BufferedReader(new FileReader(saveFile));

        String resultJson = "";
        String line;
        while ((line = reader.readLine()) != null) {
            resultJson += line;
        }
        reader.close();

        JsonArray jsonArray = (JsonArray) new JsonParser().parse((resultJson.toString()));

        Type todoType = new TypeToken<ArrayList<TODO>>() {
        }.getType();

        todos = gson.fromJson(jsonArray, todoType);

        for (int i = 0; i < TODO.todos.size(); i++){
            todos.get(i).setId(5000000 + i);
        }

    }

}
