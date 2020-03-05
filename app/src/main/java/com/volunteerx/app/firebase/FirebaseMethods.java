/*
 * *
 *  * Created by Amil Muhammed Hamza on 2/19/20 10:23 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 2/19/20 10:23 PM
 *
 */

package com.volunteerx.app.firebase;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.volunteerx.app.R;
import com.volunteerx.app.models.User;
import com.volunteerx.app.post.model.Post;
import com.volunteerx.app.startup.model.SigningUser;
import com.volunteerx.app.startup.wizard.UserNameWizardFragment;
import com.volunteerx.app.utils.CheckerClass;
import com.volunteerx.app.utils.SharedPrefManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import static com.volunteerx.app.utils.FragmentLoadFunction.replaceFragment;

public class FirebaseMethods {

    private static final String TAG = "FirebaseMethods";
    private FirebaseAuth auth;
    private FirebaseFirestore rootRef;
    private FirebaseStorage storage;
    private DocumentReference documentReference;
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private MutableLiveData<Task<AuthResult>> resultTask = new MutableLiveData<>();

    private User user;
    private String userID;
    private String photoUrl;

    public FirebaseMethods() {
        auth = FirebaseAuth.getInstance();
        rootRef = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        if (auth.getCurrentUser() != null) {
            userID = auth.getCurrentUser().getUid();
        }
    }

//    auth.createUserWithEmailAndPassword(userData, password).addOnCompleteListener(getActivity() ,task -> {
//        if (task.isSuccessful()) {
//            Log.d(TAG, "createUserWithEmail:success");
//            User user = new User(null,
//                    null,
//                    "",
//                    "",
//                    userData,
//                    fullName,
//                    "",
//                    "",
//                    "",
//                    "",
//                    0,
//                    0,
//                    0
//            );
//            db.collection("users").document(auth.getCurrentUser().getUid()).set(user)
//                    .addOnSuccessListener(aVoid -> {
//                        builder.dismiss();
//                        Log.d(TAG, "createUserWithEmail: DocumentSnapshot added with ID:" );
//                        SharedPrefManager.getInstance(getContext()).wizardCompleted(false);
//                        replaceFragment(UserNameWizardFragment.newInstance(), "UserNameWizardFragment", getParentFragment().getFragmentManager());
//
//                    }).addOnFailureListener(e -> {
//                builder.dismiss();
//                Log.w(TAG, "createUserWithEmail: error adding document", e);
//            });
//        }else {
//            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//
//            builder.dismiss();
//
//            Toast.makeText(getContext(), "Authentication failed. " + task.getException(),
//                    Toast.LENGTH_SHORT).show(); //handle exceptions
//
//        }
//    }

    public MutableLiveData<Task<AuthResult>> createNewUser(MutableLiveData<SigningUser> signingUserMutableLiveData) {

            return new MutableLiveData<>(
                    auth.createUserWithEmailAndPassword(
                            signingUserMutableLiveData.getValue().getUserData(),
                            signingUserMutableLiveData.getValue().getPassword()
                    ).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Log.d(TAG, "onSuccess: displayName" + authResult.getUser().getEmail());
                        }
                    })
            );

    }

    public MutableLiveData<User> getCurrentUserData() {

        Log.d(TAG, "getCurrentUserData: here we are");

        documentReference = rootRef.collection("users").document(auth.getCurrentUser().getUid());

        documentReference.addSnapshotListener((documentSnapshot, e) -> {
            if (e != null) {
                Log.w(TAG, "getCurrentUserData: ", e);
//                user = null;
            }else {
                if (documentSnapshot.exists()) {
                    userLiveData.postValue(documentSnapshot.toObject(User.class));
//                    Log.d(TAG, "getCurrentUserData: username@" + user.getValue().getUsername());
                }else {
                    Log.d(TAG, "No such document");
//                    user = null;
                }
            }
        });

        Log.d(TAG, "getCurrentUserData: returning");

        return userLiveData;
    }

    public String addProfilePicture(Uri filePath, final String photoUrl){
        StorageReference storageRef = storage.getReference("/user/" + userID + "/profile/" + UUID.randomUUID().toString() + ".jpg");
        StorageReference oldRef = storage.getReferenceFromUrl(photoUrl);

        this.photoUrl = photoUrl;

        Log.d(TAG, "addProfilePicture: here we reach  " + filePath.getPath());

        storageRef.putFile(filePath)
                .addOnSuccessListener(taskSnapshot -> {

                    //delete old profile picture
                    oldRef.delete()
                            .addOnSuccessListener(aVoid -> Log.d(TAG, "addProfilePicture: old profile picture deleted"))
                            .addOnFailureListener(e -> Log.e(TAG, "addProfilePicture: onFailure: ", e));

                    //getting new Profile picture uri
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        Log.d(TAG, "onSuccess: successfully uploaded: ");
                        this.photoUrl = uri.toString();

                        //storing new profile picture on firestore db as downloadable url
                        DocumentReference documentReference = rootRef.collection("users").document(userID);
                        documentReference.update("photoUrl", uri.toString()).addOnSuccessListener(aVoid -> Log.d(TAG, "addProfilePicture: successfully added to database"));
                    });
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "onFailure: failed as shit", e);
                });

        return this.photoUrl;

    }


    public void createNewPost(MutableLiveData<Post> postModel) {

    }
}
