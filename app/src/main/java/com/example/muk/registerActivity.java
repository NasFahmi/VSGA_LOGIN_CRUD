package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class registerActivity extends AppCompatActivity {
    private EditText editTextEmailRegister;
    private EditText editTextPasswordRegister;
    private TextView linkToLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmailRegister = (EditText) findViewById(R.id.eTEmailRegister);
        editTextPasswordRegister = (EditText) findViewById(R.id.eTPasswordRegister);
        linkToLogin = (TextView) findViewById(R.id.linkToLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        linkToLogin.setOnClickListener(view -> {
            Intent toLogin = new Intent(registerActivity.this, loginActivity.class);
            startActivity(toLogin);
            finish();
        });
        btnRegister.setOnClickListener(view -> {
            Intent Register = new Intent(registerActivity.this, loginActivity.class);
            startActivity(Register);
            finish();
        });
    }
}