package com.s2032257.tom.spotifyclone.data_access__application_logic;

import com.s2032257.tom.spotifyclone.domain.Person;

/**
 * Created by Tom on 11-3-2018.
 * Zorgt in ApiAsyncTask dat de MainActivity class een gevonden persoon toevoegt aan de lijst van personen die daar gedefiniëerd is.
 * Gekozen om hier een interface voor te gebruiken ivm toekomstig gebruik (program to an interface) en eventuele verschillende implementaties van methode.
 */

public interface OnTaskCompleted {
    void onTaskCompleted(Person person);
}
