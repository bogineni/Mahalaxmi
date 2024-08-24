package mahalaxmi.mahalaxmi;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
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

import java.text.DecimalFormat;

/**
 * Created by SIV on 10-03-2018.
 */

public class Fragment_four extends Fragment {


    private static EditText notes2000, notes500, notes200, notes100, notes50, notes20, notes10, notes5, notes2, notes1;
    private static TextView value2000, value500, value200, value100, value50, value20, value10, value5, value2, value1;
    private static TextView errorText41, quantityTotal, valueTotal;

    int val2000=0, val500=0, val200=0, val100=0, val50=0, val20=0, val10=0, val5=0, val2=0, val1=0;
    int totalQty, totalValue;
    int tval2000=0, tval500=0, tval200=0, tval100=0, tval50=0, tval20=0, tval10=0, tval5=0, tval2=0, tval1=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.denom, container, false);
        setHasOptionsMenu(true);

        notes2000 = (EditText) view.findViewById(R.id.qty2000);
        notes500 = (EditText) view.findViewById(R.id.qty500);
        notes200 = (EditText) view.findViewById(R.id.qty200);
        notes100 = (EditText) view.findViewById(R.id.qty100);
        notes50 = (EditText) view.findViewById(R.id.qty50);
        notes20 = (EditText) view.findViewById(R.id.qty20);
        notes10 = (EditText) view.findViewById(R.id.qty10);
        notes5 = (EditText) view.findViewById(R.id.qty5);
        notes2 = (EditText) view.findViewById(R.id.qty2);
        notes1 = (EditText) view.findViewById(R.id.qty1);


        value2000 = (TextView) view.findViewById(R.id.result2000);
        value500 = (TextView) view.findViewById(R.id.result500);
        value200 = (TextView) view.findViewById(R.id.result200);
        value100 = (TextView) view.findViewById(R.id.result100);
        value50 = (TextView) view.findViewById(R.id.result50);
        value20 = (TextView) view.findViewById(R.id.result20);
        value10 = (TextView) view.findViewById(R.id.result10);
        value5 = (TextView) view.findViewById(R.id.result5);
        value2 = (TextView) view.findViewById(R.id.result2);
        value1 = (TextView) view.findViewById(R.id.result1);
        quantityTotal = (TextView) view.findViewById(R.id.qtyTotal);
        valueTotal = (TextView) view.findViewById(R.id.resultTotal);
        errorText41 = (TextView) view.findViewById(R.id.errorText4);



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



        notes2000.addTextChangedListener(textwatcher);
        notes500.addTextChangedListener(textwatcher);
        notes200.addTextChangedListener(textwatcher);
        notes100.addTextChangedListener(textwatcher);
        notes50.addTextChangedListener(textwatcher);
        notes20.addTextChangedListener(textwatcher);
        notes10.addTextChangedListener(textwatcher);
        notes5.addTextChangedListener(textwatcher);
        notes2.addTextChangedListener(textwatcher);
        notes1.addTextChangedListener(textwatcher);

        //*/

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
                clearFun4();
                return true;
            case R.id.share:
                // MainActivity.showToast("Nothing is here to share");
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleTextChange(Editable s){
        try {
            if ( notes2000.getText().hashCode() == s.hashCode()) {
                val2000 = Integer.parseInt(notes2000.getText().toString());
                tval2000 = value(2000, val2000);
                value2000.setText(formatValue(tval2000));

            }

            if ( notes500.getText().hashCode() == s.hashCode()) {
                val500 = Integer.parseInt(notes500.getText().toString());;
                tval500 = value(500, val500);
                value500.setText(formatValue(tval500));
            }

            if ( notes200.getText().hashCode() == s.hashCode()) {
                val200 = Integer.parseInt(notes200.getText().toString());
                tval200 = value(200, val200);
                value200.setText(formatValue(tval200));
            }
            if ( notes100.getText().hashCode() == s.hashCode()) {
                val100 = Integer.parseInt(notes100.getText().toString());
                tval100 = value(100, val100);
                value100.setText(formatValue(tval100));
            }
            if ( notes50.getText().hashCode() == s.hashCode()) {
                val50 = Integer.parseInt(notes50.getText().toString());
                tval50 = value(50, val50);
                value50.setText(formatValue(tval50));
            }

            if ( notes20.getText().hashCode() == s.hashCode()) {
                val20 = Integer.parseInt(notes20.getText().toString());
                tval20 = value(20, val20);
                value20.setText(formatValue(tval20));
            }

            if ( notes10.getText().hashCode() == s.hashCode()) {
                val10 = Integer.parseInt(notes10.getText().toString());
                tval10 = value(10, val10);
                value10.setText(formatValue(tval10 ));
            }
            if ( notes5.getText().hashCode() == s.hashCode()) {
                val5 = Integer.parseInt(notes5.getText().toString());
                tval5 = value(5, val5);
                value5.setText(formatValue(tval5));
            }
            if ( notes2.getText().hashCode() == s.hashCode()) {
                val2 = Integer.parseInt(notes2.getText().toString());
                tval2 = value(2, val2);
                value2.setText(formatValue(tval2));
            }
            if ( notes1.getText().hashCode() == s.hashCode()) {
                val1 = Integer.parseInt(notes1.getText().toString());
                tval1 = value(1, val1);
                value1.setText(formatValue(tval1));
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }

        finally {

            totalQty = val2000 + val500 + val200 + val100 + val50 + val20 + val10 + val5 + val2 + val1;
            totalValue = tval2000 + tval500 + tval200 + tval100 + tval50 + tval20 + tval10 + tval5 + tval2 + tval1;

            quantityTotal.setText(formatValue(totalQty));
            valueTotal.setText(formatValue(totalValue));
            errorText41.setText(numToWords(totalValue));
        }
    }

    private int value(int r, int s) {
    return r * s;
    }

    private String formatValue(int num){
        DecimalFormat formatter = new DecimalFormat("#,##,##,###");

        return formatter.format(num);

       }

    private String numToWords (int n){
        NumberToWordsConverter ntw = new NumberToWordsConverter();
        return ntw.convert(n);
    }

    private void clearFun4(){
        notes2000.setText("");
        notes500.setText("");
        notes200.setText("");
        notes100.setText("");
        notes50.setText("");
        notes20.setText("");
        notes10.setText("");
        notes5.setText("");
        notes2.setText("");
        notes1.setText("");

        value2000.setText("");
        value500.setText("");
        value200.setText("");
        value100.setText("");
        value50.setText("");
        value20.setText("");
        value10.setText("");
        value5.setText("");
        value2.setText("");
        value1.setText("");



        quantityTotal.setText("");
        valueTotal.setText("");
        errorText41.setText("");

        val2000=0; val500=0; val200=0; val100=0; val50=0; val20=0; val10=0; val5=0; val2=0; val1=0;
        totalQty=0; totalValue=0;
        tval2000=0; tval500=0; tval200=0; tval100=0; tval50=0; tval20=0; tval10=0; tval5=0; tval2=0; tval1=0;


    }



}
