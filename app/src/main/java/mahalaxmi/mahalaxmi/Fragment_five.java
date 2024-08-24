package mahalaxmi.mahalaxmi;



import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import java.text.DateFormat;
import java.text.DecimalFormat;

import static java.lang.Double.NaN;
//import java.util.Date;

/**
 * Created by SIV on 20-03-2018.
 */

public class Fragment_five extends Fragment {



    private static EditText billNo1, vehicleNo1, amount1, price1;
    private static TextView litres1;

    RadioGroup rg;

    String RbValue;
    String product = "(Diesel)";


    String vehicleNo11;
    long billNo11;
    double amount11, price11, litres11;

    // private MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bill, container, false);
        setHasOptionsMenu(true);


        rg = (RadioGroup) view.findViewById(R.id.radioGroup);
        billNo1 = (EditText) view.findViewById(R.id.billNo);
        vehicleNo1 = (EditText) view.findViewById(R.id.vehicleNo);
        amount1 = (EditText) view.findViewById(R.id.amount);
        price1 = (EditText) view.findViewById(R.id.price);

        amount1.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(6, 2)});
        price1.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(2, 2)});


        litres1 = (TextView) view.findViewById(R.id.litres);


        //final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        // For accessing default checked radio button value
        RbValue = ((RadioButton)(view.findViewById(rg.getCheckedRadioButtonId()))).getText().toString();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton)group.findViewById(checkedId);
                RbValue=radioButton.getText().toString();

                if (RbValue.equals("MS")) product = "(Petrol)";

                Toast.makeText(getContext(), RbValue + " is Selected", Toast.LENGTH_SHORT).show();
            }
        });


        TextWatcher textwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                handleTextChange(editable);
            }
        };

        amount1.addTextChangedListener(textwatcher);
        price1.addTextChangedListener(textwatcher);


        final Button sendMessage1 =
                (Button) view.findViewById(R.id.sendMessage);

        sendMessage1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                sendToSMS();

            }
        });


        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.clear_share, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.share:
                sendToWhatsapp();
                return true;

            case R.id.clear_one:
                clearFun5();
                return true;



            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }




    private void handleTextChange(Editable s){
        try {
            String s1 = amount1.getText().toString();
            String s2 = price1.getText().toString();

            amount11 = 0.0;
            if (!s1.isEmpty()) {
                amount11 = Double.parseDouble(s1);
            }

            price11 = 0.0;
            if (!s2.isEmpty()) {
                price11 = Double.parseDouble(s2);
            }
            if (amount11 != 0.0 && price11 != 0.0) litres11 = amount11 / price11; else litres11=0;

            if (litres11 != NaN) {
                litres1.setText(formatValue(litres11, "####.####"));
            }
            else litres1.setText("");

        }
        catch (Exception e){MainActivity.showToast(e.toString(),getContext());}
    }


    //For clearing all the fields
    private void clearFun5(){

        amount1.setText("");
        price1.setText("");
        billNo1.setText("");
        vehicleNo1.setText("");
        litres1.setText("");
        litres11 = 0.0;
        }


    private String formatValue(double num, String pattern){
        DecimalFormat formatter = new DecimalFormat(pattern);

        return formatter.format(num);

    }

    private void sendToWhatsapp(){

        String message = "";
        try {
            if (!litres1.getText().toString().isEmpty() && litres11 != 0.0 & litres11 != NaN) {


                String s1 = billNo1.getText().toString();
                String billString;
                if (!s1.isEmpty()) {
                    billNo11 = Long.parseLong(s1);
                    billString = String.valueOf(billNo11);
                } else billString = "";

                vehicleNo11 = vehicleNo1.getText().toString();


                // message = "*Mahalaxmi Petroleums*\n_Chintalapalem, Cumbum_ \n\n" + currentDateTimeString + "\n\n" + "*Current Stock Position* \n\n```HSD : " + formatValue(volHSD) + " Ltrs```\n```MS  : " + formatValue(volMS) + " Ltrs```";
                message = "Thannk you very much for the purchase of " + RbValue+ " " + product + " at our RO\n\nDetails of your purchase \n\n" +
                        "*Bill No:*" + " " + billString + "\n" +
                        "*Vehicle No:*" + " " + vehicleNo11 + "\n" +
                        "*Quantity:*" + " " + formatValue(litres11, "####.####") + " Ltrs\n" +
                        "*Amount:* Rs." + " " + formatValue(amount11, "##,##,###.##") + "\n\n" +
                        "Providing The Best Quality Fuel\n*Mahalaxmi Petroleums*\nChintalapalem, Cumbum";

                openWhatsApp(message);
            }

            else MainActivity.showToast("Sale value is zero, Enter Amt & Price values",getContext());


        } catch (Exception e) {
            MainActivity.showToast(e.toString(),getContext());

        }

    }

    // Method for sending a whatsapp message
    private void openWhatsApp(String message) {
        //String smsNumber = "7****"; //without '+'
        try {
            Intent sendIntent = new Intent("android.intent.action.MAIN");
            //sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            sendIntent.putExtra(Intent.EXTRA_TEXT, message);
            // sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch(Exception e) {
            MainActivity.showToast(e.toString(), getContext());
        }

    }

    private void sendToSMS(){
        String message = "";
        try {
            if (!litres1.getText().toString().isEmpty() && litres11 != 0.0 & litres11 != NaN) {


                String s1 = billNo1.getText().toString();
                String billString;
                if (!s1.isEmpty()) {
                    billNo11 = Long.parseLong(s1);
                    billString = String.valueOf(billNo11);
                } else billString = "";

                vehicleNo11 = vehicleNo1.getText().toString();


                // message = "*Mahalaxmi Petroleums*\n_Chintalapalem, Cumbum_ \n\n" + currentDateTimeString + "\n\n" + "*Current Stock Position* \n\n```HSD : " + formatValue(volHSD) + " Ltrs```\n```MS  : " + formatValue(volMS) + " Ltrs```";
                message = "Thannk you very much for the purchase of " + RbValue +" " + product + " at our RO\n\nDetails of your purchase \n\n" +
                        "Bill No:" + " " + billString + "\n" +
                        "Vehicle No:" + " " + vehicleNo11 + "\n" +
                        "Quantity:" + " " + formatValue(litres11, "####.####") + " Ltrs\n" +
                        "Amount: Rs." + " " + formatValue(amount11, "##,##,###.##")+ "\n\n" +
                        "Providing The Best Quality Fuel \nMahalaxmi Petroleums\nChintalapalem, Cumbum";

                sendSMS(message);
            }

            else MainActivity.showToast("Sale value is zero, Enter Amt & Price values",getContext());


        } catch (Exception e) {
            MainActivity.showToast(e.toString(),getContext());
        }
    }
    // Method For sending sms
    private void sendSMS(String message) {
        //String smsNumber = "7****"; //without '+'
        try {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.putExtra("sms_body", message);
            sendIntent.setType("vnd.android-dir/mms-sms");
            startActivity(sendIntent);
        } catch(Exception e) {

            MainActivity.showToast(e.toString(),getContext());
        }

    }



}



