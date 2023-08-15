package com.example.muk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    DBHelper db = new DBHelper(this);
    private EditText editTextEmailLogin;
    private EditText editTextPasswordLogin;
    private TextView LinkToRegister;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmailLogin= (EditText) findViewById(R.id.eTEmailLogin);
        editTextPasswordLogin = (EditText) findViewById(R.id.etPasswordLogin);
        LinkToRegister = (TextView) findViewById(R.id.linkToRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        LinkToRegister.setOnClickListener(view -> {
            Intent toRegister = new Intent(loginActivity.this, registerActivity.class);
            startActivity(toRegister);
            finish();
        });
        btnLogin.setOnClickListener(view -> {
            String email = editTextEmailLogin.getText().toString();
            String password = editTextPasswordLogin.getText().toString();

            Boolean login = db.login(email,password);
            if (login){
                Intent Login = new Intent(loginActivity.this,dashboardActivity.class);
                startActivity(Login);
                finish();
            }else{
                Toast.makeText(getBaseContext(),"Email atau Password salah",Toast.LENGTH_LONG).show();
            }
        });
    }
}