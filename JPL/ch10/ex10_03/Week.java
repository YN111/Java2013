public enum Week {
	SUNDAY, 
	MONDAY, 
	TUESDAY, 
	WEDNESDAY, 
	THURSDAY, 
	FRIDAY, 
	SATURDAY;

	public static boolean isWeekday_if(Week week) {
		if (week == SUNDAY || week == SATURDAY)
			return false;
		else
			return true;
	}

	public static boolean isWeekday_switch(Week week) {
		switch (week) {
		case SUNDAY:
		case SATURDAY:
			return false;
		default:
			return true;
		}
	}
}
