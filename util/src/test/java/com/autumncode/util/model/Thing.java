package com.autumncode.util.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Thing")
@Data
@Builder
@NoArgsConstructor
public class Thing {
    public Thing(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column
    String name;
}
