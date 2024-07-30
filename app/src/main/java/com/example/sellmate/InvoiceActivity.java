package com.example.sellmate;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class InvoiceActivity extends AppCompatActivity {

    private ListView invoiceListView;
    private FloatingActionButton fab;
    private ArrayList<String> invoices;
    private InvoiceAdapter invoiceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        invoiceListView = findViewById(R.id.invoiceListView);
        fab = findViewById(R.id.fab);

        invoices = new ArrayList<>();
        invoiceAdapter = new InvoiceAdapter(this, invoices);
        invoiceListView.setAdapter(invoiceAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateInvoiceDialog();
            }
        });

        loadInvoicesForToday();
    }

    private void loadInvoicesForToday() {
        // TODO: Load invoices from the database for the current day
        // This is a placeholder example
        invoices.add("Invoice 001");
        invoices.add("Invoice 002");
        invoiceAdapter.notifyDataSetChanged();
    }

    private void showCreateInvoiceDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_create_invoice, null);
        builder.setView(dialogView);

        AutoCompleteTextView customerAutoComplete = dialogView.findViewById(R.id.customerAutoComplete);
        EditText invoiceNumberEditText = dialogView.findViewById(R.id.invoiceNumberEditText);
        EditText dateEditText = dialogView.findViewById(R.id.dateEditText);
        LinearLayout itemsContainer = dialogView.findViewById(R.id.itemsContainer);
        EditText discountEditText = dialogView.findViewById(R.id.discountEditText);
        TextView subTotalTextView = dialogView.findViewById(R.id.subTotalTextView);
        TextView discountTextView = dialogView.findViewById(R.id.discountTextView);
        TextView totalTextView = dialogView.findViewById(R.id.totalTextView);
        Button addItemButton = dialogView.findViewById(R.id.addItemButton);
        Button createButton = dialogView.findViewById(R.id.createButton);

        AlertDialog alertDialog = builder.create();

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add a new item row
                View itemRow = inflater.inflate(R.layout.item_row, null);
                AutoCompleteTextView itemAutoComplete = itemRow.findViewById(R.id.itemAutoComplete);
                EditText quantityEditText = itemRow.findViewById(R.id.quantityEditText);
                EditText unitPriceEditText = itemRow.findViewById(R.id.unitPriceEditText);
                TextView totalPriceTextView = itemRow.findViewById(R.id.totalPriceTextView);

                // Add text watchers to calculate total price
                TextWatcher textWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        calculateTotalPrice(quantityEditText, unitPriceEditText, totalPriceTextView);
                    }
                };

                quantityEditText.addTextChangedListener(textWatcher);
                unitPriceEditText.addTextChangedListener(textWatcher);

                itemsContainer.addView(itemRow);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer = customerAutoComplete.getText().toString().trim();
                String invoiceNumber = invoiceNumberEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                String discount = discountEditText.getText().toString().trim();

                if (TextUtils.isEmpty(customer) || TextUtils.isEmpty(invoiceNumber) || TextUtils.isEmpty(date)) {
                    Toast.makeText(InvoiceActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate subtotal, discount, and total
                double subTotal = calculateSubTotal(itemsContainer);
                double discountAmount = TextUtils.isEmpty(discount) ? 0.00 : Double.parseDouble(discount);
                double total = subTotal - discountAmount;

                subTotalTextView.setText(String.format("%.2f", subTotal));
                discountTextView.setText(String.format("%.2f", discountAmount));
                totalTextView.setText(String.format("%.2f", total));

                // TODO: Create new invoice and save to database
                invoices.add("New Invoice for " + customer);
                invoiceAdapter.notifyDataSetChanged();
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    private void calculateTotalPrice(EditText quantityEditText, EditText unitPriceEditText, TextView totalPriceTextView) {
        String quantityStr = quantityEditText.getText().toString().trim();
        String unitPriceStr = unitPriceEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(quantityStr) && !TextUtils.isEmpty(unitPriceStr)) {
            double quantity = Double.parseDouble(quantityStr);
            double unitPrice = Double.parseDouble(unitPriceStr);
            double totalPrice = quantity * unitPrice;
            totalPriceTextView.setText(String.format("%.2f", totalPrice));
        } else {
            totalPriceTextView.setText("0.00");
        }
    }

    private double calculateSubTotal(LinearLayout itemsContainer) {
        double subTotal = 0.00;
        for (int i = 0; i < itemsContainer.getChildCount(); i++) {
            View itemRow = itemsContainer.getChildAt(i);
            TextView totalPriceTextView = itemRow.findViewById(R.id.totalPriceTextView);
            String totalPriceStr = totalPriceTextView.getText().toString().trim();
            if (!TextUtils.isEmpty(totalPriceStr)) {
                subTotal += Double.parseDouble(totalPriceStr);
            }
        }
        return subTotal;
    }
}
