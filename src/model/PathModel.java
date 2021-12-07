package model;

import java.util.List;

public class PathModel {
    private List<PositionModel> positions;

    public PathModel(List<PositionModel> positions) {
        this.positions = positions;
    }

    public List<PositionModel> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionModel> positions) {
        this.positions = positions;
    }
}
