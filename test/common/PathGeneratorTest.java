package common;

import model.PathModel;
import org.junit.Assert;
import org.junit.Test;

public class PathGeneratorTest {

    /**
     * Author: Sha Wei
     */
    @Test
    public void testPathGeneratorGetPath1() {
        PathModel pathModel = PathGenerator.getPath1();
        Assert.assertNotNull(pathModel);
        Assert.assertTrue(pathModel.getPositions().size() > 0);
    }
}
