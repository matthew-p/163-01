package atmPack;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.*;
public class ATMTest {

	/**
	 * ***  Your assignment is to write many test cases  *****
	 */
	/* some examples provided to help you get started */

	// Testing valid constructors with wide range of values
	@Test
	public void testConstructor() {
		ATM s1 = new ATM(6, 5, 4);
		
		assertEquals (s1.getHundreds(), 6);
		assertEquals (s1.getFifties(), 5);
		assertEquals (s1.getTwenties(), 4);
		
		ATM s2 = new ATM();
		assertEquals (s2.getHundreds(), 0);
		assertEquals (s2.getFifties(), 0);
		assertEquals (s2.getTwenties(), 0);
		
		ATM s3 = new ATM(s1);
		assertEquals (s3.getHundreds(), 6);
		assertEquals (s3.getFifties(), 5);
		assertEquals (s3.getTwenties(), 4);
	}
	
	// testing valid takeOut with wide range of
	// quarters, dimes, nickels, pennies
	@Test
	public void testTakeOut1() {
		ATM s1 = new ATM(3,3,2);
		s1.takeOut(1,1,1);
		assertEquals (s1.getHundreds(), 2);
		assertEquals (s1.getFifties(), 2);
		assertEquals (s1.getTwenties(), 1);
	}
	
	// testing valid takeOut with wide range of amounts
	@Test
	public void testTakeOut2() {
		ATM s1 = new ATM(5,3,3);
		ATM s2 = s1.takeOut(120);
		/*
		assertEquals (s1.getHundreds(), 4);
		assertEquals (s1.getFifties(), 3);
		assertEquals (s1.getTwenties(), 2);
		*/
		assertEquals (s2.getHundreds(), 1);
		assertEquals (s2.getFifties(), 0);
		assertEquals (s2.getTwenties(), 1);
	}
	
	       // testing valid takeOut with wide range of amounts
        @Test
        public void testTakeOut3() {
                ATM s1 = new ATM(5,3,3);
                ATM s2 = s1.takeOut(110);
                /*
                assertEquals (s1.getHundreds(), 5);
                assertEquals (s1.getFifties(), 2);
                assertEquals (s1.getTwenties(), 0);
                */
                assertEquals (s2.getHundreds(), 0);
                assertEquals (s2.getFifties(), 1);
                assertEquals (s2.getTwenties(), 3);
        }
        
        @Test
        public void testTakeOut4() {
                ATM s1 = new ATM(5,3,6);
                ATM s2 = s1.takeOut(130);
                /*
                assertEquals (s1.getHundreds(), 5);
                assertEquals (s1.getFifties(), 2);
                assertEquals (s1.getTwenties(), 2);
                */
                assertEquals (s2.getHundreds(), 0);
                assertEquals (s2.getFifties(), 1);
                assertEquals (s2.getTwenties(), 4);
        }
        
        @Test
        public void testTakeOut5() {
                ATM s1 = new ATM(5,3,6);
                ATM s2 = s1.takeOut(170);
                
               /* assertEquals (s1.getHundreds(), 4);
                assertEquals (s1.getFifties(), 2);
                assertEquals (s1.getTwenties(), 5);*/
                
                assertEquals (s2.getHundreds(), 1);
                assertEquals (s2.getFifties(), 1);
                assertEquals (s2.getTwenties(), 1);
        }
        
        @Test
        public void testTakeOut6() {
                ATM s1 = new ATM(5,3,6);
                ATM s2 = s1.takeOut(180);
                
               /* assertEquals (s1.getHundreds(), 4);
                assertEquals (s1.getFifties(), 3);
                assertEquals (s1.getTwenties(), 2);*/
                
                assertEquals (s2.getHundreds(), 1);
                assertEquals (s2.getFifties(), 0);
                assertEquals (s2.getTwenties(), 4);
        }
        
        @Test
        public void testTakeOut7() {
                ATM s1 = new ATM(5,3,6);
                ATM s2 = s1.takeOut(330);
                
                /*assertEquals (s1.getHundreds(), 3);
                assertEquals (s1.getFifties(), 2);
                assertEquals (s1.getTwenties(), 2);*/
                
                assertEquals (s2.getHundreds(), 2);
                assertEquals (s2.getFifties(), 1);
                assertEquals (s2.getTwenties(), 4);
        }
        
        @Test
        public void testTakeOut8() {
                ATM s1 = new ATM(5,3,6);
                ATM s2 = s1.takeOut(340);
                
                /*assertEquals (s1.getHundreds(), 2);
                assertEquals (s1.getFifties(), 3);
                assertEquals (s1.getTwenties(), 4);*/
                
                assertEquals (s2.getHundreds(), 3);
                assertEquals (s2.getFifties(), 0);
                assertEquals (s2.getTwenties(), 2);
        }
        
