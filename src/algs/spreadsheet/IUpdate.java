package algs.spreadsheet;

/**
 * Update the specific cell with its proper value.
 * 
 * Only needed for bonus question.
 */
public interface IUpdate {

	/** Alert GUI that given cell identified by reference has changed its value. */
	void update(String reference, double value);
}