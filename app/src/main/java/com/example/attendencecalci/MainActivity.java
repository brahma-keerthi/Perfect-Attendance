package com.example.attendencecalci;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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


    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText1 = findViewById(R.id.editTextNumber);
        editText2 = findViewById(R.id.editTextNumber2);
        editText3 = findViewById(R.id.editTextNumber3);
        textView = findViewById(R.id.textView);

        editText1.addTextChangedListener(buttonEnabler);
        editText2.addTextChangedListener(buttonEnabler);
        editText3.addTextChangedListener(buttonEnabler);
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
    private TextWatcher buttonEnabler = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String a = editText1.getText().toString();
            String b = editText2.getText().toString();
            String c = editText3.getText().toString();

            button.setEnabled(!a.isEmpty() && !b.isEmpty() && !c.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}