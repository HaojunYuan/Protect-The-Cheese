package model;

import java.util.Objects;

public class PositionModel {
    private int positionX;
    private int positionY;

    public PositionModel(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PositionModel that = (PositionModel) o;
        return positionX == that.positionX && positionY == that.positionY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionX, positionY);
    }
}
