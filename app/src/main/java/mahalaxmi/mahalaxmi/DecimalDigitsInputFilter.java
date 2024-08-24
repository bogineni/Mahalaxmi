package mahalaxmi.mahalaxmi;

import android.text.InputFilter;
import android.text.Spanned;

//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 * Created by SIV on 21-03-2018.
 */

public class DecimalDigitsInputFilter implements InputFilter {

    //#1 InputFilter filter = new InputFilter() {
    final int maxDigitsBeforeDecimalPoint ;
    final int maxDigitsAfterDecimalPoint ;

    public DecimalDigitsInputFilter(int digitsBeforeDecimal,int digitsAfterDecimal ){
        maxDigitsBeforeDecimalPoint = digitsBeforeDecimal;
        maxDigitsAfterDecimalPoint = digitsAfterDecimal;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        StringBuilder builder = new StringBuilder(dest);
        builder.replace(dstart, dend, source
                .subSequence(start, end).toString());
        if (!builder.toString().matches(
                "(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?"

        )) {
            if (source.length() == 0)
                return dest.subSequence(dstart, dend);
            return "";
        }

        return null;

    }
    //#1 };

    // mEdittext.setFilters(new InputFilter[] { filter });

}















/*
public class DecimalDigitsInputFilter implements InputFilter {

    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero ) {
       // mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        mPattern=Pattern.compile("[0-9]{0,2}+((\\.[0-9]{0,1})?)||(\\.)?"); //this is working but taking 3 digits after decimal

        //mPattern=Pattern.compile("[0-9]{0,2}+((\\.[0-9]{0,1})?)");
        //This above pattern is working the possible values it
        //is taking is X, XX, XXX , XX.XX , X.XX, .XX

       // mPattern=Pattern.compile("[0-9]{2}+((\\.[0-9]{0,1})?)"); // not letting anything to enter
       // mPattern=Pattern.compile("^[5-9][0-9]\\.[5-9][0-9]$"); //not letting to enter anything
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Matcher matcher=mPattern.matcher(dest);
        if(!matcher.matches())
            return "";
        return null;
    }

} */
