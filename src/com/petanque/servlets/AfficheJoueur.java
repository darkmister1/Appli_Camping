package com.petanque.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petanque.beans.Joueur;

/**
 * Servlet implementation class AfficheJoueur
 */
@WebServlet("/AfficheJoueur")
public class AfficheJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Joueur j = new Joueur();
		List<String> messages = j.GetJoueursFromDB(request);
		
		this.getServletContext().getRequestDispatcher("/affiche_joueur.jsp").forward(request, response);
	}
}
