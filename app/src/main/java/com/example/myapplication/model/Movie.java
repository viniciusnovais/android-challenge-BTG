package com.example.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private int id;

    private String poster;
    private String name;
    private String year;
    private String title;
    private String synopsis;
    private float note;
    private boolean favorite;
    private int[] genres;

    private String genresStr;

    public Movie() {
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        poster = in.readString();
        name = in.readString();
        year = in.readString();
        title = in.readString();
        synopsis = in.readString();
        note = in.readFloat();
        favorite = in.readByte() != 0;
        genres = in.createIntArray();
        genresStr = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int[] getGenres() {
        return genres;
    }

    public void setGenres(int[] genres) {
        this.genres = genres;
    }

    public String getGenresStr() {
        return genresStr;
    }

    public void setGenresStr(String genresStr) {
        this.genresStr = genresStr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(poster);
        parcel.writeString(name);
        parcel.writeString(year);
        parcel.writeString(title);
        parcel.writeString(synopsis);
        parcel.writeFloat(note);
        parcel.writeByte((byte) (favorite ? 1 : 0));
        parcel.writeIntArray(genres);
        parcel.writeString(genresStr);
    }
}
