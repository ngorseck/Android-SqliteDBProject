package com.isi.sqliteappuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.isi.sqliteappuser.dao.IUser;
import com.isi.sqliteappuser.dao.UserImpl;
import com.isi.sqliteappuser.entities.User;

public class MainActivity extends AppCompatActivity {

    private EditText emailtxt, passwordtxt;
    private Button conbt;
    private TextView inscriptionlb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailtxt = (EditText) findViewById(R.id.emailtxt);
        passwordtxt = (EditText) findViewById(R.id.passwordtxt);
        conbt = (Button) findViewById(R.id.connexionbt);
        inscriptionlb = (TextView) findViewById(R.id.inscriptionlb);

        inscriptionlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        conbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailtxt.getText().toString();
                String pwd = passwordtxt.getText().toString();
                if(email.equals("") || pwd.equals("")) {
                    Toast.makeText(MainActivity.this,
                            "Veillez remplir tous les champs",
                            Toast.LENGTH_LONG).show();
                } else {
                    IUser userdao = new UserImpl(MainActivity.this);
                    User user = userdao.login(email, pwd);
                    if(user != null) {
                        Intent intent = new Intent(MainActivity.this, AccueilActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Email ou mot de passe incorrect !",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }




}