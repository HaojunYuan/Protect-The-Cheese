package common;

import model.MapModel;
import org.junit.Assert;
import org.junit.Test;

public class MapGeneratorTest {

    /**
     * Author: Sha Wei
     */
    @Test
    public void testGetMapReturnMapWithPaths() {
        MapModel mapModel = MapGenerator.getMapById(1);
        Assert.assertNotNull(mapModel);
        Assert.assertNotNull(mapModel.getPaths());
        Assert.assertTrue(mapModel.getPaths().size() > 0);
    }
}
