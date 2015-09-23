package pl.sqer.controller.dashboard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import pl.sqer.controller.common.ChartUtils;
import pl.sqer.controller.common.Identity;
import pl.sqer.dao.common.Messages;
import pl.sqer.dto.resolution.ResolutionEnum;
import pl.sqer.dto.tasks.TaskDto;
import pl.sqer.service.task.TaskService;

/**
 * The Class DashboardController.
 */
@ManagedBean(name = "dashboard")
@ViewScoped
public class DashboardController implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1678043799405843751L;

	/** The Constant LOG. */
	private static final Logger LOG = Logger
			.getLogger(DashboardController.class);

	/** The identity. */
	@ManagedProperty(value = "#{identity}")
	Identity identity;

	/** The selected. */
	private TaskDto selected;

	/** The task service. */
	@EJB
	private TaskService taskService;

	/** The list. */
	private List<TaskDto> list;

	/** The model. */
	private DashboardModel model;

	/** The pie chart data. */
	private String pieResolutionChartData;

	/** The pie data list. */
	private final List<KeyValue> resolutionChartDataList = new ArrayList<KeyValue>();

	/** The pie chart data. */
	private String pieTypeChartData;

	/** The pie data list. */
	private final List<KeyValue> typeChartDataList = new ArrayList<KeyValue>();

	/**
	 * Instantiates a new tasks controller.
	 */
	public DashboardController() {
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		createDashboard();
	}

	/**
	 * Creates the dashboard.
	 */
	private void createDashboard() {
		// Chart
		createCharts();
		// table
		createTable();

		// Create dashboard model
		model = new DefaultDashboardModel();
		final DashboardColumn column1 = new DefaultDashboardColumn();
		final DashboardColumn column2 = new DefaultDashboardColumn();
		column1.addWidget("tasks");
		column2.addWidget("resolutionChart");
		column2.addWidget("typeChart");
		model.addColumn(column1);
		model.addColumn(column2);
	}

	/**
	 * Creates the chart.
	 */
	private void createCharts() {
		final Map<String, String> map = taskService.selectResolutionChartData();

		for (final Entry<String, String> entry : map.entrySet()) {
			resolutionChartDataList.add(new KeyValue(entry.getKey(), entry
					.getValue()));
		}

		final Map<String, String> map2 = taskService.selectTypeChartData();

		for (final Entry<String, String> entry : map2.entrySet()) {
			typeChartDataList
					.add(new KeyValue(entry.getKey(), entry.getValue()));
		}

	}

	/**
	 * Creates the table
	 */
	private void createTable() {
		list = taskService.findByUserAndResolution(identity.getUser().getId(),
				ResolutionEnum.UNRESOLVED);
	}

	/**
	 * Gets list of tasks dtos.
	 *
	 * @return the tasks list
	 */
	public List<TaskDto> getList() {
		return list;
	}

	/**
	 * Got to edit.
	 *
	 * @return the string
	 */
	public String gotToEdit() {
		LOG.info("Go to edit page");
		if (selected != null) {
			return "tasks_details.xhtml?id=" + selected.getId()
					+ "&faces-redirect=true";
		} else {
			LOG.info("User not select any task");
			Messages.showWarnMessage("taskDatatable",
					DashboardMessages.DASHBOARD_MSG_PLEASE_SELECT_TASK_SUMMARY,
					DashboardMessages.DASHBOARD_MSG_PLEASE_SELECT_TASK_DETAILS);
			return null;
		}
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public TaskDto getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected
	 *            the new selected
	 */
	public void setSelected(final TaskDto selected) {
		this.selected = selected;
	}

	/**
	 * Gets the identity.
	 *
	 * @return the identity
	 */
	public Identity getIdentity() {
		return identity;
	}

	/**
	 * Sets the identity.
	 *
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(final Identity identity) {
		this.identity = identity;
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public DashboardModel getModel() {
		return model;
	}

	/**
	 * Gets the resolution chart data.
	 *
	 * @return the chart data
	 */
	public String getPieResolutionChartData() {
		if (pieResolutionChartData == null
				|| pieResolutionChartData.trim().length() <= 0) {
			pieResolutionChartData = ChartUtils
					.populateChartData(resolutionChartDataList);
		}
		return pieResolutionChartData;
	}

	/**
	 * Gets the type chart data.
	 *
	 * @return the chart data
	 */
	public String getPieTypeChartData() {
		if (pieTypeChartData == null || pieTypeChartData.trim().length() <= 0) {
			pieTypeChartData = ChartUtils.populateChartData(typeChartDataList);
		}
		return pieTypeChartData;
	}

	/**
	 * Gets the resolution chart title
	 *
	 * @return the title
	 */
	public String getResolutionChartTitle() {
		return Messages.getMessage("lbl_dashboard_resolution_chart");
	}

	/**
	 * Gets the type chart title
	 *
	 * @return the title
	 */
	public String getTypeChartTitle() {
		return Messages.getMessage("lbl_dashboard_type_chart");
	}
}