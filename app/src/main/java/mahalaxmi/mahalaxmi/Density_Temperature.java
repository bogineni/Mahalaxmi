package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 10-03-2018.
 */

/**
 * Created by SIV on 17-09-2016.
 */



public class Density_Temperature {

    private double dx,tx,density15,deltaT, hydCorrection, realDensity;

    /* dx = measured density at temperature tx
        tx = temperature at which dx is measured
        density15 = density to be found out at 15 deg celcius
        realDensity = value of density (dx) after hydrometer correction
        deltaT = tx - 15 deg celius
     */



    public  Density_Temperature ( double dx, double tx ) {

        this.dx = dx;
        this.tx = tx;
        this.deltaT = tx - 15 ;
        this.hydCorrection = 1 - 0.000023*deltaT - 0.00000002*deltaT*deltaT ;
        this.realDensity = dx * hydCorrection ; // measured density after hydrometer correction

        this.density15 = find_d15x();

    }





    public double roundTo(double x,int n){
        //double pn = Math.pow(10,n);
        //return Math.round(x*pn)/pn;
        // second parameter has become useless as above two lines of code is not working.

        return Math.round(x*10000d)/10000d;

    }

    public double getDensity15(){

        return roundTo(density15,4);
    }




    private double find_d15x() {
        double d15x;
        if (tx != 15) d15x = find_d15();
        else  d15x = realDensity;
        return d15x;
    }


    private double find_d15()
    {
        double d0 = realDensity;
        double guard = 30;
        while(guard > 0)
        {
            double d0new = approx_d15(d0);

            d0 = d0new;
            guard--;
        }
        return d0;
    }

  private double approx_d15(double d){

        return realDensity / vcf(alpha15(d));

  }

    private double vcf (double alphaValue){
        return Math.pow(Math.E, -alphaValue*deltaT - 0.8*alphaValue*alphaValue*deltaT*deltaT );
    }

    private double alpha15(double d15)
    {
        double K0, K1 ;

        if (d15 >= 839) {
            K0 = 186.9696;
            K1 = 0.4862;

            return (K0/(d15*d15)) + (K1/d15);
        }

        else if (d15 >= 788){
            K0 = 594.5418;
            K1 = 0.0;

            return (K0/(d15*d15)) + (K1/d15);

        }

        else if (d15 > 770){

            K0 = 2680.3206;
            K1 = 0;

            return  (K0/(d15*d15)) - 0.00336312;


        }

        else {
            K0 = 346.4228;
            K1 = 0.4388;

            return (K0/(d15*d15)) + (K1/d15);
        }





    } //end of alpha15 code



} //end of class
