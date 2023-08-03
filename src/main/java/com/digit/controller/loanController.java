package com.digit.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.model.bankApp;

@WebServlet("/loan")
public class loanController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		bankApp b = new bankApp();
		
		b.setLid( Integer.parseInt(req.getParameter("Lid")));
		
		ResultSet resultset = b.loan();
		
		try{
		if(resultset!=null) {
			 session.setAttribute("Lid", resultset.getInt("Lid"));
			 session.setAttribute("Ltype", resultset.getString("Ltype"));
			 session.setAttribute("Tenure", resultset.getInt("Tenure"));
			 session.setAttribute("Interest", resultset.getInt("Interest"));
			 session.setAttribute("Description", resultset.getString("Description"));
			resp.sendRedirect("/MVCBankApplications/LoanDetails.jsp");
		}
		else {
			resp.sendRedirect("/MVCBankApplications/LoanDetailsFail.jsp");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
}
	

}
