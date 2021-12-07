package common;

import model.PathModel;
import model.PositionModel;

import java.util.ArrayList;
import java.util.List;

public class PathGenerator {
    public static PathModel getPath1() {
        List<PositionModel> positions = new ArrayList<>();
        positions.add(new PositionModel(0, 15));
        positions.add(new PositionModel(1, 15));
        positions.add(new PositionModel(2, 15));
        positions.add(new PositionModel(3, 15));
        positions.add(new PositionModel(4, 15));
        positions.add(new PositionModel(5, 15));
        positions.add(new PositionModel(6, 15));
        positions.add(new PositionModel(7, 15));
        positions.add(new PositionModel(8, 15));
        positions.add(new PositionModel(9, 15));

        positions.add(new PositionModel(10, 15));
        positions.add(new PositionModel(10, 16));
        positions.add(new PositionModel(10, 17));
        positions.add(new PositionModel(10, 18));
        positions.add(new PositionModel(10, 19));
        positions.add(new PositionModel(10, 20));

        positions.add(new PositionModel(11, 20));
        positions.add(new PositionModel(12, 20));
        positions.add(new PositionModel(13, 20));
        positions.add(new PositionModel(14, 20));


        positions.add(new PositionModel(14, 21));
        positions.add(new PositionModel(14, 22));
        positions.add(new PositionModel(14, 23));

        positions.add(new PositionModel(15, 23));
        positions.add(new PositionModel(16, 23));
        positions.add(new PositionModel(17, 23));
        positions.add(new PositionModel(18, 23));

        positions.add(new PositionModel(18, 22));
        positions.add(new PositionModel(18, 21));
        positions.add(new PositionModel(18, 20));
        positions.add(new PositionModel(18, 19));
        positions.add(new PositionModel(18, 18));
        positions.add(new PositionModel(18, 17));
        positions.add(new PositionModel(18, 16));
        positions.add(new PositionModel(18, 15));
        positions.add(new PositionModel(18, 14));
        positions.add(new PositionModel(18, 13));
        positions.add(new PositionModel(18, 12));
        positions.add(new PositionModel(18, 11));
        positions.add(new PositionModel(18, 10));

        positions.add(new PositionModel(19, 10));
        positions.add(new PositionModel(20, 10));
        positions.add(new PositionModel(21, 10));
        positions.add(new PositionModel(22, 10));
        positions.add(new PositionModel(23, 10));
        positions.add(new PositionModel(24, 10));
        positions.add(new PositionModel(25, 10));
        positions.add(new PositionModel(26, 10));
        positions.add(new PositionModel(27, 10));
        positions.add(new PositionModel(28, 10));
        positions.add(new PositionModel(29, 10));
        positions.add(new PositionModel(30, 10));

        return new PathModel(positions);
    }

    public static PathModel getTowerPath1() {
        List<PositionModel> positions = new ArrayList<>();

        PathModel path = getPath1();
        List<PositionModel> existingPathPositions = path.getPositions();
        String direction = "Y";
        for (int i = 0; i < existingPathPositions.size() - 1; i += 2) {
            // if next y is same as current y, then set tower stations next to it
            if (existingPathPositions.get(i).getPositionY()
                    == existingPathPositions.get(i + 1).getPositionY()) {
                if (direction.equals("Y")) {
                    positions.add(new PositionModel(existingPathPositions.get(i).getPositionX(),
                            existingPathPositions.get(i).getPositionY() + 1));
                    positions.add(new PositionModel(existingPathPositions.get(i).getPositionX(),
                            existingPathPositions.get(i).getPositionY() - 1));
                }
                direction = "Y";
            }

            // if next x is same as current x, then set tower stations next to it
            if (existingPathPositions.get(i).getPositionX()
                    == existingPathPositions.get(i + 1).getPositionX()) {
                if (direction.equals("X")) {
                    positions.add(new PositionModel(existingPathPositions.get(i).getPositionX() + 1,
                            existingPathPositions.get(i).getPositionY()));
                    positions.add(new PositionModel(existingPathPositions.get(i).getPositionX() - 1,
                            existingPathPositions.get(i).getPositionY()));
                }
                direction = "X";
            }
        }

        return new PathModel(positions);
    }
}
