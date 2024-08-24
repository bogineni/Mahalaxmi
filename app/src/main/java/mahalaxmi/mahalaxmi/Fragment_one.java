package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 10-03-2018.
 */

/**
 * Created by SIV on 08-09-2016.
 */




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
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class Fragment_one extends Fragment {



    final short radHSD = 1219;
    final short lenHSD = 6810;
    final short radMS = 999;
    final short lenMS = 6804;

    private static EditText dipH,dipM;
    private static TextView volH,volM;

    short dipHSD , dipMS ;
    double volHSD, volMS;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        setHasOptionsMenu(true);

        dipH = (EditText) view.findViewById(R.id.dipHSD1);
        dipM = (EditText) view.findViewById(R.id.dipMS1);

        dipH.setFilters(new InputFilter[] {new InputFilterMinMax(1,2*radHSD)});
        dipM.setFilters(new InputFilter[] {new InputFilterMinMax(1,2*radMS)});

        volH = (TextView) view.findViewById(R.id.volHSD1);
        volM = (TextView) view.findViewById(R.id.volMS1);


        TextWatcher textwatcherHSD = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                volHSD(editable);
            }
        };

        TextWatcher textwatcherMS = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                volMS(editable);
            }
        };

        dipH.addTextChangedListener(textwatcherHSD);
        dipM.addTextChangedListener(textwatcherMS);



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
            case R.id.clear_one:
                clearFun1();
                return true;
            case R.id.share:
                sendToWhatsap();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void volHSD (Editable editable) {

        try {

            String dh = editable.toString();
            if(!dh.isEmpty()) {
                dipHSD = Short.parseShort(dh);
                Liquid_Volume dtvh = new Liquid_Volume(radHSD, lenHSD, dipHSD);
                volHSD = dtvh.getVolume();
                volH.setText(formatValue(volHSD));
            }

        } catch (NumberFormatException e) {
            volH.setText("");
            MainActivity.showToast(e.toString(), getContext());
        }

    }


    public void volMS (Editable editable) {
        try{
            String dm = editable.toString();
            if(!dm.isEmpty()) {
                dipMS = Short.parseShort(dm);
                Liquid_Volume dtvm = new Liquid_Volume(radMS, lenMS, dipMS);
                volMS = dtvm.getVolume();
                volM.setText(formatValue(volMS));
            }

        }
        catch(NumberFormatException e){
            volM.setText("");
            MainActivity.showToast(e.toString(), getContext());
        }

    }


    public void clearFun1(){

        volH.setText("");
        volM.setText("");
        dipH.setText("");
        dipM.setText("");

        dipHSD =0 ; dipMS = 0 ; volHSD = 0.0 ; volMS = 0.0;

    }

    private String formatValue(double num){
        DecimalFormat formatter = new DecimalFormat("##,###.#");

        return formatter.format(num);

    }

    private void sendToWhatsap(){
        String message = "";
        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        try{
            if(dipHSD !=0 && dipMS != 0 && volHSD != 0.0 && volMS != 0.0 ){

                message = "*Mahalaxmi Petroleums*\n_Chintalapalem, Cumbum_ \n\n" + currentDateTimeString + "\n\n" + "*Current Stock Position* \n\n```HSD : " + formatValue(volHSD) + " Ltrs```\n```MS  : " + formatValue(volMS) + " Ltrs```";

                openWhatsApp(message);
            }

            else MainActivity.showToast("Blank fields are detected. Enter the dip values", getContext());




        }
        catch (Exception e){
            MainActivity.showToast(e.toString(), getContext());

        }

    }


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




}
