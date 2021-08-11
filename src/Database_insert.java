import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
 
public class Database_insert extends gym_dietTracker implements ActionListener{    
 
	private static final long serialVersionUID = 1L;

public boolean insertion() {
      try (
  
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/gym?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx"); 
 
         Statement stmt = conn.createStatement();
      ) {
    	 String sqlId = "select id from gym_table";
    	 ResultSet rsetId = stmt.executeQuery(sqlId);
    	 int idCtr = 0;
    	 while(rsetId.next()) {
    		 idCtr = rsetId.getInt("id");
    	 }
    	 idCtr++;
    	   
         String sqlInsert = "insert into gym_table values  " + 
				 "("+idCtr+", "+gym_dietTracker.username_signUp+", "+gym_dietTracker.password_signUp+", "+gym_dietTracker.firstName+", "+gym_dietTracker.surName+", "+gym_dietTracker.age+", "+gym_dietTracker.gender+", "+gym_dietTracker.height+", "+gym_dietTracker.weight+", "+gym_dietTracker.physicalActivity+", "+gym_dietTracker.trainingProgram+", "+gym_dietTracker.weightGainLoss+")";
         System.out.println("The SQL statement is: " + sqlInsert + "\n");  // Echo for debugging
         int countInserted = stmt.executeUpdate(sqlInsert);
         System.out.println(countInserted + " records inserted.\n");

         String strSelect = "select * from gym_table";
         System.out.println("The SQL statement is: " + strSelect + "\n"); 
         ResultSet rset = stmt.executeQuery(strSelect);
         Boolean duplicate = false;
        
         while(rset.next()) { 
            System.out.println(rset.getString("id") + ", "+ rset.getString("username") + ", "
                    + rset.getString("password") + ", "
                    + rset.getString("firstname") + ", "
                    + rset.getString("surname") + ", "
                    + rset.getString("age")+", "
                    + rset.getString("gender")+", "
                    + rset.getString("height")+", "
                    + rset.getString("weight")+", "
                    + rset.getString("physicalactivity")+", "
                    + rset.getString("trainingprogram")+", "
                    + rset.getString("weightgainloss"));
            	if(gym_dietTracker.usernameCheck.equals(rset.getString("username")) && idCtr != rset.getInt("id")) {
            		if(gym_dietTracker.passwordCheck.equals(rset.getString("password"))  && idCtr != rset.getInt("id")) {	
            			duplicate = true;
            		    break;
            	   }
            	}	
    	
         }
         if(duplicate == true) {
        		String delete = "delete from gym_table where id = "+idCtr;
                System.out.println("The SQL statement is: " + delete + "\n");  // Echo for debugging
                int countDeleted = stmt.executeUpdate(delete);
                System.out.println(countDeleted + " records deleted.\n");
    			return false;
         }
         
      } catch(SQLException ex) {
         ex.printStackTrace();
      }
      return true;
   }
   
