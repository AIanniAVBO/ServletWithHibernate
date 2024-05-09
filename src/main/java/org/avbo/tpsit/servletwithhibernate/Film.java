package org.avbo.tpsit.servletwithhibernate;

import jakarta.persistence.*;

@Entity
@Table(name="film")
public class Film {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="film_id", nullable=false)
	public int filmID;
    @Column(name="title", nullable=false)
    public String title;
    @Column(name="description")
    public String description;
    
    public Film()
    {
    }
    
	public Film(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public Film(int filmID, String title, String description) {
		super();
		this.filmID = filmID;
		this.title = title;
		this.description = description;
	}
	
	public synchronized int getFilmID() {
		return filmID;
	}
	public synchronized void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	public synchronized String getTitle() {
		return title;
	}
	public synchronized void setTitle(String title) {
		this.title = title;
	}
	public synchronized String getDescription() {
		return description;
	}
	public synchronized void setDescription(String description) {
		this.description = description;
	}
    
}
