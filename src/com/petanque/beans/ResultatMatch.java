package com.petanque.beans;

public class ResultatMatch {
	//Attributs
	private int id_eq1;
	private int id_eq2;
	private int score_eq1;
	private int score_eq2;
	private int winner;
	
	//Setters
	public void set_eq1(int id_eq1) {
		this.id_eq1 = id_eq1;
	}
	public void set_eq2(int id_eq2) {
		this.id_eq2 = id_eq2;
	}
	public void set_score1(int score1) {
		this.score_eq1 = score1;
	}
	public void set_score2(int score2) {
		this.score_eq2 = score2;
	}
	public void set_winner(int w) {
		this.winner = w;
	}
	
	//Getters
	public int get_eq1() {
		return this.id_eq1;
	}
	public int get_eq2() {
		return this.id_eq2;
	}
	public int get_score1() {
		return this.score_eq1;
	}
	public int get_score2() {
		return this.score_eq2;
	}
	public int get_winner() {
		return this.winner;
	}
	

}
