package test.Model;

import model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
* @author Justin Hicks. Junit tests for setTurnTime.
 *
* @since <pre>Nov 2, 2015</pre> 
* @version 1.0.4
*/ 
public class PlayerTest { 
    private Player player;

    @Before
    public void before() {
        player = new Player("Justin", Player.Race.HUMAN, Player.Color.RED);
        player.setFood(2); //Arbitrary nonzero food amount
    }

    @After
    public void after() {
        player = null;
    }


    /**
    * setTurnTime junit tests.
     * * When the Player has no food.
    */
    @Test
    public void testSetTurnTimeNoFood() {
        player.setFood(0);
        int expectedTT = 5;
        int randomRound = 12; //arbitrary round number
        player.setTurnTime(randomRound);
        assertEquals(expectedTT, player.getTurnTime());
    }
    /**
     * When the round is less than or equal to 4, and the Player's food is less than 3.
     */
    @Test
    public void testSetTurnTimeRound4LowTime() {
        int foodNum = 2;
        int round = 4;
        player.setFood(foodNum);
        int expectedTT = 30;
        player.setTurnTime(round);
        assertEquals(expectedTT, player.getTurnTime());
    }
    /**
     * When the round is less than or equal to 4, and the Player's food is more than 3.
     */
    @Test
    public void testSetTurnTimeRound4MoreFoodNormalTime() {
        int foodNum = 4;
        int round = 4;
        player.setFood(foodNum);
        int expectedTT = 50;
        player.setTurnTime(round);
        assertEquals(expectedTT, player.getTurnTime());
    }
    /**
     * When the round is less than or equal to 8, and the Player's food is less than 4.
     */
    @Test
    public void testSetTurnTimeRound8LowTime() {
        int foodNum = 3;
        int round = 8;
        player.setFood(foodNum);
        int expectedTT = 30;
        player.setTurnTime(round);
        assertEquals(expectedTT, player.getTurnTime());
    }
    /**
     * When the round is less than or equal to 8, and the Player's food is more than 4.
     */
    @Test
    public void testSetTurnTimeRound8NormalTime() {
        int foodNum = 5;
        int round = 8;
        player.setFood(foodNum);
        int expectedTT = 50;
        player.setTurnTime(round);
        assertEquals(expectedTT, player.getTurnTime());
    }
    /**
     * When the round is greater than 8, and the Player's food is less than 5.
     */
    @Test
    public void testSetTurnTimeRound9LowTime() {
        int foodNum = 4;
        int round = 9;
        player.setFood(foodNum);
        int expectedTT = 30;
        player.setTurnTime(round);
        assertEquals(expectedTT, player.getTurnTime());
    }
    /**
     * When the round is greater than 8, and the Player's food is greater than 5.
     */
    @Test
    public void testSetTurnTimeRound9NormalTime() {
        int foodNum = 6;
        int round = 9;
        player.setFood(foodNum);
        int expectedTT = 50;
        player.setTurnTime(round);
        assertEquals(expectedTT, player.getTurnTime());
    }
} 
