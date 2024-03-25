package com.example.copyshare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button copy, clear, share;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textId);
        copy = findViewById(R.id.copyButton);
        clear = findViewById(R.id.clearId);
        share = findViewById(R.id.shareId);


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Text = textView.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Data", Text);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity.this, "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                textView.setText("");
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,textView.getText().toString());
                intent.setType("text/plain");
                intent = Intent.createChooser(intent,"share via: ");
                startActivity(intent);
            }
        });

    }
}