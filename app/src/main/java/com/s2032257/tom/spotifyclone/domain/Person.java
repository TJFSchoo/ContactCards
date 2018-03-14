package com.s2032257.tom.spotifyclone.domain;

/**
 * Created by Tom on 10-3-2018.
 * Verantwoordelijk voor het aanmaken van SpotifyItems en het getten() en setten() van bijbehorende gegevens.
 */

public class Person {

    private String gender;
    private String title;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrlThumb;
    private String imageUrlMedium;
    private String imageUrlLarge;
    private String phone;
    private String cell;
    private String nationality;

    public Person(String gender, String title, String firstName, String lastName, String email, String imageUrlThumb, String imageUrlMedium, String imageUrlLarge, String phone, String cell, String nationality) {
        this.gender = gender;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.imageUrlThumb = imageUrlThumb;
        this.imageUrlMedium = imageUrlMedium;
        this.imageUrlLarge = imageUrlLarge;
        this.phone = phone;
        this.cell = cell;
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return this.gender + " " + this.firstName + " " + this.lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrlThumb() {
        return imageUrlThumb;
    }

    public String getImageUrlMedium() {
        return imageUrlMedium;
    }

    public String getImageUrlLarge() {
        return imageUrlLarge;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrlThumb(String imageUrlThumb) {
        this.imageUrlThumb = imageUrlThumb;
    }

    public void setImageUrlMedium(String imageUrlMedium) {
        this.imageUrlMedium = imageUrlMedium;
    }

    public void setImageUrlLarge(String imageUrlLarge) {
        this.imageUrlLarge = imageUrlLarge;
    }
}
