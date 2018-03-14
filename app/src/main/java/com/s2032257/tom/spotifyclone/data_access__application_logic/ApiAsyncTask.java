package com.s2032257.tom.spotifyclone.data_access__application_logic;

import android.os.AsyncTask;
import android.util.Log;

import com.s2032257.tom.spotifyclone.domain.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Tom on 11-3-2018.
 * Regelt het ophalen van API gegevens via meegegeven URL in de constructor in MainActivity.
 * Ook maakt de onPostExecute tijdens het loopen personen aan die worden meegegeven aan de MainActivity class via de OnTaskCompleted methode.
 */

public class ApiAsyncTask extends AsyncTask<String, Void, String> {
    private OnTaskCompleted listener = null;                                                        // Aanmaken van listener: Zorgt ervoor dat personen die gevonden worden tijdens het loopen tijdens het opvragen van data van de API source doorgegeven worden aan MainActivity.

    public ApiAsyncTask(OnTaskCompleted listener) {                                                 // Constructor
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String[] urls) {                                                // Hoofdfunctionaliteit van deze class: Op aparte thread de target API URL benaderen en deze data returnen als 1 lange String
        String result = "";                                                                         // Aanmaken van String die gebruikt wordt om de gevonden API data als volledige lange regel door te geven aan onPostExecute()
        URL url;                                                                                    // Aanmaken van URL object
        HttpURLConnection urlConnection = null;                                                     // Aanmaken van HttpURLConnection object

        try {
            url = new URL(urls[0]);                                                                 // Array van meegegeven urls (in geval van MainActivity dus 1) wordt opgevraagd en omgezet naar 1 losse URL
            urlConnection = (HttpURLConnection) url.openConnection();                               // Verbinding maken met HttpUrlConnection
            InputStream in = urlConnection.getInputStream();                                        // Aanmaken en instantiëren van InputStream voor verbonden HttpUrlConnection
            InputStreamReader reader = new InputStreamReader(in);                                   // Aanmaken en instantiëren van InputStreamReader voor bovenstaande InputStream
            int data = reader.read();

            while (data != -1) {                                                                    // Loop die doorgaat totdat data -1 is, wat gebeurt als InputStreamReader door alle gevonden data heen is
                char current = (char) data;                                                         // Loopt door data heen en voegt elke character toe aan de String
                result += current;
                data = reader.read();
            }
            return result;                                                                          // Returnen van String met complete hoop van API data

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed.";
        }
    }

    @Override
    protected void onPostExecute(String result) {                                                   // Wordt automatisch uitgevoerd nadat doInBackground() klaar is.
        super.onPostExecute(result);                            // Het aanmaken van personenlijst waarin uit de API gevonden persoonsgegevens staan

        try {
            JSONObject jsonObject = new JSONObject(result);                                         // Omzetten van de lange String uit de asynchrone taak naar JSONObject
            JSONArray jsonArray = jsonObject.getJSONArray("results");                         // Omzetten van JSONObject naar een JSONArray

            for (int i = 0; i < jsonArray.length(); i++) {                                          // Starten van een loop die even lang doorgaat als het aantal gevonden personen uit de API data
                JSONObject person = jsonArray.getJSONObject(i);                                     // Op de positie van de loop worden de gegevens van de aangewezen persoon opgehaald en in een JSONObject gestopt

                String gender = person.getString("gender");                                   // Maken van losse Strings die opgehaald worden uit dit JSONObject
                String email = person.getString("email");
                String phone = person.getString("phone");
                String cell = person.getString("cell");
                String nationality = person.getString("nat");

                JSONObject jName = person.getJSONObject("name");                                    // Nieuwe JSONObject om een map dieper te gaan in de data
                String title = jName.getString("title");
                String firstName = jName.getString("first");
                String lastName = jName.getString("last");

                JSONObject jImage = person.getJSONObject("picture");                                // Nieuwe JSONObject om een map dieper te gaan in de data
                String imageUrlThumb = jImage.getString("thumbnail");
                String imageUrlMedium = jImage.getString("medium");
                String imageUrlLarge = jImage.getString("large");

                Person personToAdd = new Person(gender, title, firstName, lastName, email, imageUrlThumb, imageUrlMedium, imageUrlLarge, phone, cell, nationality);       // Aanmaken van instantie van Person met bovengenoemde Strings als parameters
                listener.onTaskCompleted(personToAdd);                                              // Geeft Person terug aan MainActivity om daar toe te voegen aan de ArrayList van personen.
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("Error", "Fout bij het aanmaken van JSONObject in class ApiAsyncTask, methode onPostExecute().");
        }


    }
}
