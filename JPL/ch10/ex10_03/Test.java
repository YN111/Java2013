import junit.framework.TestCase;

public class Test extends TestCase {

	public void testWeek() throws Exception {
		// if-else
		assertFalse(Week.isWeekday_if(Week.SUNDAY));
		assertTrue(Week.isWeekday_if(Week.MONDAY));
		assertTrue(Week.isWeekday_if(Week.TUESDAY));
		assertTrue(Week.isWeekday_if(Week.WEDNESDAY));
		assertTrue(Week.isWeekday_if(Week.THURSDAY));
		assertTrue(Week.isWeekday_if(Week.FRIDAY));
		assertFalse(Week.isWeekday_if(Week.SATURDAY));

		// switch
		assertFalse(Week.isWeekday_switch(Week.SUNDAY));
		assertTrue(Week.isWeekday_switch(Week.MONDAY));
		assertTrue(Week.isWeekday_switch(Week.TUESDAY));
		assertTrue(Week.isWeekday_switch(Week.WEDNESDAY));
		assertTrue(Week.isWeekday_switch(Week.THURSDAY));
		assertTrue(Week.isWeekday_switch(Week.FRIDAY));
		assertFalse(Week.isWeekday_switch(Week.SATURDAY));
	}
}