        @Test
        public void testTakeOut9() {
                ATM s1 = new ATM(5,3,6);
                ATM s2 = s1.takeOut(350);
                
                /*assertEquals (s1.getHundreds(), 2);
                assertEquals (s1.getFifties(), 2);
                assertEquals (s1.getTwenties(), 6);*/
                
                assertEquals (s2.getHundreds(), 3);
                assertEquals (s2.getFifties(), 1);
                assertEquals (s2.getTwenties(), 0);
        }
        
//        @Test
//        public void testTakeOut10() {
//                ATM s1 = new ATM(6,20,6);
//                ATM s2 = s1.takeOut(930);
//                
//               /* assertEquals (s1.getHundreds(), 0);
//                assertEquals (s1.getFifties(), 15);
//                assertEquals (s1.getTwenties(), 2);*/
//                
//                assertEquals (s2.getHundreds(), 6);
//                assertEquals (s2.getFifties(), 5);
//                assertEquals (s2.getTwenties(), 4);
//        }
        
//        @Test
//        public void testTakeOut11() {
//                ATM s1 = new ATM(6,20,6);
//                ATM s2 = s1.takeOut(960);
//                
////                assertEquals (s1.getHundreds(), 0);
////                assertEquals (s1.getFifties(), 14);
////                assertEquals (s1.getTwenties(), 3);
//                
//                assertEquals (s2.getHundreds(), 6);
//                assertEquals (s2.getFifties(), 6);
//                assertEquals (s2.getTwenties(), 3);
//        }
        
        @Test
        (expected = IllegalArgumentException.class)
        public void testTakeOut12() {
                ATM s1 = new ATM(6,20,6);
                ATM s2 = s1.takeOut(111);       
                
        }
        
        @Test
        (expected = IllegalArgumentException.class)
        public void testTakeOut13() {
            ATM s1 = new ATM(6,20,6);
            ATM s2 = s1.takeOut(66);
            
        }
        
        @Test
        (expected = IllegalArgumentException.class)
        public void testTakeOut14() {
            ATM s1 = new ATM(6,20,6);
            ATM s2 = s1.takeOut(9);

        }
	
        @Test
        public void testTakeOut15() {
            ATM s1 = new ATM(6,20,6);
            ATM s2 = s1.takeOut(10);
            
//            assertEquals (s1.getHundreds(), 6);
//            assertEquals (s1.getFifties(), 20);
//            assertEquals (s1.getTwenties(), 6);
            
            assertEquals (s2, null);
            
        }
        
        @Test
        public void testTakeOut16() {
            ATM s1 = new ATM(6,20,6);
            ATM s2 = s1.takeOut(30);
            
//            assertEquals (s1.getHundreds(), 6);
//            assertEquals (s1.getFifties(), 20);
//            assertEquals (s1.getTwenties(), 6);
            
            assertEquals (s2, null);
            
        }
        
        @Test
        public void testTakeOut17() {
            ATM s1 = new ATM(6,20,6);
            ATM s2 = s1.takeOut(20);
            
//            assertEquals (s1.getHundreds(), 6);
//            assertEquals (s1.getFifties(), 20);
//            assertEquals (s1.getTwenties(), 5);
            
            assertEquals (s2.getHundreds(), 0);
            assertEquals (s2.getFifties(), 0);
            assertEquals (s2.getTwenties(), 1);
            
        }
        
        @Test
        public void testTakeOut18() {
            ATM s1 = new ATM(6,20,6);
            ATM s2 = s1.takeOut(50);
            
//            assertEquals (s1.getHundreds(), 6);
//            assertEquals (s1.getFifties(), 19);
//            assertEquals (s1.getTwenties(), 6);
            
            assertEquals (s2.getHundreds(), 0);
            assertEquals (s2.getFifties(), 1);
            assertEquals (s2.getTwenties(), 0);
            
        }
        
//        @Test
//        public void testTakeOut19() {
//            ATM s1 = new ATM(2,4,11);
//            ATM s2 = s1.takeOut(490);
//            
////            assertEquals (s1.getHundreds(), 0);
////            assertEquals (s1.getFifties(), 1);
////            assertEquals (s1.getTwenties(), 4);
//            
//            assertEquals (s2.getHundreds(), 2);
//            assertEquals (s2.getFifties(), 3);
//            assertEquals (s2.getTwenties(), 7);
//            
//        }
//        
//        @Test
//        public void testTakeOut20() {
//            ATM s1 = new ATM(12,20,56);
//            ATM s2 = s1.takeOut(3090);
//            
////            assertEquals (s1.getHundreds(), 0);
////            assertEquals (s1.getFifties(), 1);
////            assertEquals (s1.getTwenties(), 9);
//            
//            assertEquals (s2.getHundreds(), 12);
//            assertEquals (s2.getFifties(), 19);
//            assertEquals (s2.getTwenties(), 47);
//            
//        }
        
//        @Test
//        public void testTakeOut21() {
//            ATM s1 = new ATM(1,1,8);
//            ATM s2 = s1.takeOut(240);
//            
////            assertEquals (s1.getHundreds(), 0);
////            assertEquals (s1.getFifties(), 1);
////            assertEquals (s1.getTwenties(), 1);
//            
//            assertEquals (s2.getHundreds(), 1);
//            assertEquals (s2.getFifties(), 0);
//            assertEquals (s2.getTwenties(), 7);
//            
//        }
        
