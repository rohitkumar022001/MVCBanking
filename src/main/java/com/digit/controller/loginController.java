package com.digit.controller;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.model.bankApp;
import com.mysql.cj.Session;

@WebServlet("/Login")
public class loginController extends HttpServlet {
	private Connection con;

	private PreparedStatement pstmt;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int cust_id = Integer.parseInt(req.getParameter("cust_id"));
		int pin = Integer.parseInt(req.getParameter("pin"));
		
		bankApp ba = new bankApp();
		ba.setCust_id(cust_id);

		ba.setPin(pin);
		
		boolean b = ba.login();
		HttpSession session=req.getSession(true);
		session.setAttribute("cust_id",ba.getCust_id());
		session.setAttribute("ifsc_code",ba.getIfsc_code());
		session.setAttribute("bank_name",ba.getBank_name());
		session.setAttribute("pin",ba.getPin());
		session.setAttribute("accno",ba.getAcc_no());
		if(b==true) {
			session.setAttribute("cust_name",ba.getCust_name());
			res.sendRedirect("/MVCBankApplications/HomePage.jsp");

		}

		else {

			res.sendRedirect("/MVCBankApplications/LoginFail.html");

		}
		

	}
}
