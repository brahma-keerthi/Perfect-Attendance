package com.example.attendencecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText1;
    private EditText editText2;
    private TextView textView;
    private EditText editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editTextNumber);
        editText2 = findViewById(R.id.editTextNumber2);
        editText3 = findViewById(R.id.editTextNumber3);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText1.getText().toString();
                int ab = Integer.parseInt(s);
                s = editText2.getText().toString();
                int pr = Integer.parseInt(s);
                s = editText3.getText().toString();
                double per = Integer.parseInt(s);
                per /= 100;

                if (ab<pr){
                    Toast.makeText(MainActivity.this, "Present is more than Total classes\nReconsider the input", Toast.LENGTH_SHORT).show();
                }

                else if (per>1.0){
                    Toast.makeText(MainActivity.this, "Percent is more than 100\nReconsider the input", Toast.LENGTH_SHORT).show();
                }
                else if (((double)pr/(double) ab) < per) {
                    ab = ab-pr;
                    int pt = (int) ((ab * per) / (1 - per));
                    textView.setText("Not on track\nAttend next " + (pt - pr) + " classes to be on track");
                    Toast.makeText(MainActivity.this, "Thanks for using", Toast.LENGTH_SHORT).show();
                }
                else{
                    ab = ab-pr;
                    int at = (int) ((pr*(1-per))/per);
                    textView.setText("On track\nYou can bunk " + (at-ab) + " classes and still be on track");
                    Toast.makeText(MainActivity.this, "Thanks for using", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}