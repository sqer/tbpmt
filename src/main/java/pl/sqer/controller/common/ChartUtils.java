package pl.sqer.controller.common;

import java.util.List;

import pl.sqer.controller.dashboard.KeyValue;

/**
 * The Class ChartUtils.
 */
public final class ChartUtils {

	private ChartUtils() {
		// default constructor
	}

	/**
	 * Populate data.
	 *
	 * @param pieDataList
	 *            the data list
	 */
	public static String populateChartData(final List<KeyValue> pieDataList) {
		final StringBuilder stringBuilder = new StringBuilder();
		for (final KeyValue pieData : pieDataList) {
			stringBuilder.append("[");
			stringBuilder.append("'");
			stringBuilder.append(pieData.getKey());
			stringBuilder.append("'");
			stringBuilder.append(",");
			stringBuilder.append(pieData.getValue());
			stringBuilder.append("]");
			stringBuilder.append(",");
		}
		return stringBuilder.toString().substring(0,
				stringBuilder.toString().length() - 1);
	}
}