   public boolean signIn_check(String username, String password) {
	   try (   
		         Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://localhost:3306/gym?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
		               "myuser", "xxxx"); 
		         Statement stmt = conn.createStatement();
		      ){
		   String strSelect = "select * from gym_table";
	       System.out.println("The SQL statement is: " + strSelect + "\n"); 
	       ResultSet rset = stmt.executeQuery(strSelect);
	       while(rset.next()) {
	    	   if(rset.getString("username").equals(username)) {
	    		   if(rset.getString("password").equals(password)){
	    			   gym_dietTracker.firstName = rset.getString("firstname");
	    			   gym_dietTracker.surName = rset.getString("surname");
	    			   gym_dietTracker.age = rset.getInt("age");
	    			   gym_dietTracker.gender = rset.getString("gender");
	    			   gym_dietTracker.height = rset.getDouble("height");
	    			   gym_dietTracker.weight = rset.getDouble("weight");
	    			   gym_dietTracker.physicalActivity = rset.getString("physicalactivity");
	    			   gym_dietTracker.trainingProgram = rset.getString("trainingprogram");
	    			   gym_dietTracker.weightGainLoss = rset.getDouble("weightgainloss");
	    			   return true;
	    		   }
	    	   }
	       }
	 
	       return false;
	   }catch(SQLException ex) {
		   ex.printStackTrace();
		   return false;
	   }

	   
   }
   
   public void update(double weight, double height, String PA, String TP, double WGL, String username, String password) {
		      try (
		       
		    	      Connection conn = DriverManager.getConnection(
		    	               "jdbc:mysql://localhost:3306/gym?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
		    	               "myuser", "xxxx");    
		 

		         Statement stmt = conn.createStatement();
		      ) {
	
		         String strUpdate = "update gym_table set weight = "+weight+", height = "+height+", physicalactivity = "+PA+", trainingprogram = "+TP+", weightgainloss = "+WGL+" where username = "+username+" and password = "+password;
		         System.out.println("The SQL statement is: " + strUpdate + "\n");  
		         int countUpdated = stmt.executeUpdate(strUpdate);
		         System.out.println(countUpdated + " records affected.\n");
		 
		   
		         String strSelect = "select * from gym_table where username = "+username+" and password = "+password;
		         System.out.println("The SQL statement is: " + strSelect + "\n"); 
		         ResultSet rset = stmt.executeQuery(strSelect);
		         while(rset.next()) { 
		             System.out.println(rset.getString("username") + ", "
		                     + rset.getString("password") + ", "
		                     + rset.getString("firstname") + ", "
		                     + rset.getString("surname") + ", "
		                     + rset.getString("age")+", "
		                     + rset.getString("gender")+", "
		                     + rset.getString("height")+", "
		                     + rset.getString("weight")+", "
		                     + rset.getString("physicalactivity")+", "
		                     + rset.getString("trainingprogram")+", "
		                     + rset.getString("weightgainloss"));
		          }
		      } catch(SQLException ex) {
		         ex.printStackTrace();
		      }  
		   }
   
   public void view() {
	   try (   
		         Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://localhost:3306/gym?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
		               "myuser", "xxxx"); 
		         Statement stmt = conn.createStatement();
		      )
	   {
	   String strSelect = "select * from gym_table";
       System.out.println("The SQL statement is: " + strSelect + "\n"); 
       ResultSet rset = stmt.executeQuery(strSelect);
       JTextArea ta_view = new JTextArea(40, 80);
		JPanel view_panel = new JPanel();
		JFrame view_frame = new JFrame("Gym Members");
		JButton btn_back = new JButton("Back");
		
		JScrollPane scroll = new JScrollPane (ta_view, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		view_panel.add(scroll);  view_panel.add(btn_back); 
		view_frame.add(view_panel);
		scroll.setVisible(true);
		
		view_frame.show(true);
		view_frame.setSize(1000, 800);
		ta_view.setText("");
      
       while(rset.next()) { 
          System.out.println(rset.getString("id") + ", "+ rset.getString("username") + ", "
                  + rset.getString("password") + ", "
                  + rset.getString("firstname") + ", "
                  + rset.getString("surname") + ", "
                  + rset.getString("age")+", "
                  + rset.getString("gender")+", "
                  + rset.getString("height")+", "
                  + rset.getString("weight")+", "
                  + rset.getString("physicalactivity")+", "
                  + rset.getString("trainingprogram")+", "
                  + rset.getString("weightgainloss"));
          ta_view.append("First name: "
                  + rset.getString("firstname") + ", Surname: "
                  + rset.getString("surname") + ", Age: "
                  + rset.getString("age")+", Gender: "
                  + rset.getString("gender")+", Height: "
                  + rset.getString("height")+", Weight: "
                  + rset.getString("weight")+", Physical activity: "
                  + rset.getString("physicalactivity")+", training program: "
                  + rset.getString("trainingprogram")+", weight gain/loss: "
                  + rset.getString("weightgainloss")+"\n");
   
       }
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				view_frame.getContentPane().removeAll();
				view_frame.repaint();
				view_frame.hide();
				mainframe.show();
				mainframe.setSize(400, 400);
			}
		});
	   }catch(SQLException ex) {
	         ex.printStackTrace();
	      }  
   }

@Override
public void itemStateChanged(ItemEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
   
}