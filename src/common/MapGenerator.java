package common;

import model.MapModel;

public class MapGenerator {
    public static MapModel getMapById(Integer mapId) {
        MapModel map = new MapModel();
        if (mapId == 1) {
            map.getPaths().add(PathGenerator.getPath1());
            map.getTowerPaths().add(PathGenerator.getTowerPath1());
        }
        return map;
    }

}
