package com.volunteerx.app.MediaSwipeVew.media;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class VideoModel extends MediaModel implements Parcelable {

    private Uri uri;

    public VideoModel(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public VideoModel(Parcel parcel) {
        uri = parcel.readParcelable(Uri.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int size) {
        parcel.writeParcelable(uri, size);
    }

    @SuppressWarnings("unused")
    public static final Creator<VideoModel> CREATOR = new Creator<VideoModel>() {
        @Override
        public VideoModel createFromParcel(Parcel parcel) {
            return new VideoModel(parcel);
        }

        @Override
        public VideoModel[] newArray(int size) {
            return new VideoModel[size];
        }
    };

}
