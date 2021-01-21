package test.Model;

import controllers.GameController;
import model.Player;
import model.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Joseph Cantrell on 11/11/2015.
 */
public class SellFoodTest {
    Player player;
    Store store;

    @Before
    public void before() {
        player = new Player("Joseph", Player.Race.HUMAN, Player.Color.BLUE);
        store = new Store(GameController.Difficulty.STANDARD);
    }

    @After
    public void after() {
        player = null;
        store = null;
    }

    /**
     * Test buying food from the player, in other words, the player selling food
     */
    @Test
    public void testSellFoodNotEnoughFood() {
        int playerFood = 5;
        int storeFood = 8;
        int amountToBuy = 6;
        int playerMoney = 5000;
        player.setFood(playerFood);
        player.setMoney(playerMoney);
        store.sellFood(amountToBuy, player);
        assertEquals(store.getFood(), 8);
        assertEquals(5, player.getFood());
        assertEquals(5000, player.getMoney());
    }
    @Test
    public void testSellFood() {
        int foodPrice = 30;
        int playerFood = 5;
        int storeFood = 8;
        int amountToBuy = 2;
        int playerMoney = 5000;
        player.setFood(playerFood);
        player.setMoney(playerMoney);
        store.sellFood(amountToBuy, player);
        assertEquals(store.getFood(), 10);
        assertEquals(player.getFood(), playerFood - amountToBuy);
        assertEquals(player.getMoney(), playerMoney + foodPrice * amountToBuy);
    }

}
