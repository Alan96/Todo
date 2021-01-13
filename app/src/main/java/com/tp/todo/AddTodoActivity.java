package com.tp.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddTodoActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_todo);

        Spinner list = findViewById(R.id.list);

        // Permet de remplir la liste déroulante
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.importance));
        listAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        list.setAdapter(listAdapter);

        final Intent intent = new Intent(this, MainActivity.class);

        Button cancel = findViewById(R.id.buttonCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setContentView(R.layout.activity_main);
                startActivity(intent);

            }
        });

    }


    @SuppressLint("ResourceType")
    public void addTodo(View view) {

        EditText input = findViewById(R.id.getTask);
        Spinner list = findViewById(R.id.list);

        String name = input.getText().toString();
        String urgency = list.getSelectedItem().toString();

        if (name.length() < 3) {
            Toast toast = Toast.makeText(this, "Votre TODO doit contenir au moins 3 caractères", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        TODO task = new TODO(name, urgency);

        TODO.addTodo(task);
        input.setText("");
        hideKeyboard(this);
        Toast toast = Toast.makeText(this, "Tâche ajoutée !", Toast.LENGTH_SHORT);
        toast.show();
    }


    // Fonction qui cache le clavier (trouvé sur internet)
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}