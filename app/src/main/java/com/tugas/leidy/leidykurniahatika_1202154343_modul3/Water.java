package com.tugas.leidy.leidykurniahatika_1202154343_modul3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;

public class Water {
    private final String title; //inisialisasi variabel title
    private final String info; //inisialisasi variabel info
    private final int imageResource; //inisialisasi variabel gambar

    static final String TITLE_KEY = "Title";
    static final String IMAGE_KEY = "Image Resource";

    Water(String title, String info, int imageResource) {
        //mengambil nilai title, info dan image dari
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    // set string title dan return
    String getTitle() {
        return title;
    }

    // set string info dan return
    String getInfo() {
        return info;
    }

    // set int image dan return
    int getImageResource() {
        return imageResource;
    }

    static Intent starter(Context context, String title, @DrawableRes int imageResId) {
        //mengarahkan ke class DetailActivity
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(TITLE_KEY, title);
        detailIntent.putExtra(IMAGE_KEY, imageResId);
        return detailIntent;
    }
}
