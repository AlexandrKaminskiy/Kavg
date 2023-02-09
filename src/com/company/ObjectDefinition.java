package com.company;

public class ObjectDefinition{

    private final int x;
    private final int y;
    private final double scalar;
    private int clas;
    private boolean isCenter;

    public ObjectDefinition(int x, int y) {
        this.x = x;
        this.y = y;
        scalar = Math.sqrt(x * x + y * y);
    }

    public double getScalar() {
        return scalar;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getClas() {
        return clas;
    }

    public void setClas(int clas) {
        this.clas = clas;
    }

    public boolean isCenter() {
        return isCenter;
    }

    public void setCenter(boolean center) {
        isCenter = center;
    }

    @Override
    public String toString() {
        return "ObjectDefinition{" +
                "x=" + x +
                ", y=" + y +
                ", clas=" + clas +
                ", isCenter=" + isCenter +
                '}';
    }
}
