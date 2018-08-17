package com.app.eventos.dao;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ConfiguracaoFirebaseAuth {
    private static FirebaseAuth firebaseAuth;
    private static FirebaseUser firebaseUser;

    public static FirebaseAuth getFirebaseAuth() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance();
        }

        return firebaseAuth;
    }
}
