package com.digit.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

public class bankApp {
	int bank_id;
    String bank_name;
    int ifsc_code;
    int acc_no;
    int receiver_ifsc;
    int lid;
    /**
	 * @return the lid
	 */
	public int getLid() {
		return lid;
	}



	/**
	 * @param lid the lid to set
	 */
	public void setLid(int lid) {
		this.lid = lid;
	}



	/**
	 * @return the transaction_id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}



	/**
	 * @param transaction_id the transaction_id to set
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	int receiver_acc;
    /**
	 * @return the receiver_ifsc
	 */
    Random random = new Random();
	String transaction_id = (100000 + random.nextInt(900000)) + "";
	
	public int getReceiver_ifsc() {
		return receiver_ifsc;
	}



	/**
	 * @param receiver_ifsc the receiver_ifsc to set
	 */
	public void setReceiver_ifsc(int receiver_ifsc) {
		this.receiver_ifsc = receiver_ifsc;
	}



	/**
	 * @return the receiver_acc
	 */
	public int getReceiver_acc() {
		return receiver_acc;
	}



	/**
	 * @param receiver_acc the receiver_acc to set
	 */
	public void setReceiver_acc(int receiver_acc) {
		this.receiver_acc = receiver_acc;
	}
	int pin;
    int cust_id;
    String cust_name;
    int balance;
    String email_id;
    long phone;
    int npin;
    int cpin;
    
    /**
	 * @return the npin
	 */
	public int getNpin() {
		return npin;
	}



	/**
	 * @param npin the npin to set
	 */
	public void setNpin(int npin) {
		this.npin = npin;
	}



	/**
	 * @return the cpin
	 */
	public int getCpin() {
		return cpin;
	}



	/**
	 * @param cpin the cpin to set
	 */
	public void setCpin(int cpin) {
		this.cpin = cpin;
	}
	private Connection con;
    private PreparedStatement psta;
	private PreparedStatement pstmt;
	private ResultSet resultset;
    public  bankApp() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/bankapp";
            String user = "root";
            String pwd = "Welcome@123";
            con = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

 

    public int getBank_id() {
        return bank_id;
    }

 

    public void setBank_id(int bank_id) {
        this.bank_id = bank_id;
    }

 

    public String getBank_name() {
        return bank_name;
    }

 

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

 

    public int getIfsc_code() {
        return ifsc_code;
    }

 

