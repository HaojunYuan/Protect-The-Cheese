package model;

import java.util.ArrayList;
import java.util.List;

public class MapModel {
    private List<PathModel> paths;
    private List<PathModel> towerPaths;

    public MapModel() {
        this.paths = new ArrayList<>();
        this.towerPaths = new ArrayList<>();
    }

    public List<PathModel> getPaths() {
        return paths;
    }

    public List<PathModel> getTowerPaths() {
        return towerPaths;
    }

    public void setPaths(List<PathModel> paths) {
        this.paths = paths;
    }

    public void setTowerPaths(List<PathModel> towerPaths) {
        this.towerPaths = towerPaths;
    }
}
