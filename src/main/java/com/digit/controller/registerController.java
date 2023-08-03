package com.digit.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.model.bankApp;
@WebServlet("/Register")
public class registerController extends HttpServlet {
	private Connection con;

	private PreparedStatement pstmt;

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int bank_id = Integer.parseInt(req.getParameter("bank_id"));

		String bank_name = req.getParameter("bank_name");

		int ifsc_code = Integer.parseInt(req.getParameter("ifsc_code"));

		int accno = Integer.parseInt(req.getParameter("accno"));

		int pin = Integer.parseInt(req.getParameter("pin"));

		int cust_id = Integer.parseInt(req.getParameter("cust_id"));

		String cust_name = req.getParameter("cust_name");

		int balance = Integer.parseInt(req.getParameter("balance"));

		String email = req.getParameter("email");

		long phone = Long.parseLong(req.getParameter("phone"));

		bankApp ba=new bankApp();

		ba.setBank_id(bank_id);

		ba.setBank_name(bank_name);

		ba.setIfsc_code(ifsc_code);

		ba.setAcc_no(accno);

		ba.setPin(pin);

		ba.setCust_id(cust_id);

		ba.setBalance(balance);

		ba.setCust_name(cust_name);

		ba.setEmail_id(email);

		ba.setPhone(phone);
		

		boolean b = ba.register();

		if(b==true) {

		resp.sendRedirect("/MVCBankApplications/RegisterSuccess.html");

		 

		}

		else {

		resp.sendRedirect("/MVCBankApplications/RegisterFail.html");

		 

		}
		

		}
	

	}

