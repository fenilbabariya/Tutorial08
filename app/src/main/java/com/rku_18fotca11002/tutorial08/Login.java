package com.rku_18fotca11002.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText edtName, edtPassword;
    Button btnLogin;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtName = findViewById(R.id.txtuname);
        edtPassword = findViewById(R.id.txtPsw);
        btnLogin = findViewById(R.id.btnLogin);
        db = new DatabaseHelper(this);
        GetData();
    }

    public void GetData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = edtName.getText().toString();
                String password = edtPassword.getText().toString();

                if(uname.equals("")){
                    Toast.makeText(Login.this, "Username must not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.equals("")){
                    Toast.makeText(Login.this, "Password must not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(uname).matches()){
                    Toast.makeText(Login.this, "Username must be proper Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(Login.this, "Password must not be less than 6 letters", Toast.LENGTH_SHORT).show();
                    return;
                }

                Boolean check = db.checkData(uname, password);
                if(check){
                    Toast.makeText(Login.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,Welcome.class);
                    intent.putExtra("username", uname);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    public void registration(View view) {
        startActivity(new Intent(Login.this,Registration.class));
        finish();
    }
}