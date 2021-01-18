package com.example.android.justjava;


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
        String name = nameOfCustomer.getText().toString();
        return name;
    }
    public void submitOrder(View view) {
        String oSum ="YOUR ORDER SUMMARY\n\nName: "+displayName()+"\nYour Order\nQuantity: "+quantity+"\nExtras: "+extra1+extra2+"\nTotal Bill: "+quantity*(10+wc+2*ct)+"\nThank you! Come again";
        Intent intent_email=new Intent(Intent.ACTION_SENDTO);
        intent_email.setData(Uri.parse("mailto:"));
        intent_email.putExtra(Intent.EXTRA_SUBJECT, "Order Summary");
        intent_email.putExtra(Intent.EXTRA_TEXT,oSum);
//        Intent chooser = Intent.createChooser(intent_email,"Send order summary with:");
        if(intent_email.resolveActivity(getPackageManager())!=null){
//            startActivity(chooser);
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
    public void checkBox(View view){
        onCheck =((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.checkbox1:
                if(onCheck){
                    extra1 = "Whipped cream";
                    wc=1;
                    f=true;
                }
                break;
            case R.id.checkbox2:
                if(onCheck&&f){extra2 = "+ Chocolate";ct=1;}
                else if(onCheck){extra2 = "Chocolate";ct=1;}
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
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}