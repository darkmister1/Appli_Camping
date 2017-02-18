package com.petanque.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petanque.beans.Doublette;

/**
 * Servlet implementation class CreerDoublettes
 */
@WebServlet("/CreerDoublettes")
public class CreerDoublettes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Doublette d = new Doublette();
		List<String> messages = d.GetDoublettesFromDB(request);
		
		this.getServletContext().getRequestDispatcher("/creer_affiche_doublettes.jsp").forward(request, response);
	}

}
