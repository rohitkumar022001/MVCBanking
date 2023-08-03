package com.digit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.model.bankApp;

@WebServlet("/CheckBalance")
public class balanceController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		
		bankApp ba = new bankApp();
		ba.setAcc_no(accno);
		
		boolean b = ba.balance();
		session.setAttribute("balance",ba.getBalance() );
		if(b==true) {
			resp.sendRedirect("/MVCBankApplications/Balance.jsp");
			
		}
		else {
			resp.sendRedirect("/MVCBankApplications/BalanceFail.html");
		}
	}

}
