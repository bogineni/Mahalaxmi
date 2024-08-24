package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 10-03-2018.
 */

/**
 * Created by SIV on 08-09-2016.
 */

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
import android.widget.TextView;

public class Fragment_two extends Fragment {

    private static EditText volH,volM;
    private static TextView dipH,dipM;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        setHasOptionsMenu(true);

        volH = (EditText) view.findViewById(R.id.volHSD2);
        volM = (EditText) view.findViewById(R.id.volMS2);

        volH.setFilters(new InputFilter[] {new InputFilterMinMax(1,30000)});
        volM.setFilters(new InputFilter[] {new InputFilterMinMax(1,20000)});

        dipH = (TextView) view.findViewById(R.id.dipHSD2);
        dipM = (TextView) view.findViewById(R.id.dipMS2);


        TextWatcher textwatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                clickFun2(editable);
            }
        };

        volH.addTextChangedListener(textwatcher);
        volM.addTextChangedListener(textwatcher);



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
                clearFun2();
                return true;

            case R.id.share:
               // MainActivity.showToast("Nothing is here to share");
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void clickFun2 (Editable editable)
    {


        double volHSD = 0, volMS = 0;
        final double radHSD = 121.9;
        final double lenHSD = 681.0;
        final double radMS = 99.9;
        final double lenMS = 680.4;
        double dipHSD, dipMS;


        try {
            volHSD = Double.parseDouble(volH.getText().toString());
            if(volHSD>30000 || volHSD<0){
                clearFun2();
                MainActivity.showToast("Enter correct values", getContext());

            }
            else {
                Liquid_Height HSD = new Liquid_Height(radHSD,lenHSD,volHSD);
                dipHSD = HSD.getDip();
                //String.format("%f",dipHSD);
                //dipH.setText(Double.toString(dipHSD));
                dipH.setText(dipHSD + "");
            }

        }
        catch (NumberFormatException e) {
            dipH.setText("");
        }
        try{ volMS = Double.parseDouble(volM.getText().toString());

            if(volMS>20000 || volMS<0){
                clearFun2();
                MainActivity.showToast("Enter correct values", getContext());

            }
            else {

                Liquid_Height MS = new Liquid_Height(radMS, lenMS, volMS);
                dipMS = MS.getDip();
                //String.format("%f",dipHSD);
                //dipM.setText(String.format("%f",dipMS));
                dipM.setText(dipMS + "");
            }
        }
        catch(NumberFormatException e){
            dipM.setText("");
        }

    }

    public void clearFun2(){

        volH.setText("");
        volM.setText("");
        dipH.setText("");
        dipM.setText("");

    }

}
