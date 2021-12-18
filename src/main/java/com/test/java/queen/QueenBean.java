package com.test.java.queen;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class QueenBean {
    private int x;
    private int y;

    public QueenBean() {
    }

    public QueenBean(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QueenBean queenBean = (QueenBean) o;

        if (x != queenBean.x) return false;
        return y == queenBean.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    //是否冲突 true是 false 否
    public boolean isConflct(QueenBean queenBean) {
        if (queenBean == null) {
            return false;
        }
        if (this.getX() == queenBean.getX() || this.getY() == queenBean.getY()) {
            return true;
        } else return Math.abs(this.getX() - queenBean.getX()) == Math.abs(this.getY() - queenBean.getY());
    }

    public boolean isConflictList(List<QueenBean> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        for (QueenBean queenBean : list) {
            if (isConflct(queenBean)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "QueenBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
