package com.tp.todo;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class SaveManager {


    private JSONArray data;
    private Context context;

    public SaveManager(Context context) {
        this.context = context;
        getDataFromFile();
    }


    private void getDataFromFile() {

        try {
            InputStream jsonFile = context.getAssets().open("todoTasks.json");
            int size = jsonFile.available();
            byte[] buffer = new byte[size];
            jsonFile.read(buffer);
            jsonFile.close();

            String jsonData = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");

            this.data = jsonArray;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Log.e("MainActivity", "Erreur");
        }
    }


    public void displayAllSaveData() {
        String taskName;
        String taskUrgency;
        Boolean taskDone;
        try {
            for (int i = 0; i < this.data.length(); i++) {
                taskName = this.data.getJSONObject(i).getString("name");
                taskUrgency = this.data.getJSONObject(i).getString("urgency");
                taskDone = this.data.getJSONObject(i).getBoolean("done");

                if (taskDone) {
                    Log.w("MainActivity", " ");
                    Log.w("MainActivity", "Tache terminée : ");
                } else {
                    Log.w("MainActivity", " ");
                    Log.w("MainActivity", "Tache a faire : ");
                }
                Log.w("MainActivity", taskName + " - " + taskUrgency);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MainActivity", "Erreur");
        }
    }


    public void saveData() {

        //TODO Faire le systeme de sauvegarde de données
    }

}
