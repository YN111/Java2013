import java.util.LinkedList;

public class PrintQueue {

	private LinkedList<PrintJob> list = new LinkedList<PrintJob>();

	/**
	 * —v‘f‚ğ’Ç‰Á‚µ‚Ü‚·
	 * @param j
	 */
	public void add(PrintJob j) {
		list.add(j);
	}

	/**
	 * —v‘f‚ğíœ‚µAæ“ª‚Ì—v‘f‚ğ•Ô‚µ‚Ü‚·
	 * —v‘f‚ª‘¶İ‚µ‚È‚¢ê‡‚Ínull‚ª•Ô‚è‚Ü‚·
	 * @return
	 */
	public PrintJob remove() {
		if (list.size() == 0)
			return null;

		return list.remove();
	}

}
