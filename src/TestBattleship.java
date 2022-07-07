import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBattleship {
    @Test
    void battleClassCreationTest() {
        Battleship battleshipr = new Battleship();
        Assert.assertNotNull(battleshipr);
    }

    @Test
    void battleDrawFieldTest() {
        Battleship battleshipr = new Battleship();
        int[][] battlefield = new int[10][10];
        int actual = 10;
        int expected = battleshipr.drawField(battlefield);

        Assert.assertEquals(actual, expected);
    }

    @Test
    void battleAvailableTest(){
        Battleship battleshipr = new Battleship();
        int x = 0;
        int y = 0;
        int deck = 4;
        int rotation = 1;
        int[][] battlefield = new int[10][10];
        Assert.assertTrue(battleshipr.available(x,y,deck,rotation,battlefield));
    }

     @Test
     void battleClearScreenTest(){
         Battleship battleshipr = new Battleship();
         Assert.assertTrue(battleshipr.clearscreen());
     }

    @Test
    void battleMakeTurnTest(){
        Battleship battleshipr = new Battleship();
        String playerName = "";
        int[][] monitor = new int[10][10];
        int[][] battlefield = new int[10][10];
        Assert.assertTrue(battleshipr.makeTurn(playerName,monitor,battlefield));

    }

}
