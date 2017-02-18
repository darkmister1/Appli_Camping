package com.petanque.beans;
import java.util.*;

public class TirageEquipe {
	private Doublette d;
	private ArrayList<Integer> tirage;
	
	public void set_doublette(Doublette d) {
		this.d = d;
	}
	
	public void set_tableau_tirage(ArrayList<Integer> tt) {
		this.tirage = tt;
	}
	
	public Doublette get_doublette() {
		return this.d;
	}
	public ArrayList<Integer> get_tableau_tirage() {
		return this.tirage;
	}
}
