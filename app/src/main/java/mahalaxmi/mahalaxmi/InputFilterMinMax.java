package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 27-03-2018.
 */

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;


public class InputFilterMinMax implements InputFilter {

    private int min, max;
    private double dmin, dmax;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax (double dmin, double dmax){
        this.dmin = dmin;
        this.dmax = dmax;
    }

    /*public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }*/

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) {

        }

     /*   try {
            double input = Double.parseDouble(dest.toString() + source.toString());
            if (isInRange(dmin, dmax, input))
                return null;
        } catch (NumberFormatException nfe) {
            Fragment_three f = new Fragment_three();
            MainActivity.showToast(nfe.toString()+ "From double", f.getContext() );
        } */
        return "";
    }

    private  boolean  isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

  //  private  boolean  isInRange(double a, double b, double c) {
  //      return b > a ? c >= a && c <= b : c >= b && c <= a;
  //  }


}