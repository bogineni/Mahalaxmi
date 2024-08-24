package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 10-03-2018.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by SIV on 10-09-2016.
 */
public class Fragment_three extends Fragment {


    private static EditText m_density,m_temperature,in_density;

    private static TextView density_15,variation,result;

    private final int den_low_limit = 653, den_up_limit=999 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        setHasOptionsMenu(true);

        m_density = (EditText) view.findViewById(R.id.in_m_density);
        m_temperature = (EditText) view.findViewById(R.id.in_m_temp);
        in_density = (EditText)view.findViewById(R.id.in_in_density);

        density_15 = (TextView) view.findViewById(R.id.out_density);
        variation = (TextView) view.findViewById(R.id.out_variation);
        result = (TextView) view.findViewById(R.id.result_text);


        m_density.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});
        m_temperature.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(2,1)});
        in_density.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,1)});


        //following code not letting to enter other than zero... have to check it..for now above two lines of code
        //is substituted in the place of below two lines of code
        // One reason may be these two edittexts are allowed to take decimals in xml file, but inputfilterminmax has no code for
        //doubles.
        // m_density.setFilters(new InputFilter[] {new InputFilterMinMax(653,999)});
        // in_density.setFilters(new InputFilter[] {new InputFilterMinMax(653,999)});


        final Button calcBtn31  =
                (Button) view.findViewById(R.id.calcBtn3);

        calcBtn31.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickFun3();
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
            case R.id.clear_one:
                clearFun3();
                return true;

            case R.id.share:
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void clickFun3(){

        double m_den,m_temp,in_den,out_den,out_var;

        //variation.setVisibility(View.VISIBLE);

        try {
            m_den = Double.parseDouble(m_density.getText().toString());
            m_temp = Double.parseDouble(m_temperature.getText().toString());
            in_den = Double.parseDouble(in_density.getText().toString());

            if (m_den<den_low_limit || m_den>den_up_limit || in_den<den_low_limit || in_den>den_up_limit){
                clearFun3();
                MainActivity.showToast("Densities must be between " +  den_low_limit + " and " +  den_up_limit, getContext());


            }

            else if (m_temp<0 || m_temp>100){
                clearFun3();
                MainActivity.showToast("Temperature must be between 0 and 99.9", getContext());


            }

            else{
                //MainActivity.showToast("Error/nIf Variation is more than 30 points, Fuel is not acceptable", getContext());

                Density_Temperature dt = new Density_Temperature(m_den, m_temp);
                out_den = dt.getDensity15();
                out_var = dt.roundTo(Math.abs(in_den - out_den)*10, 4);

                density_15.setText(out_den + "");
                variation.setText(out_var + "");

                if (out_var > 30) {
                    result.setTextColor(0xFFFF0000);
                    result.setText("Not Acceptable");


                } else {
                    result.setTextColor(0xFF00FF00);
                    result.setText("Acceptable");
                }
            }


        }
        catch (NumberFormatException e) {
            density_15.setText("");
            variation.setText("");

        }

    }

    public void clearFun3(){

        density_15.setText("");
        variation.setText("");
        m_density.setText("");
        m_temperature.setText("");
        in_density.setText("");
        result.setText("");

    }




}