package pl.sqer.controller.task;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import pl.sqer.dto.tasks.TaskDto;

/**
 * The Class LazyTasksSorter.
 */
public class LazyTaskSorter implements Comparator<TaskDto> {

	/** The sort field. */
	private final String sortField;

	/** The sort order. */
	private final SortOrder sortOrder;

	/**
	 * Instantiates a new lazy tasks sorter.
	 *
	 * @param sortField
	 *            the sort field
	 * @param sortOrder
	 *            the sort order
	 */
	public LazyTaskSorter(final String sortField, final SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final TaskDto o1, final TaskDto o2) {
		try {
			final Object value1 = TaskDto.class.getField(this.sortField)
					.get(o1);
			final Object value2 = TaskDto.class.getField(this.sortField)
					.get(o2);

			final int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (final Exception e) {
			throw new RuntimeException();
		}
	}
}