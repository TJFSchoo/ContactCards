package com.s2032257.tom.spotifyclone.presentation;

/**
 * Created by Tom on 10-3-2018.
 * De tweede laag van de UI, die opgeroepen wordt op het moment van een onItemClick() (regel 51 in MainActivity) op een item uit de lijst van mensen.
 * Geeft in de UI persoonsgegevens in detail weer.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2032257.tom.spotifyclone.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);                                                   // Opvragen en setten van de layout van bijbehorende .xml element

        Intent intent = getIntent();                                                                // De meegegeven waardes bij het instantiëren van de DetailActivity worden via deze Intent opgehaald en toegepast op diverse UI elementen met persoonsgegevens.
        String imageUrl = intent.getStringExtra(MainActivity.EXTRA_URL);
        String fullName = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String nationality = intent.getStringExtra(MainActivity.EXTRA_NATIONALITY);
        String gender = intent.getStringExtra(MainActivity.EXTRA_GENDER);
        String email = intent.getStringExtra(MainActivity.EXTRA_EMAIL);
        String phone = intent.getStringExtra(MainActivity.EXTRA_PHONE);
        String cell = intent.getStringExtra(MainActivity.EXTRA_CELL);

        ImageView imageDetail = findViewById(R.id.image_view_detail);
        TextView nameDetail = findViewById(R.id.text_view_name_detail);
        TextView nationalityDetail = findViewById(R.id.text_view_nationality_detail);
        TextView genderDetail = findViewById(R.id.text_view_gender_detail);
        TextView emailDetail = findViewById(R.id.text_view_email_detail);
        TextView phoneDetail = findViewById(R.id.text_view_phone_detail);
        TextView cellDetail = findViewById(R.id.text_view_cell_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageDetail);                   // Toepassen van de foto in de UI via Picasso
        nameDetail.setText(fullName);                                                               // Invullen van gegevens van aangeklikte persoon in de TextViews
        nationalityDetail.setText("Nationality: " + nationality);                                   // ''
        genderDetail.setText("Gender: " + gender);                                                  // ''
        emailDetail.setText("Email: " + email);                                                     // ''
        phoneDetail.setText("Phone: " + phone);                                                     // ''
        cellDetail.setText("Cell: " + cell);                                                        // ''

    }
}
