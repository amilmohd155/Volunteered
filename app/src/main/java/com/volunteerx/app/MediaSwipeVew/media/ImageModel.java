package com.volunteerx.app.MediaSwipeVew.media;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

public class ImageModel extends MediaModel implements Parcelable {

    private String url;
    private Drawable errorDrawable;

    public ImageModel(String url) {
        this.url = url;
    }

    public ImageModel(String url, Drawable errorDrawable) {
        this.url = url;
        this.errorDrawable = errorDrawable;
    }

    protected ImageModel(Parcel parcel) {
        url = parcel.readString();
        errorDrawable = (Drawable) parcel.readValue(Drawable.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);

        if (errorDrawable != null) {
            parcel.writeParcelable(((BitmapDrawable) errorDrawable).getBitmap(), i);
        }

    }

    @SuppressWarnings("unused")
    public static final Creator<ImageModel> CREATOR = new Creator<ImageModel>() {
        @Override
        public ImageModel createFromParcel(Parcel in) {
            return new ImageModel(in);
        }

        @Override
        public ImageModel[] newArray(int size) {
            return new ImageModel[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public void setErrorDrawable(Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
    }
}
