package com.rku_18fotca11002.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    Button btnRegister;
    EditText txtFname,txtLname,txtUname,txtPsw;
    Switch switchBranch;
    RadioGroup rdoGroup;
    RadioButton rdoGender;
    Spinner spinCity;
    CheckBox checkBox;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegister = findViewById(R.id.btnRegister);
        txtFname = findViewById(R.id.txtFname);
        txtLname = findViewById(R.id.txtLname);
        txtUname = findViewById(R.id.txtUname);
        txtPsw = findViewById(R.id.txtPsw);
        switchBranch = findViewById(R.id.switchBranch);
        spinCity = findViewById(R.id.spinCity);
        rdoGroup = findViewById(R.id.radioGroup);
        checkBox = findViewById(R.id.checkBox);
        db = new DatabaseHelper(this);
        AddData();
    }

    public void AddData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = txtFname.getText().toString();
                String lname = txtLname.getText().toString();
                String uname = txtUname.getText().toString();
                String psw = txtPsw.getText().toString();
                String city = spinCity.getSelectedItem().toString();
                int id = rdoGroup.getCheckedRadioButtonId();
                rdoGender = findViewById(id);
                String gender = rdoGender.getText().toString();
                String branch = "";
                String profile = "";

                if(fname.equals("")) {
                    Toast.makeText(Registration.this, "First name must not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(lname.equals("")) {
                    Toast.makeText(Registration.this, "Last name must not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(uname.equals("")) {
                    Toast.makeText(Registration.this, "Username name must not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(uname).matches()){
                    Toast.makeText(Registration.this, "Username must be proper Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(psw.equals("")) {
                    Toast.makeText(Registration.this, "Password name must not empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(psw.length() < 6) {
                    Toast.makeText(Registration.this, "Password must not be less than 6 letters", Toast.LENGTH_SHORT).show();;
                    return;
                }

                if(switchBranch.isChecked()){
                    branch = switchBranch.getTextOn().toString();
                }else{
                    branch = switchBranch.getTextOff().toString();
                }

                if(checkBox.isChecked()){
                    profile = "Active";
                }else{
                    profile = "Inactive";
                }

                if(db.insertData(fname,lname,uname,psw,branch,gender,city,profile)){
                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Registration.this,Login.class));
                    finish();
                }else{
                    Toast.makeText(Registration.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}