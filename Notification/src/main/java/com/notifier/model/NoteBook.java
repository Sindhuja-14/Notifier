package com.notifier.model;

public class NoteBook {
	private int id_;
	private String notebook_name;
	
	public int getId_() {
		return id_;
	}
	public void setId_(int id_) {
		this.id_ = id_;
	}
	public String getNotebook_name() {
		return notebook_name;
	}
	public void setNotebook_name(String notebook_name) {
		this.notebook_name = notebook_name;
	}
	public NoteBook(int id_, String notebook_name) {
		super();
		this.id_ = id_;
		this.notebook_name = notebook_name;
	}
	

}

