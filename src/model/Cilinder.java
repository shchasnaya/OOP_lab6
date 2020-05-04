package model;

public class Cilinder extends AbstractForm {
    private float length;
    private float diameter;

    public Cilinder(Wood wood, float length, float diameter) throws Exception {
        super(wood);
        setLength(length);
        setDiameter(diameter);
    }


    public float getLength() {
        return length;
    }

    public float getDiametr() {
        return diameter;
    }

    @Override
    public String toString() {
        return "Cilinder:  wood - " + wood +
                ", weight - " + weight() +
                "cm2, volume - " + volume() + "cm3";
    }

    @Override
    public float volume(){

        return (float) (length*(2/3)* Math.pow(diameter/2, 2));
    }

    public void setLength(float length) throws Exception {
        if(length < 20 || length > 800)
            throw new Exception(length +
                    " is not correct weight.\n"
                    + "Must be from 20 to 800cm.");
        this.length = length;
    }

    public void setDiameter(float diameter) throws Exception {
        if(diameter < 20 || diameter > 800)
            throw new Exception(diameter +
                    " is not correct weight.\n"
                    + "Must be from 20 to 800cm.");
        this.diameter = diameter;
    }
}
