package com.isi.sqliteappuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.isi.sqliteappuser.dao.IUser;
import com.isi.sqliteappuser.dao.UserImpl;
import com.isi.sqliteappuser.entities.User;

public class InscriptionActivity extends AppCompatActivity {

    private EditText iemailtxt, ipasswordtxt, ietattxt;
    private Button inscriptionbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        iemailtxt = (EditText) findViewById(R.id.iemailtxt);
        ipasswordtxt = (EditText) findViewById(R.id.ipasswordtxt);
        ietattxt = (EditText) findViewById(R.id.ietattxt);
        inscriptionbt = (Button) findViewById(R.id.inscriptionbt);

        inscriptionbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = iemailtxt.getText().toString();
                String pwd = ipasswordtxt.getText().toString();
                int etat = Integer.parseInt(ietattxt.getText().toString());

                User user = new User();
                user.setEmail(email);
                user.setPassword(pwd);
                user.setEtat(etat);

                IUser userdao = new UserImpl(InscriptionActivity.this);

                long result = 0;
                try {
                    result = userdao.add(user);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (result != 0) {
                    Toast.makeText(InscriptionActivity.this,
                            "Données ajoutées avec succès",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InscriptionActivity.this,
                            "Erreur,  données non ajoutées",
                            Toast.LENGTH_LONG).show();
                }
                iemailtxt.setText("");
                ipasswordtxt.setText("");
                ietattxt.setText("");
            }
        });
    }
}