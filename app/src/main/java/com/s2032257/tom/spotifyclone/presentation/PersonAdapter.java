package com.s2032257.tom.spotifyclone.presentation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.s2032257.tom.spotifyclone.domain.Person;
import com.s2032257.tom.spotifyclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tom on 11-3-2018.
 * Regelt het hele gebeuren omtrent het regelen van de RecyclerView adapter en ViewHolder
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context mCtx;                                                                           // Context regelaar
    private ArrayList<Person> personList;                                                           // Aanmaken van een personenlijst die in de RecyclerView geladen moet worden
    private OnItemClickListener listener;                                                           // Aanmaken van de OnItemClick listener

    public void setOnItemClickListener(OnItemClickListener listener) {                               // Setter
        this.listener = listener;
    }

    public PersonAdapter(Context mCtx, ArrayList<Person> personList) {                              // Constructor
        this.mCtx = mCtx;
        this.personList = personList;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {                    // Methode die automatisch start na het aanmaken van een ViewHolder object. Tijdelijke 'buffer' voor geladen gegevens van Personen
        View v = LayoutInflater.from(mCtx).inflate(R.layout.example_item, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {                           // Methode voor het setten van de twee tekstvelden (name & email) en het maken/setten van de bijbehorende (large) foto in de UI.
        Person person = personList.get(position);
        holder.name.setText(person.getName());
        holder.email.setText(person.getEmail());
        String imageUrl = person.getImageUrlLarge();
        Picasso.with(mCtx).load(imageUrl).fit().centerInside().into(holder.imageView);              // Picasso verzorgt in zijn geheel het ophalen van de image en toepassen op de layout.
    }

    @Override
    public int getItemCount() {                                                                     // Returnt de grootte van de lijst.
        return personList.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {                                  // Interne extended class van de ViewHolder: Wordt na aanmaken opgevolgd door het uitvoeren van onCreateViewHolder() hierboven.

        public ImageView imageView;                                                                 // Aanmaken van ImageView object (van een item uit de row van de RecyclerView)
        public TextView name;                                                                       // Aanmaken van TextView object (''
        public TextView email;                                                                      // ''

        public PersonViewHolder(View itemView) {                                                    // Koppelt de .xml elementen aan de java objecten.
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_imageLarge);                          // Instantiëren van bovengenoemde objecten met bijbehorende xml elementen als parameters
            name = itemView.findViewById(R.id.text_view_personName);
            email = itemView.findViewById(R.id.text_view_personEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
