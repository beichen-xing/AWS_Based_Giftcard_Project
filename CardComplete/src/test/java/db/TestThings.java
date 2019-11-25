package db;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.UUID;
import org.junit.Test;

import model.Card;


public class TestThings {
	@Test
	public void testGet() {
	    GiftCardDAO cd = new GiftCardDAO();
	    try {
	    	Card c = cd.getCard("1");
	    	System.out.println("card " + c.id);
	    } catch (Exception e) {
	    	fail ("didn't work:" + e.getMessage());
	    }
	}
}
