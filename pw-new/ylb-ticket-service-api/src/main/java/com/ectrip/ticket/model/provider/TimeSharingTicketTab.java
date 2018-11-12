package com.ectrip.ticket.model.provider;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 分时时间信息
 * @author root
 *
 */
@Entity
@Table
public class TimeSharingTicketTab implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String startDate;
	private String endDate;
	private String dayTime;//有效开始时间
	@Column(name="totalStock")
	private Integer tatalStock;
	private Integer currStock;
	private Integer saleStock;
	private String productId;
	@Transient
	private List<TimeSharingTicketTab> times;
	@Transient
	private String showDate;

	public String getShowDate() {
		return this.startDate+"-"+endDate;
	}


	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	@Transient
	private String hourStart;
	@Transient
	private String hourEnd;
	@Transient
	private String minuteStart;
	@Transient
	private String minuteEnd;


	public TimeSharingTicketTab() {
		// TODO Auto-generated constructor stub
	}


	public TimeSharingTicketTab(Long id, String startDate, String endDate, Integer tatalStock, Integer currStock,
								Integer saleStock, String productId) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.tatalStock = tatalStock;
		this.currStock = currStock;
		this.saleStock = saleStock;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public String getDayTime() {
		return dayTime;
	}


	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getTatalStock() {
		return tatalStock;
	}
	public void setTatalStock(Integer tatalStock) {
		this.tatalStock = tatalStock;
	}
	public Integer getCurrStock() {
		return currStock;
	}
	public void setCurrStock(Integer currStock) {
		this.currStock = currStock;
	}
	public Integer getSaleStock() {
		return saleStock;
	}
	public void setSaleStock(Integer saleStock) {
		this.saleStock = saleStock;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}



	public List<TimeSharingTicketTab> getTimes() {
		return times;
	}


	public void setTimes(List<TimeSharingTicketTab> times) {
		this.times = times;
	}

	public String getHourStart() {
		if(startDate != null && !"".equals(startDate)){
			return startDate.split(":")[0];
		}else{
			return this.hourStart;
		}

	}


	public void setHourStart(String hourStart) {
		this.hourStart = hourStart;
	}


	public String getHourEnd() {
		if(endDate != null && !"".equals(endDate)){
			return endDate.split(":")[0];
		}else{
			return this.hourEnd;
		}

	}


	public void setHourEnd(String hourEnd) {
		this.hourEnd = hourEnd;
	}


	public String getMinuteStart() {
		if(startDate != null && !"".equals(startDate)){
			return startDate.split(":")[1];
		}else{
			return this.minuteStart;
		}
	}


	public void setMinuteStart(String minuteStart) {
		this.minuteStart = minuteStart;
	}


	public String getMinuteEnd() {
		if(endDate != null && !"".equals(endDate)){
			return endDate.split(":")[1];
		}else{
			return this.minuteEnd;
		}
	}


	public void setMinuteEnd(String minuteEnd) {
		this.minuteEnd = minuteEnd;
	}

}
