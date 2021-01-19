package com.example.android.justjava;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0,wc=0,ct=0;
    EditText nameOfCustomer;
    boolean onCheck=false;
    String extra1="",extra2="";
    boolean f=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    private String displayName(){
        nameOfCustomer =(EditText) findViewById(R.id.name_description);
        return nameOfCustomer.getText().toString();
    }
    @SuppressLint({"QueryPermissionsNeeded", "StringFormatMatches"})
    public void submitOrder(View view) {
        String name = displayName();
        Intent intent_email=new Intent(Intent.ACTION_SENDTO);
        intent_email.setData(Uri.parse("mailto:"));
        intent_email.putExtra(Intent.EXTRA_SUBJECT,R.string.order);
        intent_email.putExtra(Intent.EXTRA_TEXT,getString(R.string.order)+"\n"+getString(R.string.quickSummary,name,quantity,extra1,extra2,quantity*(10+2*wc+ct)));
        if(intent_email.resolveActivity(getPackageManager())!=null){
            startActivity(intent_email);
        }
    }
    public void increment(View view){
        if(quantity<100)quantity = quantity +1;
        display(quantity);
    }
    public void decrement(View view){
        if(quantity>0) quantity = quantity -1;
        display(quantity);
    }
    @SuppressLint("NonConstantResourceId")
    public void checkBox(View view){
        onCheck =((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox1:
                if(onCheck){
                    extra1 =getString(R.string.Extras1);
                    wc=1;
                    f=true;
                }
                break;
            case R.id.checkbox2:
                if(onCheck&&f){extra2 = "+"+getString(R.string.Extras2);ct=1;}
                else if(onCheck){extra2 = getString(R.string.Extras2);ct=1;}
                break;
        }
    }
    public void reset(View view){
        quantity =0;
        onCheck=false;
        f=false;
        extra1="";
        extra2="";
        ct=0;wc=0;
        setContentView(R.layout.activity_main);

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    @SuppressLint("SetTextI18n")
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}