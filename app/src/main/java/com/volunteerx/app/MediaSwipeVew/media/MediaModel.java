package com.volunteerx.app.MediaSwipeVew.media;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaModel implements Parcelable {

    private int id;
    private int position;

    public MediaModel() {
    }

    public MediaModel(Parcel parcel) {
        id = parcel.readInt();
        position = parcel.readInt();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeInt(position);

    }

    public static final Creator<MediaModel> CREATOR = new Creator<MediaModel>() {

        @Override
        public MediaModel createFromParcel(Parcel parcel) {
            return new MediaModel(parcel);
        }

        @Override
        public MediaModel[] newArray(int size) {
            return new MediaModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
