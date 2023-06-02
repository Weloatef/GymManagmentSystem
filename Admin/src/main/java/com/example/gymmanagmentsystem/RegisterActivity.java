package com.example.gymmanagmentsystem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Pass;
    private EditText Username;
    private EditText PhoneNumber;
    private EditText Name;
    private ImageView uploadImage;
    private Button Register;
    private TextView FailedRTextView;
    private ProgressBar progressBar;
    private String imageURL;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressBar=findViewById(R.id.ProgressbarR);
        uploadImage = findViewById(R.id.ProfilePic);
        FailedRTextView=findViewById(R.id.FailedRTextView);
        Email=findViewById(R.id.EmailR);
        Username=findViewById(R.id.UsernameR);
        PhoneNumber=findViewById(R.id.PhoneNumberR);
        Name=findViewById(R.id.NameR);
        Pass=findViewById(R.id.PasswordR);
        Register=findViewById(R.id.RegisterButton);
        TextView Login = findViewById(R.id.LoginTextView);
        String LoginText="Already have an account? Login here.";
        SpannableString ss=new SpannableString(LoginText);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent LoginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
                ds.setUnderlineText(false);
            }
        };
        ss.setSpan(clickableSpan1,25,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Login.setText(ss);
        Login.setMovementMethod(LinkMovementMethod.getInstance());
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else {
                            Toast.makeText(RegisterActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        Register.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            saveData();

        });


    }
    public void saveData(){
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Profile Pictures")
                .child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                uploadData();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                imageURL="https://firebasestorage.googleapis.com/v0/b/gym-managment-system-84aec.appspot.com/o/ProfilePic.png?alt=media&token=bbb7945f-a1ec-4323-8965-a84c5a856e65&_gl=1*xm233s*_ga*OTEwOTc1NjguMTY4NTMxODAxMw..*_ga_CW55HF8NVT*MTY4NTY3NjE0NS4xNS4xLjE2ODU2Nzg4NzUuMC4wLjA.";
                uploadData();
            }
        });
    }
    public void uploadData(){
        String name = Name.getText().toString();
        String username = Username.getText().toString();
        String email = Email.getText().toString();
        String phoneNumber = PhoneNumber.getText().toString();
        String password = Pass.getText().toString();
        HelperClass dataClass = new HelperClass(imageURL,name,username,email,phoneNumber,password);
        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        FirebaseDatabase.getInstance().getReference().child("Admins").child(username)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this,AdminManageActivity.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "Failed To Register", Toast.LENGTH_SHORT).show();
                        FailedRTextView.setTextColor(Color.RED);
                        FailedRTextView.setText("Please make sure that:\n-There is no empty input");
                    }
                });
    }
}
