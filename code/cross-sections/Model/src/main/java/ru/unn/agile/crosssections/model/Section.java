package ru.unn.agile.crosssections.model;

public class Section {

    private Point a;
    private Point b;

    public Section(Point a, Point b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Points should not be in one place");
        }
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }
}