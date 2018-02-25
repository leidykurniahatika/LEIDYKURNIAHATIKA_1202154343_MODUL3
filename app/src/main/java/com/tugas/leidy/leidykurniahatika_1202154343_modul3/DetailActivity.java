package com.tugas.leidy.leidykurniahatika_1202154343_modul3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    // inisialisasi variabel
    private ImageView WaterView;

    //inisialisasi nilai level = 0
    private int level = 0;

    TextView isi, ProductTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Variabel waterview mengambil id dari waterview
        WaterView = (ImageView) findViewById(R.id.WaterView);

        //Variabel isi mengambil id isi
        isi = (TextView) findViewById(R.id.isi);
        ProductTitle = findViewById(R.id.newsTitleDetail);

        // Untuk memanggil method isi
        isi();

        //Set the text from the Intent extra
        ProductTitle.setText(getIntent().getStringExtra(Water.TITLE_KEY));

    }

    public void isi() {
        //untuk menampilkan isi water
        switch (level) {
            case 0:
                isi.setText("2L");
                break;
            case 1:
                isi.setText("3L");
                break;
            case 2:
                isi.setText("5L");
                break;
            case 3:
                isi.setText("6L");
                break;
            case 4:
                isi.setText("8L");
                break;
            case 5:
                isi.setText("Full");
                break;
        }

    }

    public void Plus(View view) {
        if (level < 5) { // jika kondisi <5
            level++; //level water ditambah
            WaterView.setImageLevel(level); //mengambil image dengan sesuai level
        } else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Air sudah full", Toast.LENGTH_SHORT); // menampilkan toast jika air sudah penuh
            toast.show();
        }
        isi(); //memanggil method isi
    }

    public void Minus(View view) {
        if (level > 0) { //jika level lebih dr 0
            level--; //level water dikurang
            WaterView.setImageLevel(level); //menampilkan image dengan level yang sesuai
        } else {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Air sedikit", Toast.LENGTH_SHORT); //menampilakn toast jika air sedikit
            toast.show();
        }
        isi(); //memanggil method isi
    }
}
