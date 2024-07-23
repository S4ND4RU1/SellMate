package com.example.sellmate;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SalesmenCreationActivity extends AppCompatActivity {

    private EditText salesmenIDEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText contactNumberEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button uploadImageButton;
    private Button createSalesmenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesmen_creation);

        salesmenIDEditText = findViewById(R.id.salesmenID);
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        contactNumberEditText = findViewById(R.id.contactNumber);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        createSalesmenButton = findViewById(R.id.createSalesmenButton);

        createSalesmenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // Proceed with salesmen creation, e.g., save to database
                    Toast.makeText(SalesmenCreationActivity.this, "Salesmen created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement image upload functionality here
                Toast.makeText(SalesmenCreationActivity.this, "Image upload functionality to be implemented", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs() {
        String salesmenID = salesmenIDEditText.getText().toString().trim();
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String contactNumber = contactNumberEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (TextUtils.isEmpty(salesmenID)) {
            salesmenIDEditText.setError("Salesmen ID is required");
            return false;
        }

        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError("First Name is required");
            return false;
        }

        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError("Last Name is required");
            return false;
        }

        if (TextUtils.isEmpty(contactNumber)) {
            contactNumberEditText.setError("Contact Number is required");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }

        return true;
    }
}
