package com.notifier.model;

import java.time.LocalDate;

public class Note {
	private int id;
	private LocalDate endDate;
	private String noteDescription;
	private String notename;
	private LocalDate remainderDate;
	private LocalDate startDate;
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getNoteDescription() {
		return noteDescription;
	}
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}
	public String getNotename() {
		return notename;
	}
	public void setNotename(String notename) {
		this.notename = notename;
	}
	public LocalDate getRemainderDate() {
		return remainderDate;
	}
	public void setRemainderDate(LocalDate remainderDate) {
		this.remainderDate = remainderDate;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Note(LocalDate endDate, String noteDescription, String notename, LocalDate remainderDate, LocalDate startDate, boolean status) {
		super();
		this.endDate = endDate;
		this.noteDescription = noteDescription;
		this.notename = notename;
		this.remainderDate = remainderDate;
		this.startDate = startDate;
		this.status = status;
	}
	public Note(int id, LocalDate endDate, String noteDescription, String notename, LocalDate remainderDate,
			LocalDate startDate, boolean status) {
		super();
		this.id = id;
		this.endDate = endDate;
		this.noteDescription = noteDescription;
		this.notename = notename;
		this.remainderDate = remainderDate;
		this.startDate = startDate;
		this.status = status;
	}
	
	
}
