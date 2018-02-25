package com.tugas.leidy.leidykurniahatika_1202154343_modul3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText mUsername; // inisialisasi variabel username
    private EditText mPassword; // inisialisasi variabel password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = (EditText) findViewById(R.id.edit_username); //variabel username mengambil id dari edit_username
        mPassword = (EditText) findViewById(R.id.edit_password); //variable password mengambil id dari edit_password
    }

    public void Login(View view) {
        //membuat Intent yang mengarah ke class MainActivity
        Intent intent = new Intent(this, MainActivity.class);

        String username = mUsername.getText().toString(); //inisialisai username ke string
        String password = mPassword.getText().toString(); //inisialisai password ke string

        //membuat kondisi berdasarkan username dan password yang telah ditentukan
        if ((username.equals("EAD")) || (password.equals("MOBILE"))) {
            Toast toast = Toast.makeText(this, "Anda Berhasil Login", Toast.LENGTH_SHORT); //menampilkan toast jika button di klik dan berhasil login
            toast.show();
            startActivity(intent);

        } else {
            Toast toast = Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT); //nampilin toast jika button diklik dan tidak berhasil login
            toast.show();
        }
    }
}
