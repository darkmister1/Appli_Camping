package com.petanque.beans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.petanque.beans.Joueur;

public class Doublette {
	//Attributs
	private Joueur joueur_1;
	private Joueur joueur_2;
	private int numero_doublette;
	private List<String> messages = new ArrayList<String>();
	private List<Doublette> doublettes = new ArrayList<Doublette>();
	
	//Setters
	public void set_joueur1(Joueur j) {
		this.joueur_1 = j;
	}
	public void set_joueur2(Joueur j) {
		this.joueur_2 = j;
	}
	public void set_numeroDoublette(int num) {
		this.numero_doublette = num;
	}
	
	//Getters
	public Joueur get_joueur1() {
		return this.joueur_1;
	}
	public Joueur get_joueur2() {
		return this.joueur_2;
	}
	public int get_numeroDoublette() {
		return this.numero_doublette;
	}
	
	public List <String> GetDoublettesFromDB(HttpServletRequest request) {
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
			PreparedStatement ps = co.prepareStatement("SELECT * FROM bdd_thomas.Joueurs ORDER BY RAND();");
			
			rs = ps.executeQuery();
			rs.last();
			int lignes = rs.getRow();
			rs.beforeFirst();
			int i = 1;
			int doub = 1;
			Doublette d = new Doublette();
			//Traitement des resultats
			while (rs.next()) {	
				Joueur j = new Joueur();
				if (i %2 == 0) {
					String nom_joueur2 = rs.getString("nom");
					String prenom_joueur2 = rs.getString("prenom");
					j.set_nomJoueur(nom_joueur2);
					j.set_prenomJoueur(prenom_joueur2);
					d.set_joueur2(j);
					d.set_numeroDoublette(doub);
					doublettes.add(d);
					i = i + 1;
					doub = doub + 1;					
					String query = "INSERT INTO bdd_thomas.Doublettes (nom1,prenom1,nom2,prenom2) VALUES (?,?,?,?);";
					ps = co.prepareStatement(query);
					ps.setString(1,d.get_joueur1().get_nomJoueur());
					ps.setString(2,d.get_joueur1().get_prenomJoueur());
					ps.setString(3, d.get_joueur2().get_nomJoueur());
					ps.setString(4, d.get_joueur2().get_prenomJoueur());
					ps.executeUpdate();
					d = new Doublette();
				} else {
					String nom_joueur1 = rs.getString("nom");
					String prenom_joueur1 = rs.getString("prenom");
					j.set_nomJoueur(nom_joueur1);
					j.set_prenomJoueur(prenom_joueur1);
					d.set_joueur1(j);
					i = i + 1;
				}
				
			}
			request.setAttribute("doublettes", doublettes);
			
				
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
