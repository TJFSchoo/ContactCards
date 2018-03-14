package com.s2032257.tom.spotifyclone.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.s2032257.tom.spotifyclone.data_access__application_logic.ApiAsyncTask;
import com.s2032257.tom.spotifyclone.data_access__application_logic.OnTaskCompleted;
import com.s2032257.tom.spotifyclone.domain.Person;
import com.s2032257.tom.spotifyclone.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, OnTaskCompleted {
    static final String EXTRA_URL = "imageUrl";                                                     // non-private voor gebruik in MainActivity én DetailActivity. Public is niet nodig ivm zelfde package.
    static final String EXTRA_NAME = "name";                                                        // ''
    static final String EXTRA_GENDER = "gender";                                                    // ''
    static final String EXTRA_NATIONALITY = "nationality";                                          // ''
    static final String EXTRA_EMAIL = "email";                                                      // ''
    static final String EXTRA_PHONE = "phone";                                                      // ''
    static final String EXTRA_CELL = "cell";                                                        // ''

    private RecyclerView recyclerView;                                                              // Aanmaken van RecyclerView instantie
    private PersonAdapter adapter;                                                                  // Aanmaken van PersonAdapter instantie voor het regelen van RecyclerView dingen en de ViewHolder
    private ArrayList<Person> peopleList;                                                           // Aanmaken van personenlijst instantie
    public ApiAsyncTask aSyncTask = new ApiAsyncTask(this);                                 // Aanmaken en initiëren van asynchrone taak instantie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);                              // Initiëren van instantie van RecyclerView
        recyclerView.setHasFixedSize(true);                                                         // ''
        recyclerView.setLayoutManager(new LinearLayoutManager(this));                       // Verticale layout RecyclerView
        peopleList = new ArrayList<Person>();                                                       // Initiëren van Source lijst van personen voor vullen van RecyclerView
        String[] urls = {"https://randomuser.me/api?results=15"};                                   // Aangeven van API source URL met als doel 15 personen op te halen
        try {
            aSyncTask.execute(urls);                                                                // Starten van asynchrone taak (het ophalen van de API data en deze om te zetten naar instanties van Person, en toe te voegen aan geïnstantieerde personenlijst in deze class)
        } catch (Exception e) {
            Log.i("Error", "Kan ApiAsyncTask.execute() niet uitvoeren vanuit MainActivity.");
        }
        adapter = new PersonAdapter(MainActivity.this, peopleList);                           // Initiëren van de vuller van de RecyclerView instantie
        recyclerView.setAdapter(adapter);                                                           // Stel de RecyclerView instantie op de vuller
        adapter.setOnItemClickListener(MainActivity.this);                                          // Stel de vuller instantie in op runnende MainActivity class
    }

    @Override
    public void onItemClick(int position) {                                                         // OnClick logica voor het aanklikken van een persoon in de RecyclerView UI
        Intent detailIntent = new Intent(this, DetailActivity.class);                 // Aanmaken van Intent die de wissel naar DetailActivity kan regelen
        Person clickedPerson = peopleList.get(position);                                            // Aanduiden van de specifieke aangeklikte persoon
        detailIntent.putExtra(EXTRA_URL, clickedPerson.getImageUrlLarge());                         // De 'titels' van categoriën en daarbij horende gegevens van de aangeklikte persoon worden meegenomen
        detailIntent.putExtra(EXTRA_NAME, clickedPerson.getName());                                 // ''
        detailIntent.putExtra(EXTRA_GENDER, clickedPerson.getGender());                             // ''
        detailIntent.putExtra(EXTRA_NATIONALITY, clickedPerson.getNationality());                   // ''
        detailIntent.putExtra(EXTRA_EMAIL, clickedPerson.getEmail());                               // ''
        detailIntent.putExtra(EXTRA_PHONE, clickedPerson.getPhone());                               // ''
        detailIntent.putExtra(EXTRA_CELL, clickedPerson.getCell());                                 // ''
        startActivity(detailIntent);                                                                // Het starten van de Intent en dus het initiëren van DetailActivity
    }

    @Override
    public void onTaskCompleted(Person newlyAddedPerson) {                                          // Deze methode wordt vanuit ApiAsyncTask klasse aangeroepen in methode onPostExecute()in asynchrone taak instantie;
        peopleList.add(newlyAddedPerson);                                                           // Toevoegen van gevonden persoon uit de API aan geinstantiëerde personenlijst
        adapter.notifyDataSetChanged();                                                             // Seintje(of refresh) naar de PersonsAdapter instantie (regelaar van de RecyclerView dingen en ViewHolder)
    }
}




