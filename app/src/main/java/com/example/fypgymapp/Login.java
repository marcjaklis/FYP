package com.example.fypgymapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button submit;
    private String email;
    private String password;
    private EditText emailText;
    private EditText passwordText;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "Ryan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        submit = (Button) findViewById(R.id.submit);


        /////////////////////   Button Listener //////////////////
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                /////// Email Info ///////
                emailText = (EditText) findViewById(R.id.emailText);
                email = emailText.getText().toString();
                if(TextUtils.isEmpty(email)) {
                    emailText.setError("Insert your email");
                    return;
                }
                /////// End of Email Info //////

                /////// Password Info ///////
                passwordText = (EditText) findViewById(R.id.centerDescription);
                password = passwordText.getText().toString();
                if(TextUtils.isEmpty(password)) {
                    passwordText.setError("Insert your password");
                    return;
                }
                /////// End of Password Info //////


                /////////  Sign In /////////
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                                    Toast.makeText(Login.this, "Can't login",
                                            Toast.LENGTH_SHORT).show();
                                }

                                ///////////////////// Check if Admin or Regular User ////////////
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                if (user!=null)
                                {
                                    String userID = user.getUid();

                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                    Log.d(TAG, "UID is:" + userID);
                                    Query query = databaseReference.child("regularUsers").orderByChild("uID").equalTo(userID);


                                    ValueEventListener valueEventListener = new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.getValue()!=null)
                                            //TEST
                                            {
                                                Log.d(TAG, "Is a regular user");
                                                Intent i = new Intent(Login.this, Featured.class);
                                                startActivity(i);
                                            }
                                            else
                                            {
                                                Log.d(TAG, "Is not a regular user");
                                                Intent i = new Intent(Login.this, AdminFirstPage.class);
                                                startActivity(i);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            Log.d(TAG,"Database Error for Regular User Check");
                                        }
                                    };


                                    query.addValueEventListener(valueEventListener);
                                }



                            }
                        });
                /////////////////////////////



            }
        });
        /////////////////////   End of Button Listener ///////////


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
