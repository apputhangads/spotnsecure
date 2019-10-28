package com.example.tappu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final Pattern EMAIL_ID =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=])" +
                    "(?=\\S+$)" +
                    ".{6,12}" +
                    "$");
    EditText mailid, username, password, phoneno, cpassword;
    Button Register;
    TextView login;
    String uname, pno, emailInput, inputpassword, conpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.name);
        phoneno=findViewById(R.id.phoneno);
        mailid=findViewById(R.id.email);
        password=findViewById(R.id.password);
        cpassword=findViewById(R.id.cpassword);
        Register=findViewById(R.id.register);
        login=findViewById(R.id.textView);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });

    }
    public boolean validate(){
        boolean valid=true;
        if(uname.isEmpty()||uname.length()>50){
            username.setError("please enter valid name");
            valid=false;
        }
        if(pno.isEmpty()||pno.length()!=10){
            phoneno.setError("Please enter valid phone number");
            valid=false;
        }
        if(emailInput.isEmpty()|| (!EMAIL_ID.matcher(emailInput).matches())){
            mailid.setError("Please enter valid email address");
            valid=false;
        }
        if(inputpassword.isEmpty()|| (!PASSWORD_PATTERN.matcher(inputpassword).matches())){
            password.setError("Password is too weak");
            valid=false;
        }
        if(conpassword.isEmpty()||!(conpassword.contentEquals(inputpassword))){
            cpassword.setError("Password does not match");
            valid=false;
        }
        return valid;
    }
    public void initialise() {
        uname = username.getText().toString().trim();
        pno = phoneno.getText().toString().trim();
        emailInput = mailid.getText().toString().trim();
        inputpassword = password.getText().toString().trim();
        conpassword = cpassword.getText().toString().trim();
    }


    public void register() {
        initialise();
        if (!validate()) {
            Toast.makeText(this, "sign up has failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i=new Intent(MainActivity.this,login.class);
            startActivity(i);
        }
    }

}
