package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 10-03-2018.
 */

/**
 * Created by SIV on 12-09-2016.
 */


public class Liquid_Height{

    //final double radHSD = 121.9;
    //final double lenHSD = 681.0;
    //final double radMS = 99.9;
    //final double lenMS = 680.4;

    final double epsilon = 0.0001;
    double dip,volume,radius,length;

    public Liquid_Height(double x, double y, double z){
        this.radius = x;
        this.length = y;
        this.volume = z;

        double ratio,guess,dip1;

        ratio = volume*1000/(radius*radius*length);

        guess = 2*ratio/Math.PI;

        dip1 = height(guess,ratio,epsilon);

        dip = dip1*radius;
    }


    public double fun1(double x,double y){
        return Math.acos(1-x) + Math.sqrt(x*(2-x))*(x-1) - y ;
    }
    public double fun2(double x){
        return 2*Math.sqrt(x*(2-x));
    }
    public boolean isGoodEnough(double x,double y,double e){
        return Math.abs((Math.acos (1-x) + Math.sqrt(x*(2-x))*(x-1) - y)/y)> e;
    }

    public double height(double x,double y,double e){
        while (isGoodEnough(x,y,e)){
            x = x - fun1(x,y)/fun2(x);
        }

        return x;
    }

    public double getDip(){return Math.round(dip*10.0)/10.0;}



}