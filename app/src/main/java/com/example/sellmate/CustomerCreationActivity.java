package com.example.sellmate;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerCreationActivity extends AppCompatActivity {

    private EditText customerIDEditText;
    private EditText customerNameEditText;
    private EditText contactNumberEditText;
    private EditText addressEditText;
    private EditText contactPersonNameEditText;
    private Button createCustomerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_creation);

        customerIDEditText = findViewById(R.id.customerID);
        customerNameEditText = findViewById(R.id.customerName);
        contactNumberEditText = findViewById(R.id.contactNumber);
        addressEditText = findViewById(R.id.address);
        contactPersonNameEditText = findViewById(R.id.contactPersonName);
        createCustomerButton = findViewById(R.id.createCustomerButton);

        createCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    // Proceed with customer creation, e.g., save to database
                    Toast.makeText(CustomerCreationActivity.this, "Customer created successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInputs() {
        String customerID = customerIDEditText.getText().toString().trim();
        String customerName = customerNameEditText.getText().toString().trim();
        String contactNumber = contactNumberEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String contactPersonName = contactPersonNameEditText.getText().toString().trim();

        if (TextUtils.isEmpty(customerID)) {
            customerIDEditText.setError("Customer ID is required");
            return false;
        }

        if (TextUtils.isEmpty(customerName)) {
            customerNameEditText.setError("Customer Name is required");
            return false;
        }

        if (TextUtils.isEmpty(contactNumber)) {
            contactNumberEditText.setError("Contact Number is required");
            return false;
        }

        if (TextUtils.isEmpty(contactPersonName)) {
            contactPersonNameEditText.setError("Contact Person Name is required");
            return false;
        }

        return true;
    }
}
