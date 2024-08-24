package mahalaxmi.mahalaxmi;

/**
 * Created by SIV on 10-03-2018.
 */

/**
 * Created by SIV on 12-09-2016.
 */
public class Liquid_Volume {

    final short radius;
    final short length;
    short height;
    double volume;


    public Liquid_Volume(short rad, short len, short height){
        this.radius = rad;
        this.length = len;
        this.height = height;
    }


    private double calVol(short rad, short len, short height) {
        double area;
        area = (Math.PI * Math.pow(rad, 2) / 2) - Math.pow(rad, 2) * Math.asin(1 - (height / (double)rad)) - (rad - height) * Math.sqrt(height * (2 * rad - height));
        return Math.round(area*len*0.000001);
    }

    public double getVolume(){

        volume = calVol(radius,length,height);
        return volume;

    }


}

