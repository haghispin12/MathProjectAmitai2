package com.example.mathprojectamitai2;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

public class User_pro {

    private String UID;

    private int score;

    private DocumentReference documentId;

    public User_pro(String UID, int score, DocumentReference documentId) {
        this.UID = UID;
        this.score = score;
        this.documentId = documentId;
    }

    public User_pro(String UID) {
        this.UID = UID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public DocumentReference getDocumentId() {
        return documentId;
    }

    public void setDocumentId(DocumentReference documentId) {
        this.documentId = documentId;
    }
}
