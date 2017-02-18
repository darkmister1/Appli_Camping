package com.petanque.beans;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class Joueur {
	//Attributs
	private String nom_joueur;
	private String prenom_joueur;
	private int numero_joueur;
	private List<String> messages = new ArrayList<String>();
	private List<Joueur> joueurs_inscrits = new ArrayList<Joueur>();
	//Getters
	public String get_nomJoueur() {
		return this.nom_joueur;
	}
	public String get_prenomJoueur() {
		return this.prenom_joueur;
	}
	public int get_numeroJoueur() {
		return this.numero_joueur;
	}
	
	//Setters
	public void set_nomJoueur(String nom) {
		this.nom_joueur = nom;
	}
	public void set_prenomJoueur(String prenom) {
		this.prenom_joueur = prenom;
	}
	public void set_numeroJoueur(int num) {
		this.numero_joueur = num;
	}
	
	//Insertion du nouveau joueur en DB
	
	@SuppressWarnings("finally")
	public List<String> insertJoueurIntoDB(HttpServletRequest request) {
		//Chargement du driver JDBC
		try {
			messages.add("Chargement du driver ...");
			Class.forName("com.mysql.jdbc.Driver");
			messages.add("Driver charge !");
		} catch (ClassNotFoundException e) {
			messages.add("Erreur lors du chargement du driver : " + e.getMessage());
		}
		
		String url_bdd="jdbc:mysql://localhost:9090/bdd_thomas";
		String utilisateur="admin";
		String mdp="database";
		Connection co = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//Connexion à la BDD
			messages.add("Connexion a la BDD");
			co = DriverManager.getConnection(url_bdd,utilisateur,mdp);
			messages.add("Connexion etablie !");
			
			//Gestion des requetes
			st = co.createStatement();
			PreparedStatement ps = co.prepareStatement("INSERT INTO bdd_thomas.Joueurs (nom,prenom) VALUES (?, ?);");
			
			String paramNom = request.getParameter("nom");
			String paramPrenom = request.getParameter("prenom");
			
			ps.setString(1, paramNom);
			ps.setString(2, paramPrenom);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				messages.add("Insertion reussie");
			} else {
				messages.add("Insertion ratee ...");
			}
			
				
		} catch(SQLException e) {
			messages.add("Erreur dans la connexion ! <br />");			
		} finally {
			messages.add( "Fermeture de l'objet ResultSet." );
	        if ( rs != null ) {
	            try {
	                rs.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        messages.add( "Fermeture de l'objet Statement." );
	        if ( st != null ) {
	            try {
	                st.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
	        messages.add( "Fermeture de l'objet Connection." );
	        if ( co!= null ) {
	            try {
	                co.close();
	            } catch ( SQLException ignore ) {
	            }
	        }
		return messages;
		}
	}
	
	public List <String> GetJoueursFromDB(HttpServletRequest request) {
		//Chargement du driver JDBC
				try {
					//messages_2.add("Chargement du driver ...");
					Class.forName("com.mysql.jdbc.Driver");
					//messages_2.add("Driver charge !");
				} catch (ClassNotFoundException e) {
					//messages_2.add("Erreur lors du chargement du driver : " + e.getMessage());
				}
				
				String url_bdd="jdbc:mysql://localhost:9090/bdd_thomas";
				String utilisateur="admin";
				String mdp="database";
				Connection co = null;
				Statement st = null;
				ResultSet rs = null;
				
				try {
					//Connexion à la BDD
					//messages.add("Connexion a la BDD");
					co = DriverManager.getConnection(url_bdd,utilisateur,mdp);
					//messages.add("Connexion etablie !");
					
					//Gestion des requetes
					st = co.createStatement();
					@SuppressWarnings("static-access")
					PreparedStatement ps = co.prepareStatement("SELECT * FROM bdd_thomas.Joueurs;");
					
					rs = ps.executeQuery();
					int i = 1;
					//Traitement des resultats
					while (rs.next()) {
						String nomJoueur = rs.getString("nom");
						String prenomJoueur = rs.getString("prenom");
						Joueur j = new Joueur();
						j.set_nomJoueur(nomJoueur);
						j.set_prenomJoueur(prenomJoueur);
						j.set_numeroJoueur(i);
						joueurs_inscrits.add(j);
						i=i+1;
					}
					
					request.setAttribute("joueurs_inscrits", joueurs_inscrits);
					
						
				} catch(SQLException e) {
					//messages.add("Erreur dans la connexion ! <br />");			
				} finally {
					//messages.add( "Fermeture de l'objet ResultSet." );
			        if ( rs != null ) {
			            try {
			                rs.close();
			            } catch ( SQLException ignore ) {
			            }
			        }
			        //messages.add( "Fermeture de l'objet Statement." );
			        if ( st != null ) {
			            try {
			                st.close();
			            } catch ( SQLException ignore ) {
			            }
			        }
			        //messages.add( "Fermeture de l'objet Connection." );
			        if ( co!= null ) {
			            try {
			                co.close();
			            } catch ( SQLException ignore ) {
			            }
			        }
				}	
		return messages;
	}

}
