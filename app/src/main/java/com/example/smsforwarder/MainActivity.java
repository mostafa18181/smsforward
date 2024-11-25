package com.example.smsforwarder;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    EditText inputNumber, outputNumber;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputNumber = findViewById(R.id.inputNumber);
        outputNumber = findViewById(R.id.outputNumber);
        startButton = findViewById(R.id.startButton);

        // Request permissions
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_SMS
        }, 1);

        startButton.setOnClickListener(v -> {
            String input = inputNumber.getText().toString();
            String output = outputNumber.getText().toString();

            if (input.isEmpty() || output.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, SmsReceiver.class);
                intent.putExtra("inputNumber", input);
                intent.putExtra("outputNumber", output);
                Toast.makeText(MainActivity.this, "Service started!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
