package android.example.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/



import java.text.NumberFormat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox = (CheckBox)findViewById(R.id.whipped_cream_checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();

                }
            }
        });


    }


    int quantity =0;

    public void decrement(View view) {

        if ( quantity == 1) {
            Toast toast = Toast.makeText(this, " You can't have less !", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity -1;
        displayQuantity(quantity);
    }


    public void increment(View view) {

        if (quantity ==100 ) {
            // show an error message sa a toast
            Toast toast = Toast.makeText(this, " You can't have more orders !", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity = quantity +1;
        displayQuantity(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    @SuppressLint("StringFormatInvalid")
    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {

        String priceMessage ="";
        priceMessage += getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, addWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.Quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price, price);
        priceMessage +=  "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    public void submitOrder(View view) {
        EditText namefield = findViewById(R.id.nameInput);
        String name  = namefield.getText().toString();
        Log.v("MainActivity", "Name: " + name);


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped Cream : "+ hasWhippedCream);


        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocoalte = chocolateCheckBox.isChecked();
        Log.v("Main Activity", "Has Chocolate : " + hasChocoalte);


        int price = calculatePrice( hasWhippedCream, hasChocoalte);
        String priceMessage = (createOrderSummary(name, price, hasWhippedCream, hasChocoalte));
        displayMessage(priceMessage);


//

}
    /**
     * This method Calculates the price for the order
     * @param  addChocolate is whether or not the user wants chocolate.
     * @param  addWhippedCream addChocolate is whether or not the user wants whipped cream.
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int sum = 5;
        if (addChocolate) {
            sum  =sum + 2;
        }
        if (addWhippedCream) {
            sum  = sum + 1;
        }
        return quantity * sum ;

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView  orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
