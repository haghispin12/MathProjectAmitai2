package com.example.mathprojectamitai2;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class User_pro {

    private String UID;

    private int score;
    //private User_pro myUser;

    private DocumentReference documentId;

    public User_pro(String UID, int score, DocumentReference documentId) {
        this.UID = UID;
        this.score = score;
        this.documentId = documentId;
    }



    public User_pro() {
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

    static User_pro user;

    /**
     *  בודק אם המשתמש קיים בפיירסטור ואם הוא לא קיים הוא מוסיף אותו
     */
    public void checkIfUserExist() {

        user=this;
        FirebaseFirestore.getInstance().collection("users").whereEqualTo("uid", this.getUID()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty()) {
                    FirebaseFirestore.getInstance().collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            int n=0;
                            documentId = documentReference;
                        }
                    });

                }else
                /**
                 *   אם המשתמש מקודם כן קיים, הוא מעדכן את הdocument שלו בפיירסטור
                 */
                    for(DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        if (documentSnapshot.exists()) {
                            documentId = documentSnapshot.getReference();
                        }
                    }

            }
        });
    }











}
