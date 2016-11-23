package uk.co.usryders.sainsburys.techtest.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results {

	private List<Result> results = new ArrayList<Result>();

	private BigDecimal total = new BigDecimal(0).setScale(2, RoundingMode.HALF_DOWN);

	/**
	 * @return A list of the products found
	 */
	public List<Result> getResults() {
		// Maintain encapsulation of the set of results.
		return Collections.unmodifiableList(results);
	}

	/**
	 * @param result
	 *            Add a result to the list of results. Increment the total by
	 *            the unit price.
	 */
	public void addResult(Result result) {
		this.results.add(result);
		total = total.add(result.getUnitPrice());
	}

	/**
	 * @return The total value of all the products
	 */
	public BigDecimal getTotal() {
		return total;
	}

}
