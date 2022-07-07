import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBattleship {
    @Test
    void battleClassCreationTest() {
        Battleship battleshipr = new Battleship();
        Assert.assertNotNull(battleshipr);
    }
}
