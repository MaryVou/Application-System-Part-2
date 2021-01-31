package gr.hua.entity;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Application {

	private int id;
	private String type;
	private String category;
	private int days;
	@DateTimeFormat(iso = ISO.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate start_date;
	@DateTimeFormat(iso = ISO.DATE)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate last_date;
	private Blob req_papers;
	private Boolean super_sig;
	private Boolean mgr_sig;

	public Application() {

	}

	public Application(int id, String type, String category, int days, LocalDate start_date, LocalDate last_date,
			Blob req_papers, Boolean super_sig, Boolean mgr_sig) {
		super();
		this.id = id;
		this.type = type;
		this.category = category;
		this.days = days;
		this.start_date = start_date;
		this.last_date = last_date;
		this.req_papers = req_papers;
		this.super_sig = super_sig;
		this.mgr_sig = mgr_sig;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getLast_date() {
		return last_date;
	}

	public void setLast_date(LocalDate last_date) {
		this.last_date = last_date;
	}

	public Blob getReq_papers() {
		return req_papers;
	}

	public void setReq_papers(Blob req_papers) {
		this.req_papers = req_papers;
	}

	public Boolean getSuper_sig() {
		return super_sig;
	}

	public void setSuper_sig(Boolean super_sig) {
		this.super_sig = super_sig;
	}

	public Boolean getMgr_sig() {
		return mgr_sig;
	}

	public void setMgr_sig(Boolean mgr_sig) {
		this.mgr_sig = mgr_sig;
	}

}
