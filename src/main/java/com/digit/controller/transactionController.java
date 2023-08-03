package com.digit.controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.digit.model.bankApp;

@WebServlet("/Transfer")
public class transactionController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
	
		int cid = (int) session.getAttribute("cust_id");
		String bname = (String) session.getAttribute("bank_name");
		int ifsc =  (int)session.getAttribute("ifsc_code");
		int sender_ac = Integer.parseInt(req.getParameter("sender_accno"));
		int receiver_ifsc = Integer.parseInt(req.getParameter("receiver_ifsc"));
		int receiver_acc = Integer.parseInt(req.getParameter("receiver_accno"));
		int pin = (int) session.getAttribute("pin");
		int amount = Integer.parseInt(req.getParameter("amount"));
		bankApp ba=new bankApp();
		ba.setCust_id(cid);
		ba.setAcc_no(sender_ac);
		ba.setReceiver_acc(receiver_acc);
		ba.setReceiver_ifsc(receiver_ifsc);
		ba.setAcc_no(sender_ac);
		ba.setBank_name(bname);
		ba.setBalance(amount);
		ba.setPin(pin);
		ba.setIfsc_code(ifsc);
		session.setAttribute("tid",ba.getTransaction_id());
		boolean b = ba.transaction();
		if(b==true) {
			resp.sendRedirect("/MVCBankApplications/tansferSuccess.jsp");
		}
		else {
			resp.sendRedirect("/MVCBankApplications/transferfail.jsp");
		}
	}

}
