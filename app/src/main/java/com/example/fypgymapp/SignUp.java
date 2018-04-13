package com.example.fypgymapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private Switch adminSwitch;
    private boolean isAdmin;
    private Button submitInfo;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private final String TAG = "Ryan2";
    public String emailInfo;
    public String passwordInfo;
    public String confirmPassword;
    public String firstName, lastName;
    public RegularUser regularUser;
    public TextView invalidFormat, differentPassword, emailAlreadyUsed;
    public EditText myemail, mypassword, myconfirmpassword;
    public EditText myfirstname, mylastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //////////// Make initial Textviews invisible
        invalidFormat = (TextView) findViewById(R.id.invalidFormat);
        differentPassword = (TextView) findViewById(R.id.differentPassword);
        emailAlreadyUsed = (TextView) findViewById(R.id.emailAlreadyUsed);
        invalidFormat.setVisibility(View.INVISIBLE);
        differentPassword.setVisibility(View.INVISIBLE);
        emailAlreadyUsed.setVisibility(View.INVISIBLE);


        ////////////////////// Switch for isAdmin  ////////////////////
        isAdmin = false;
        adminSwitch = (Switch) findViewById(R.id.AdminSwitch);
        adminSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton cb, boolean on){
                if(on)
                {
                    //Do something when Switch button is on/checked
                    isAdmin=true;
                }
                else
                {
                    //Do something when Switch is off/unchecked
                    isAdmin=false;
                }
            }
        });
        ////////////////////// End of Switch for isAdmin  /////////////

        mAuth = FirebaseAuth.getInstance();




        submitInfo = (Button) findViewById(R.id.SubmitInfo);
        submitInfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                ////// First Name Info ///////
                myfirstname = (EditText) findViewById(R.id.firstNameInfo);
                firstName = myfirstname.getText().toString();
                    if(TextUtils.isEmpty(firstName)) {
                        myfirstname.setError("Insert your first name");
                        return;
                    }


                /////// Last Name Info ///////
                mylastname = (EditText) findViewById(R.id.lastNameInfo);
                lastName = mylastname.getText().toString();
                if(TextUtils.isEmpty(lastName)) {
                    mylastname.setError("Insert your last name");
                    return;
                }


                /////// Email Info ///////
                myemail = (EditText) findViewById(R.id.emailInfo);
                emailInfo = myemail.getText().toString();
                if(TextUtils.isEmpty(emailInfo)) {
                    myemail.setError("Insert your email");
                    return;
                }


                /////// Password Info /////
                mypassword = (EditText) findViewById(R.id.passwordInfo);
                passwordInfo = mypassword.getText().toString();
                if (TextUtils.isEmpty(passwordInfo)) {
                    mypassword.setError("Insert your password");
                    return;
                }



                /////// ConfirmPassword Info /////
                myconfirmpassword = (EditText) findViewById(R.id.confirmPassword);
                confirmPassword = myconfirmpassword.getText().toString();
                if (TextUtils.isEmpty(confirmPassword)) {
                    myconfirmpassword.setError("Confirm your password");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(emailInfo, passwordInfo)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.d(TAG, "onComplete: Failed=" + task.getException().getMessage()); //ADD THIS
                                    Toast.makeText(SignUp.this, "Sign Up failed",
                                            Toast.LENGTH_SHORT).show();
                                }

                                if (!isAdmin)
                                {
                                    ///////        add the user to the database   ////////
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user!= null) {
                                        regularUser = new RegularUser(user.getUid(), firstName,
                                                lastName);
                                        DatabaseReference databaseReference = FirebaseDatabase
                                                .getInstance()
                                                .getReference("regularUsers");
                                        databaseReference.push().
                                                setValue(regularUser);
                                        Toast.makeText(SignUp.this, "User Saved",
                                                Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(SignUp.this, WelcomePage.class);
                                        startActivity(i);
                                    }
                                }
                                else {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user!= null) {
                                        Intent i = new Intent(SignUp.this, AdminSignUp.class);
                                        i.putExtra("ID",user.getUid());
                                        i.putExtra("FirstName", firstName);
                                        i.putExtra("LastName", lastName);
                                        startActivity(i);
                                    }
                                    else {
                                        Log.d(TAG, "Admin User is Null");
                                    }

                                }

                            }
                        });
                ////////////////  End of mAuth.  //////////////





            }
        });
        //////////////// End of button listener  ////////////////////


    }
    ///////////////////////////////// END OF ONCREATE //////////////////




}