    public void setIfsc_code(int ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

 

    public int getAcc_no() {
        return acc_no;
    }

 

    public void setAcc_no(int acc_no) {
        this.acc_no = acc_no;
    }

 

    public int getPin() {
        return pin;
    }

 

    public void setPin(int pin) {
        this.pin = pin;
    }

 

    public int getCust_id() {
        return cust_id;
    }

 

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

 

    public String getCust_name() {
        return cust_name;
    }

 

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

 

    public int getBalance() {
        return balance;
    }

 

    public void setBalance(int balance) {
        this.balance = balance;
    }

 

    public String getEmail_id() {
        return email_id;
    }

 

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

 

    public long getPhone() {
        return phone;
    }

 

    public void setPhone(long phone) {
        this.phone = phone;
    }

 

    public boolean register() {

 

        try {
            String sql = "insert into register values(?,?,?,?,?,?,?,?,?,?)";

 

            psta = con.prepareStatement(sql);
            psta.setInt(1, bank_id);
            psta.setString(2, bank_name);
            psta.setInt(3, ifsc_code);
            psta.setInt(4, acc_no);
            psta.setInt(5, pin);
            psta.setInt(6, cust_id);
            psta.setString(7, cust_name);
            psta.setInt(8, balance);
            psta.setString(9, email_id);
            psta.setLong(10, phone);
            int x = psta.executeUpdate();
            if (x > 0) {
                return true;
            } else {
                return false;
            }

 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

 

    }
    public boolean login() {

    	

        try {
        	pstmt = con.prepareStatement("select * from register where cust_id = ? and pin = ?");
    		pstmt.setInt(1, cust_id);
    		pstmt.setInt(2, pin);

    		ResultSet result = pstmt.executeQuery();
    		
    
    		
    		if (result.next()) {
    			this.setCust_id(result.getInt("cust_id"));
    			this.setAcc_no(result.getInt("accno"));
    			this.setPin(result.getInt("pin"));
    			this.setIfsc_code(result.getInt("ifsc_code"));
    			this.setBank_name(result.getString("bank_name"));
    			this.setCust_name(result.getString("cust_name"));
    			 return true;

			}

			else {

				 return false;

			}
          
 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

 

    }
    public boolean changePassword() {
    	try {
    	
			pstmt = con.prepareStatement("update register set pin=? where accno=? and pin=?");
			pstmt.setInt(1, npin);
			
			pstmt.setInt(2, getAcc_no());
			
			pstmt.setInt(3, pin);
			
			int x = pstmt.executeUpdate();
			
			if (x>0 && (npin==cpin)) {
				return true;

			}

			else {

				return false;

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//            writer.write();

		}
    	return false;
    }
    public boolean balance() {
    	try {


			pstmt = con.prepareStatement("select balance from register where accno=?");
			pstmt.setInt(1, acc_no);
			
			ResultSet result = pstmt.executeQuery();

			if (result.next()) {
				this.setBalance(result.getInt("balance"));
				return true;

			}

			else {

				return false;

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//            writer.write();

		}
    	return false;
    }
public boolean transaction() {
	try {
		
		pstmt = con.prepareStatement("select *from register where cust_id=? and ifsc_code=? and accno=? and pin=?");
		pstmt.setInt(1, cust_id);
		pstmt.setInt(2, ifsc_code);
		pstmt.setInt(3, acc_no);
		pstmt.setInt(4, pin);
		ResultSet r = pstmt.executeQuery();
		if (r.next() == true) {
			pstmt = con.prepareStatement("select *from register where ifsc_code=? and accno=?");
			pstmt.setInt(1, receiver_ifsc);
			pstmt.setInt(2, receiver_acc);
			ResultSet r1 = pstmt.executeQuery();
			if (r1.next() == true) {
				pstmt = con.prepareStatement("select balance from register where accno=?");
				pstmt.setInt(1, acc_no);
				ResultSet r2 = pstmt.executeQuery();
				r2.next();
				int bal = r2.getInt("balance");
				if (bal > balance) {
					pstmt = con.prepareStatement("update register set balance=balance-? where accno=?");
					pstmt.setInt(1, balance);
					pstmt.setInt(2, acc_no);
					int x1 = pstmt.executeUpdate();
					
					if (x1 > 0) {
						pstmt = con.prepareStatement("update register set balance=balance+? where accno=?");
						pstmt.setInt(1, balance);
						pstmt.setInt(2, receiver_acc);
						int x2 = pstmt.executeUpdate();
						if (x2 > 0) {
							pstmt = con.prepareStatement("insert into transfer values(?,?,?,?,?,?,?,?)");
							pstmt.setInt(1, cust_id);
							pstmt.setString(2, cust_name);
							pstmt.setInt(3, ifsc_code);
							pstmt.setInt(4, acc_no);
							pstmt.setInt(5, receiver_ifsc);
							pstmt.setInt(6, receiver_acc);
							pstmt.setInt(7, balance);
							pstmt.setString(8, transaction_id);
							int x3 = pstmt.executeUpdate();
							if (x3 > 0) {
								return true;
								//resp.sendRedirect("/Bank_Application/tansferSuccess.jsp");

							} else {
								return false;
								//resp.sendRedirect("/Bank_Application/transferfail.jsp");
							}
						} else {
							return false;
							//resp.sendRedirect("/Bank_Application/transferfail.jsp");
						}
						
					} else {
						return false;
						//resp.sendRedirect("/Bank_Application/transferfail.jsp");
					}
				} else {
					return false;
					//resp.sendRedirect("/Bank_Application/transferfail.jsp");
				}

			} else {
				return false;
				//resp.sendRedirect("/Bank_Application/transferfail.jsp");
			}
		} else {
			return false;
			//resp.sendRedirect("/Bank_Application/transferfail.jsp");
		}

	} catch (Exception e) {

		e.printStackTrace();
	}

return false;
    
}
public ResultSet loan() {
	

	try {
		String sql = "select * from loan where Lid=? ";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, lid);
		 resultset = pstmt.executeQuery();
		if(resultset.next() == true){
			
			 return resultset;
			 
		}
		else {
			return null;
			
		}
		

	} catch(Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
