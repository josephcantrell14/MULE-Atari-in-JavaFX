package test.Model;

import controllers.GameController;
import model.Mule;
import model.Player;
import model.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @Author John Thomas
 */
public class StoreTest {
    Player player;
    Store store;

    @Before
    public void before() {
        player = new Player("John", Player.Race.HUMAN, Player.Color.BLUE);
        player.setMoney(0);
        store = new Store(GameController.Difficulty.BEGINNER);
    }

    @After
    public void after() {
        player = null;
        store = null;
    }

    @Test
    public void testBuyMuleTypeFood() {
        player.setMoney(500);
        store.buyMule(player, Mule.Type.FOOD);
        assertEquals(player.getMoney(), 375);
        assertTrue(player.hasMule());
        assertEquals(player.getMule().getOwner(), player);
        assertEquals(player.getMule().getType(), Mule.Type.FOOD);
    }

    @Test
    public void testBuyMuleTypeOre() {
        player.setMoney(500);
        store.buyMule(player, Mule.Type.ORE);
        assertEquals(player.getMoney(), 325);
        assertTrue(player.hasMule());
        assertEquals(player.getMule().getOwner(), player);
        assertEquals(player.getMule().getType(), Mule.Type.ORE);
    }

    @Test
    public void testBuyMuleTypeEnergy() {
        player.setMoney(500);
        store.buyMule(player, Mule.Type.ENERGY);
        assertEquals(player.getMoney(), 350);
        assertTrue(player.hasMule());
        assertEquals(player.getMule().getOwner(), player);
        assertEquals(player.getMule().getType(), Mule.Type.ENERGY);
    }

    @Test
    public void testBuyMuleTypeCrystite() {
        player.setMoney(500);
        store.buyMule(player, Mule.Type.CRYSTITE);
        assertEquals(player.getMoney(), 300);
        assertTrue(player.hasMule());
        assertEquals(player.getMule().getOwner(), player);
        assertEquals(player.getMule().getType(), Mule.Type.CRYSTITE);
    }

    @Test
    public void testBuyMuleNotEnoughMoney() {
        player.setMoney(100);
        store.buyMule(player, Mule.Type.ENERGY);
        assertEquals(player.getMoney(), 100);
        assertFalse(player.hasMule());
        assertNull(player.getMule());
    }

    @Test
    public void testBuyMuleAlreadyHasMule() {
        player.setMoney(500);
        store.buyMule(player, Mule.Type.ENERGY);
        store.buyMule(player, Mule.Type.ORE);
        assertEquals(player.getMoney(), 350);
        assertTrue(player.hasMule());
        assertEquals(player.getMule().getType(), Mule.Type.ENERGY);
    }

    @Test
    public void testBuyMuleStoreOutOfMules() {
        player.setMoney(5000);
        for (int i = 0; i < 25; i++) {
            store.buyMule(player, Mule.Type.ENERGY);
            player.removeMule();
        }
        store.buyMule(player, Mule.Type.CRYSTITE);
        assertEquals(player.getMoney(), 1250);
        assertFalse(player.hasMule());
        assertNull(player.getMule());
    }

}