	// testing putIn for valid low numbers
	@Test
	public void testPutIn() {
		ATM s1 = new ATM();
		s1.putIn(2,3,4);
		assertEquals (s1.getHundreds(), 2);
		assertEquals (s1.getFifties(), 3);
		assertEquals (s1.getTwenties(), 4);
	}
	
	// testing putIn and takeOut together
	@Test
	public void testPutInTakeOut() {
		ATM s1 = new ATM();
		s1.putIn(3,3,2);
		s1.takeOut(1,1,1);
		assertEquals (s1.getHundreds(), 2);
		assertEquals (s1.getFifties(), 2);
		assertEquals (s1.getTwenties(), 1);
	}

	// Testing equals for valid numbers
	@Test
	public void testEqual () {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 5, 4);

		assertFalse(s1.equals(s2));
		assertTrue(s1.equals(s3));
	}

	// testing compareTo all returns
	@Test
	public void testCompareTo () {
		ATM s1 = new ATM(2, 5, 4);
		ATM s2 = new ATM(6, 5, 4);
		ATM s3 = new ATM(2, 3, 4);
		ATM s4 = new ATM(2, 5, 4);

		assertTrue(s2.compareTo(s1) > 0);
		assertTrue(s3.compareTo(s1) < 0);
		assertTrue(s1.compareTo(s4) == 0);
	}
	
	// test toString
	@Test
	public void testToString() {
	    ATM s1 = new ATM(1, 10, 1);
	    ATM s2 = new ATM(0, 1, 20);
	    
	    assertTrue(s1.toString().equals("1 hundred dollar bill, 10 fifty dollar bills, 1 twenty dollar bill"));
	    assertTrue(s2.toString().equals("0 hundred dollar bills, 1 fifty dollar bill, 20 twenty dollar bills"));    
	}

	// load and save combined. 
	@Test
	public void testLoadSave() throws FileNotFoundException {
		ATM s1 = new ATM(6, 5, 4);
		ATM s2 = new ATM(6, 5, 4);

		s1.save("file1");
		s1 = new ATM();  // resets to zero

		s1.load("file1");
		assertTrue(s1.equals(s2));

	}
	

	// testing not able to make change
	@Test
	public void testTakeOutNull() {
		ATM s1 = new ATM(3,1,2);
		ATM s2 = s1.takeOut(700);
		assertEquals(s2,  null);
	}
	
	@Test
	public void testMutate01() {
		ATM s1 = new ATM(6, 5, 4);
		ATM.suspend(true);
		s1.takeOut(120);
		assertEquals (s1.getHundreds(), 6);
		assertEquals (s1.getFifties(), 5);
		assertEquals (s1.getTwenties(), 4);
		ATM.suspend(false);
	}
	
	@Test
        public void testMutate02() {
                ATM s1 = new ATM(6, 5, 4);
                ATM.suspend(true);
                s1.takeOut(120);
                assertEquals (s1.getHundreds(), 6);
                assertEquals (s1.getFifties(), 5);
                assertEquals (s1.getTwenties(), 4);
                ATM.suspend(false);
                s1.takeOut(3,1,2);
                assertEquals (s1.getHundreds(), 3);
                assertEquals (s1.getFifties(), 4);
                assertEquals (s1.getTwenties(), 2);
        }
	
	// IMPORTANT: only one test per exception!!!
	
	
	// testing negative number for nickels, takeOut
	@Test
	(expected = IllegalArgumentException.class)
	public void testTakeOutNegTwenties() {
		ATM s1 = new ATM(2,2,2);
		s1.takeOut(1,1,-1);
	}

	// testing negative number quarters, for constructors
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegHunderies() {
		new ATM(-300, 0, 0);		
	}
	
	// testing negative number for quarters, putIn
	@Test(expected = IllegalArgumentException.class)
	public void testPutInNeghunderies() {
		ATM s = new ATM(2,3,4);
		s.putIn(-30,2,30);
	}
}