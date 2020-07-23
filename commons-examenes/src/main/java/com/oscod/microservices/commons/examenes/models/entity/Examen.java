package com.oscod.microservices.commons.examenes.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "examenes")
public class Examen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@JsonIgnoreProperties(value = {"examen"}, allowSetters = true)
	@OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pregunta> preguntas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private Asignatura asignatura;


	public Examen() {
		this.preguntas = new ArrayList<>();
	}

	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}

	public void addPregunta(Pregunta pregunta) {
		pregunta.setExamen(this);
		this.preguntas.add(pregunta);
	}
	
	public void removePregunta(Pregunta pregunta) {
		pregunta.setExamen(null);
		this.preguntas.remove(pregunta);
	}

	
	
	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Examen)) {
			return false;
		}

		Examen a = (Examen) obj;
		return this.id != null && this.id.equals(a.getId());
	}

	
	
	
	

	
	
	
	
}
