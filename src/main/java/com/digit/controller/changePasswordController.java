package com.digit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.model.bankApp;
@WebServlet("/ChangePassword")
public class changePasswordController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int pin=Integer.parseInt(req.getParameter("pin"));
		int npin=Integer.parseInt(req.getParameter("npin"));
		int cpin=Integer.parseInt(req.getParameter("cpin"));
		bankApp ba = new bankApp();
		ba.setPin(pin);
		
		ba.setNpin(npin);
		
		ba.setCpin(cpin);
		
		HttpSession session=req.getSession();
		int accno=(int)session.getAttribute("accno");
		ba.setAcc_no(accno);
		boolean b = ba.changePassword();
		
		if(b==true) {
			resp.sendRedirect("/MVCBankApplications/passwordSuccess.html");

		}

		else {

			resp.sendRedirect("/MVCBankApplications/PasswordFail.html");

		}
	}

}
