package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.SignUpCallback;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

public class MainActivity extends AppCompatActivity {


    EditText name;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign Up");

        name = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
    }

    private void alertDisplayer(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void onClick(View view) {

        ParseUser user = new ParseUser();
        String n = name.getText().toString();
        String p = pass.getText().toString();
// Set the user's username and password, which can be obtained by a forms

        user.setUsername(n);
        user.setPassword(p);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(com.parse.ParseException e) {
                if (e == null) {
                    alertDisplayer("congrats", "Worked Successfully");
                    Toast.makeText(MainActivity.this, "Sign up is successful", Toast.LENGTH_LONG).show();

                } else {
                    ParseUser.logOut();
                    Toast.makeText(MainActivity.this, "user name already exists", Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}