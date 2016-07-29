package com.xdx.dllo.beautygoodsdemo.pictorial;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Target;

/**
 * Created by dllo on 16/7/29.
 */
public class Datum implements Parcelable {
    private int id;
    private String title, image_url;
    public Target target;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Datum() {

    }

    protected Datum(Parcel in) {
        readFromParcel(in);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image_url = in.readString();
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(image_url);
    }

    @Override
    public boolean equals(Object obj) {
        return ((Datum) obj).id == this.id;
    }
}
