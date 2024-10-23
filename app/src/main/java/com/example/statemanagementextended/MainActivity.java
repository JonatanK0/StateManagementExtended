package com.example.statemanagementextended;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Klucze do zapisywania stanu
    private static final String KEY_COUNT = "count";
    private static final String KEY_TEXT = "editTextValue";
    private static final String KEY_CHECKBOX = "checkBoxState";
    private static final String KEY_SWITCH = "switchState";

    TextView textView;
    Button button;
    EditText editText;
    CheckBox checkBox;
    Switch switch1;
    TextView textView2;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        checkBox = findViewById(R.id.checkBox);
        switch1 = findViewById(R.id.switch1);
        textView2 = findViewById(R.id.textView2);

        // Przywróć stan, jeśli istnieje
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt(KEY_COUNT, 0); // Przywróć licznik
            editText.setText(savedInstanceState.getString(KEY_TEXT, "")); // Przywróć tekst z EditText
            checkBox.setChecked(savedInstanceState.getBoolean(KEY_CHECKBOX, false)); // Przywróć stan CheckBox
            switch1.setChecked(savedInstanceState.getBoolean(KEY_SWITCH, false)); // Przywróć stan Switch

            // Ustaw widoczność textView2 na podstawie CheckBox
            if (checkBox.isChecked()) {
                textView2.setVisibility(View.VISIBLE);
            } else {
                textView2.setVisibility(View.INVISIBLE);
            }

            // Ustaw tło na podstawie Switch
            if (switch1.isChecked()) {
                findViewById(android.R.id.content).setBackgroundColor(Color.rgb(99, 99, 100));
            } else {
                findViewById(android.R.id.content).setBackgroundColor(Color.WHITE);
            }
        }

        updateCountText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                updateCountText();
            }
        });

        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                findViewById(android.R.id.content).setBackgroundColor(Color.rgb(99, 99, 100));
            } else {
                findViewById(android.R.id.content).setBackgroundColor(Color.WHITE);
            }
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                textView2.setVisibility(View.VISIBLE);
            } else {
                textView2.setVisibility(View.INVISIBLE);
            }
        });
    }

    // Zapisz stan aplikacji
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, count);
        outState.putString(KEY_TEXT, editText.getText().toString());
        outState.putBoolean(KEY_CHECKBOX, checkBox.isChecked());
        outState.putBoolean(KEY_SWITCH, switch1.isChecked());
    }


    private void updateCountText() {
        textView.setText("Licznik: " + count);
    }
}
