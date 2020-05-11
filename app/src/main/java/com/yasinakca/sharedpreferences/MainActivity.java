package com.yasinakca.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText ageText;
    TextView nameResult;
    TextView ageResult;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = findViewById(R.id.nameText);
        ageText = findViewById(R.id.ageText);
        nameResult = findViewById(R.id.nameResult);
        ageResult = findViewById(R.id.ageResult);

        sharedPreferences = getSharedPreferences("com.yasinakca.sharedpreferences", Context.MODE_PRIVATE);

        String storedName = sharedPreferences.getString("storedName","");
        int storedAge = sharedPreferences.getInt("storedAge",0);

        if (storedName != "") {
            nameResult.setText("Your name is: " + storedName);
        }else {
            nameResult.setText("Your name is: ");
        }

        if (storedAge != 0) {
            ageResult.setText("Your age is: " + storedAge);
        }else {
            ageResult.setText("Your age is: ");
        }
    }

    public void saveClicked(View view) {
        if (!nameText.getText().toString().matches("") && !ageText.getText().toString().matches("")) {
            String name = nameText.getText().toString();
            int age = Integer.parseInt(ageText.getText().toString());

            nameResult.setText("Your name is: " + name);
            ageResult.setText("Your age is: " + age);

            sharedPreferences.edit().putString("storedName",name).apply();
            sharedPreferences.edit().putInt("storedAge",age).apply();
        }
    }

    public void deleteClicked(View view) {
        String storedName = sharedPreferences.getString("storedName","");
        int storedAge = sharedPreferences.getInt("storedAge",0);

        if (storedName != "") {
            sharedPreferences.edit().remove("storedName").apply();
            nameResult.setText("Your name is: ");
        }

        if (storedAge != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
            ageResult.setText("Your age is: ");
        }
    }
}
