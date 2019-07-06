package com.muheda.domain;

/**
 * @desc 用于存储当前点之前的2个点
 */
public class PreTwoPoints {

    private Point firstPoint;
    private Point secondPoint;

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

}

