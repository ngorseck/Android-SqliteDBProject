package com.isi.sqliteappuser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.isi.sqliteappuser.dao.IUser;
import com.isi.sqliteappuser.dao.UserImpl;
import com.isi.sqliteappuser.entities.User;
import com.isi.sqliteappuser.tools.MyAdapterUser;

import java.util.ArrayList;

public class AccueilActivity extends AppCompatActivity {

    private ListView list;
    private ArrayList<User> users = new ArrayList<User>();
    private EditText emailEdit;
    private EditText passwordEdit;
    private EditText etatEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        list = (ListView) findViewById(R.id.listUser);
        chargerListe();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final int idUser = users.get(position).getId();

                AlertDialog.Builder dialog = new AlertDialog.Builder(AccueilActivity.this);
                dialog.setTitle("Modification - Suppression");
                dialog.setMessage("Choisir une action !");

                //Pour la modification
                final View addView = getLayoutInflater().inflate(R.layout.modifuser_layout, null);

                emailEdit = (EditText) addView.findViewById(R.id.emailtxt);
                passwordEdit = (EditText) addView.findViewById(R.id.passwordtxt);
                etatEdit = (EditText) addView.findViewById(R.id.etattxt);

                emailEdit.setText(users.get(position).getEmail());
                passwordEdit.setText(users.get(position).getPassword());
                etatEdit.setText(users.get(position).getEtat()+"");

                dialog.setView(addView);

                dialog.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User user = new User();
                        user.setId(idUser);
                        user.setEmail(emailEdit.getText().toString());
                        user.setPassword(passwordEdit.getText().toString());
                        int etat = Integer.parseInt(etatEdit.getText().toString());
                        user.setEtat(etat);

                        IUser userdao = new UserImpl(AccueilActivity.this);
                        userdao.update(user);
                        chargerListe();
                    }
                });

                dialog.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IUser userdao = new UserImpl(AccueilActivity.this);
                        userdao.delete(idUser);
                        chargerListe();
                    }
                });

                dialog.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    public void chargerListe(){

        IUser userdao = new UserImpl(AccueilActivity.this);

        users = (ArrayList<User>)userdao.getAll();
        MyAdapterUser adpt = new MyAdapterUser(AccueilActivity.this,users);

        list.setAdapter(adpt);
    }
}