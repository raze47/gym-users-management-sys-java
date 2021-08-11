
import javax.swing.*;

import lib.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class gym_dietTracker extends JFrame implements ActionListener, ItemListener{
	QueryDB qdb = new QueryDB();
	//CreateDB cqd = new CreateDB();
	protected static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	protected static final String JDBC_URL = "jdbc:derby:GymDatabase;create=false";
	static Database_insert dbi;
	
	static JFrame mainframe = new JFrame("Main Menu");
	static JFrame signInUp_frame = new JFrame("Login");
	static JFrame loggedIn_frame = new JFrame("Account");
	static JFrame update_frame = new JFrame("Update Info");
	static JPanel main_panel = new JPanel();
	static JButton btn_login = new JButton("Sign in/Sign up");
	static JButton btn_about = new JButton("About");
	static JButton btn_viewGymMembers = new JButton("Gym members");
	static JButton btn_classifications = new JButton("Classifications");
	

	static JTextField txt_username = new JTextField(10);
	static JTextField txt_password = new JTextField(10);
	static JTextArea txtArea_gymMembers = new JTextArea(50, 100);
	
	static String username="", password="";
	static String firstName="", surName="", gender="",  physicalActivity="", trainingProgram="";
	static String password_signUp="", username_signUp="";
	static String usernameCheck = "", passwordCheck = "";

	static String[] usernames = new String[500];
	static String[] passwords = new String[500];
	static int memberCount=0, age=0;
	static double height=0, weight=0, weightGainLoss=-1 ;
	
	static int genderPass = -1, heightPass = -1, weightPass = -1, physicalActivityPass = -1, trainingProgramPass = -1,
			weightGainLossPass = -1, weightPass2 = -1;
	static int newPhysicalActivityPass = -1, newTrainingProgramPass = -1;
	static double weightGoal = 0, needCalories = 0, tpCalorie = 0,	PA = 0, bmi = 0;

	
	
	
	public static void main(String args[]) {
		gym_dietTrackerStart();
	}
	
	public static void init_mainframe() {
		mainframe.setLayout(new BorderLayout());
		main_panel.setLayout(new GridLayout(2, 2));
		
		main_panel.add(btn_login);
		main_panel.add(btn_about);
		main_panel.add(btn_viewGymMembers);
		main_panel.add(btn_classifications);
	
		mainframe.add(main_panel, BorderLayout.CENTER);

		mainframe.setVisible(true);
		mainframe.setSize(400, 400);
	}
	public static void gym_dietTrackerStart() {
		init_mainframe();
	
		btn_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				signInUp();
			}
		});
		btn_about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				about();
			}
		});
		btn_classifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				classifications();
			}
		});
		btn_viewGymMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				viewGymMembers();
			}
		});
		
	}
	public static void viewGymMembers() {
		dbi = new Database_insert();
		mainframe.hide();
		dbi.view();
	
	
	}
	public static void classifications() {
		JTextArea ta_class = new JTextArea(40, 50);
		JPanel class_panel = new JPanel();
		JFrame class_frame = new JFrame("Classifications");
		JButton btn_back = new JButton("Back");
		
		JScrollPane scroll = new JScrollPane (ta_class, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		class_panel.add(scroll);  class_panel.add(btn_back); 
		class_frame.add(class_panel);
		scroll.setVisible(true);
		mainframe.hide();
		class_frame.show(true);
		class_frame.setSize(1000, 800);
		ta_class.setText("BMI is equal to or less than 18.5 (Underweight)\n\n" + 
				"A lean BMI can indicate that your weight maybe too low. You should consult your physician\n to determine if you should gain weight,\n as low body mass can decrease your body's immune system, which could\n lead to ilness such as disappearance of periods (women), bone loss, \nmalnutrition and other conditions. The lower your\n BMI the greater these risks become."+
				"\n\nBMI is between 18.5 and 24.9 (Normal Weight)\n\n People whose BMI is within 18.5 to 24.9 possess the ideal amount of body weight,\n associated with living longest, the lowest incidence of serious ilness, as well as being\n perceived as more physically attractive than people with BMI in higher or lower ranges. However,\n it may be a good idea to check your Waist Circumference and\n keep it within the recommended limits."+
				"\n\nBMI is between 25 and 29.9 (Overweight)\n\n People falling in this BMI range are considered overweight and would benefit from\n finding healthy ways to lower their weight, such as diet\n and exercise. Individuals who fall in this range\n are at increased risk for a variety of ilnesses. If your BMI is 27-29.99\n your risk of health problems becomes higher. In a recent\n study an increased rate of blood pressure, diabetes and\n heart disease was recorded at 27.3 for women and 27.8 for men.\n It may be a good idea to check your Waist Circumference and compare it\n with the recommended limits."+
				"\n\nBMI is between 30-34.99 (Obese Class 1)\n\n Individuals with a BMI of 30-34.99 are in a physically unhealthy condition,\n which puts them at risk for serious ilnesses such as heart disease,\n diabetes, high blood pressure, gall bladder disease, and some\n cancers. This holds especially true if you have a larger than\n recommended Waist Size. These people would benefit greatly by\n modifying their lifestyle. Ideally, see your doctor and consider\n reducing your weight by 5-10 percent. Such a weight\n reduction will result in considerable health improvements."+
				"\n\nBMI is between 35-39.99 (Obese Class 2)\n\n If you have a BMI of 35-39.99 your risk of weight-related health problems\n and even death, is severe. See your doctor and\n reduce your weight to a lower BMI."+
				"\n\nBMI is over 40 (Obese Class 3 : Morbid Obesity)\n\n With a BMI of 40+ you have an extremely high risk of weight-related\n disease and premature death. Indeed, you may have already been suffering\n from a weight-related condition. For the sake of your health it is very\n important to see your doctor and\n get specialists help for your condition.");
	
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				class_frame.getContentPane().removeAll();
				class_frame.repaint();
				class_frame.hide();
				mainframe.show(true);
				mainframe.setSize(400, 400);
			}
		});
	
	
	}
	
	public static void about() {
		JTextArea ta_about = new JTextArea(20, 1);
		JPanel about_panel = new JPanel();
		JFrame about_frame = new JFrame("About");
		JButton btn_back = new JButton("Back");
		ta_about.setText("This project is used for a gym database.\n It can store a member's name, age, gender, height, weight, and goals.\n It can be useful to regularly update their progress into the database.\n The members can also be aware of their recommended calorie intake and body mass index status");
		about_panel.add(ta_about); about_panel.add(btn_back);
		about_frame.add(about_panel);
		mainframe.hide();
		about_frame.show(true);
		about_frame.setSize(560, 400);
		
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				about_frame.getContentPane().removeAll();
				about_frame.repaint();
				about_frame.hide();
				mainframe.show(true);
				mainframe.setSize(400, 400);
			}
		});
		
	}

	public static void signInUp() {
		JLabel lbl_username = new JLabel("Username: ");
		JLabel lbl_password = new JLabel("Password: ");
		JButton btn_signIn = new JButton("Sign in");
		JButton btn_signUp = new JButton("Sign up");
		JButton btn_back = new JButton("Back");
		JPanel signInUp_panel = new JPanel();

		
		signInUp_panel.setLayout(new GridLayout(4, 2));
		signInUp_frame.setLayout(new GridLayout(4, 2));
		
		signInUp_panel.add(lbl_username); 	signInUp_panel.add(txt_username);
		signInUp_panel.add(lbl_password);   signInUp_panel.add(txt_password);
		signInUp_panel.add(btn_signIn); signInUp_panel.add(btn_signUp);
		signInUp_panel.add(btn_back);
		
		
		signInUp_frame.add(signInUp_panel);
		mainframe.hide();
		signInUp_frame.show(true);
		signInUp_frame.setSize(400, 400);
		
		btn_signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				username = txt_username.getText();
				password = txt_password.getText();
				dbi = new Database_insert();
				if(username.equals("") || password.equals("")) {
					JOptionPane.showMessageDialog(null, "Please Input All Fields", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(dbi.signIn_check(username, password) == true){
					   txt_username.setText("");
					   txt_password.setText("");
				
						
						JLabel lbl_name = new JLabel("Name: ");
						JLabel lbl_currentPhysicalActivity = new JLabel("Current physical activity: ");
						JLabel lbl_age = new JLabel("Age: ");
						JLabel lbl_gender = new JLabel("Gender: ");
						JLabel lbl_height = new JLabel("Height: ");
						JLabel lbl_currentWeight = new JLabel("Weight: ");
						JLabel lbl_trainingProgram = new JLabel("Training Program: ");
						JLabel lbl_weightGoal = new JLabel("Weight target: ");
						JLabel lbl_remainingWeight = new JLabel("Remaining: ");
						JLabel lbl_status = new JLabel("Status: ");
						JLabel lbl_classification = new JLabel("Classification: ");
						JLabel lbl_bmi = new JLabel("Body mass index: ");
						JTextField txt_name = new JTextField(10);
						JTextField txt_currentPhysicalActivity = new JTextField(10);
						JTextField txt_height = new JTextField(10);
						JTextField txt_currentWeight = new JTextField(10);
						JTextField txt_remainingWeight = new JTextField(10);
						JTextField txt_weightGoal = new JTextField(10);
						JTextField txt_trainingProgram = new JTextField(10);
						JTextField txt_needCalorie = new JTextField(10);
						JTextField txt_tpCalorie = new JTextField(10);
						JTextField txt_status = new JTextField(10);
						JTextField txt_classification = new JTextField(10);
						JTextField txt_bmi = new JTextField(10);
						JTextField txt_gender = new JTextField(10);
						JTextField txt_age = new JTextField(10);
						
						JButton btn_needCalorie = new JButton("Daily Calories for maintenance");
						JButton btn_back = new JButton("Back");
						JButton btn_update = new JButton("Update");
						JButton btn_tpCalorie = new JButton("Calories of gain/loss");
						if(trainingProgram.equals("Weight Gain")){		
							weightGoal = weightGainLoss + weight; 
						}
						else if(trainingProgram.equals("Weight Loss")) {
							weightGoal = weight - weightGainLoss;
			        	}
						else {
						
							txt_tpCalorie.setText("");
							weightGoal = weight;
						}
						
						JPanel loggedIn_panel = new JPanel();
						//BMI = (Weight in Kilograms / (Height in Meters x Height in Meters))
						loggedIn_panel.add(lbl_name); loggedIn_panel.add(txt_name);
						loggedIn_panel.add(lbl_age); loggedIn_panel.add(txt_age);
						loggedIn_panel.add(lbl_gender); loggedIn_panel.add(txt_gender);
						loggedIn_panel.add(lbl_currentPhysicalActivity); loggedIn_panel.add(txt_currentPhysicalActivity);
						loggedIn_panel.add(lbl_height); loggedIn_panel.add(txt_height);
						loggedIn_panel.add(lbl_currentWeight); loggedIn_panel.add(txt_currentWeight);
						loggedIn_panel.add(lbl_trainingProgram); loggedIn_panel.add(txt_trainingProgram);
						loggedIn_panel.add(lbl_weightGoal); loggedIn_panel.add(txt_weightGoal);
						loggedIn_panel.add(lbl_remainingWeight); loggedIn_panel.add(txt_remainingWeight);
						loggedIn_panel.add(btn_needCalorie); loggedIn_panel.add(txt_needCalorie);
						loggedIn_panel.add(btn_tpCalorie); loggedIn_panel.add(txt_tpCalorie);
						loggedIn_panel.add(lbl_status); loggedIn_panel.add(txt_status);
						loggedIn_panel.add(lbl_bmi); loggedIn_panel.add(txt_bmi);
						loggedIn_panel.add(lbl_classification); loggedIn_panel.add(txt_classification);
						loggedIn_panel.add(btn_back); loggedIn_panel.add(btn_update);
						
						loggedIn_frame.setLayout(new BorderLayout());
						loggedIn_frame.add(loggedIn_panel, BorderLayout.CENTER); 
						loggedIn_panel.setLayout(new GridLayout(15, 2));
					    loggedIn_frame.add(loggedIn_panel);
						signInUp_frame.hide();
						loggedIn_frame.show(true);
						loggedIn_frame.setSize(400, 400);
						
						txt_name.setEditable(false);
						txt_currentPhysicalActivity.setEditable(false);
						txt_height.setEditable(false);
						txt_currentWeight.setEditable(false);	
						txt_trainingProgram.setEditable(false);
						txt_weightGoal.setEditable(false);
						txt_remainingWeight.setEditable(false);
						txt_needCalorie.setEditable(false);
						txt_tpCalorie.setEditable(false);
						txt_status.setEditable(false);
						txt_classification.setEditable(false);
						txt_bmi.setEditable(false);
						txt_gender.setEditable(false);
						txt_age.setEditable(false);
						txt_gender.setText(""+gender);
						txt_age.setText(""+age);
						if(weightGoal != weight)
							txt_status.setText("On-going");
						else
							txt_status.setText("Completed!");
						
						txt_name.setText(firstName+" "+surName);
						if(physicalActivity.equals("Very Active(More than 6 times a week)"))
							physicalActivity = "Very Active";
						txt_currentPhysicalActivity.setText(physicalActivity);
						txt_height.setText(""+height);
						txt_currentWeight.setText(""+weight);
						txt_trainingProgram.setText(""+trainingProgram);

						txt_weightGoal.setText(""+weightGoal);
						txt_remainingWeight.setText(""+weightGainLoss);
						/*18.5 or less	Underweight	Info	Treatment
							18.5 to 24.99	Normal Weight	Info	Treatment
							25 to 29.99	Overweight	Info	Treatment
							30 to 34.99	Obesity (Class 1)	Info	Treatment
							35 to 39.99	Obesity (Class 2)	Info	Treatment
							40 or greater	Morbid Obesity	Info	Treatment

*/
				    	height = height/100;
				    	bmi = weight/(height*height);
				    	if(bmi <= 18.5)
				    		txt_classification.setText("Underweight");
				    	else if(bmi > 18.5 && bmi <= 24.99)
				    		txt_classification.setText("Normal");
				    	else if(bmi > 24.99 && bmi <= 29.99)
				    		txt_classification.setText("Overweight");
				    	else if(bmi > 29.99 && bmi <=34.99)
				    		txt_classification.setText("Obesity (Class 1)");
				    	else if(bmi > 34.99 && bmi <=39.99)
				    		txt_classification.setText("Obesity (Class 2)");
				    	else
				    		txt_classification.setText("Morbid Obesity");
				    	txt_bmi.setText(""+bmi);
						
						btn_back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								loggedIn_frame.getContentPane().removeAll();
								loggedIn_frame.repaint();
								loggedIn_frame.hide();
								signInUp_frame.show(true);
								signInUp_frame.setSize(400, 400);
							}
						});
						btn_needCalorie.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								//TEE for men  = 864 - 9.72 × age + PA × (14.2 × weight + 503 × height)
								//TEE for women  = 387 - 7.31 × age + PA × (10.9 × weight + 660.7 × height)
								/*               M       F
								    Sedentary 	1.0 	1.0
									Low active 	1.12 	1.14
									Active   	1.27 	1.27
									Very active 1.54 	1.45 */
					
						

								if(gender.equals("MALE")) {
									if(physicalActivity.equals("Sedentary(No exercise)"))
										PA = 1.0;
									else if(physicalActivity.equals("Low Active(1-3 times a week)"))
										PA = 1.12;
									else if(physicalActivity.equals("Active(4-6 times a week"))
										PA = 1.27;
									else
										PA = 1.54;		
									needCalories = 864 - 9.72 * (double)age + PA *(14.2 * weight + 503 * height);
								}
								else {
									if(physicalActivity.equals("Sedentary(No exercise)"))
										PA = 1.0;
									else if(physicalActivity.equals("Low Active(1-3 times a week)"))
										PA = 1.14;
									else if(physicalActivity.equals("Active(4-6 times a week"))
										PA = 1.27;
									else
										PA = 1.45;							
									needCalories = 387 - 7.31 * (double)age + PA  *(10.9 * weight + 660.7 * height);
								}
								txt_needCalorie.setText(""+needCalories);
							}
						});
						btn_tpCalorie.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
						
							if(gender.equals("MALE")) {
								if(physicalActivity.equals("Sedentary(No exercise)"))
									PA = 1.0;
								else if(physicalActivity.equals("Low Active(1-3 times a week)"))
									PA = 1.12;
								else if(physicalActivity.equals("Active(4-6 times a week"))
									PA = 1.27;
								else
									PA = 1.54;
							
									tpCalorie =  864 - 9.72 * (double)age + PA *(14.2 * weightGoal + 503 * height);
							}
							else {
								if(physicalActivity.equals("Sedentary(No exercise)"))
									PA = 1.0;
								else if(physicalActivity.equals("Low Active(1-3 times a week)"))
									PA = 1.14;
								else if(physicalActivity.equals("Active(4-6 times a week"))
									PA = 1.27;
								else
									PA = 1.45;
								tpCalorie = 387 - 7.31 * (double)age + PA  *(10.9 * weightGoal + 660.7 * height);
							}
							txt_tpCalorie.setText(""+tpCalorie);
						 }
						});	
						btn_update.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {

							    loggedIn_frame.getContentPane().removeAll();
								loggedIn_frame.repaint();
								loggedIn_frame.hide();
						
								update_frame.show(true);
								update_frame.setSize(400, 400);
								JPanel update_panel = new JPanel();
								JLabel lbl_weight = new JLabel("Weight: ");
								JLabel lbl_height = new JLabel("Height: ");
								JLabel lbl_trainingProgam = new JLabel("Training Program");
								JLabel lbl_physicalActivity= new JLabel("Physical Activity");
								JLabel lbl_weightGoal = new JLabel("Weight to gain/lose: ");
								JTextField txt_weight = new JTextField(10);
								JTextField txt_height = new JTextField(10);
								JTextField txt_weightGoal = new JTextField(10);
								JTextField txt_empty = new JTextField(10);
						
								
								JButton btn_updateNow = new JButton("Update");
								String activity[] = {"SELECT","Sedentary(No exercise)", "Low Active(1-3 times a week)", "Active(4-6 times a week)", "Very Active(More than 6 times a week)"};
								String training[] = {"SELECT", "Weight Maintenance", "Weight Loss", "Weight Gain"};
								JComboBox physical_activity_up = new JComboBox(activity);
								JComboBox training_program_up = new JComboBox(training);
							    
								update_panel.add(lbl_weight); update_panel.add(txt_weight);
							    update_panel.add(lbl_height); update_panel.add(txt_height);
							    update_panel.add(lbl_physicalActivity); update_panel.add(physical_activity_up);
							    update_panel.add(lbl_trainingProgram); update_panel.add(training_program_up);
							    update_panel.add(lbl_weightGoal); update_panel.add(txt_weightGoal);
							    update_panel.add(btn_updateNow); update_panel.add(txt_empty);
							    txt_empty.setEditable(false);
					
								update_frame.setLayout(new BorderLayout());
								update_frame.add(update_panel, BorderLayout.CENTER); 
								update_panel.setLayout(new GridLayout(6, 2));
							    update_frame.add(update_panel);
							    height = height*100;
							 
							   	txt_weightGoal.setEditable(false);
							   	

								physical_activity_up.addItemListener(new ItemListener() {
									public void itemStateChanged(ItemEvent ie) {
										if(physical_activity_up.getSelectedIndex() == 0) {
											newPhysicalActivityPass = 0;
								
										}
										else if(physical_activity_up.getSelectedIndex() == 1) {
											newPhysicalActivityPass = 1;
									
										}
										else if(physical_activity_up.getSelectedIndex() == 2) {
											newPhysicalActivityPass = 2;
										
										}
										else if(physical_activity_up.getSelectedIndex() == 3){
											newPhysicalActivityPass = 3;
						
										}
										else {
											newPhysicalActivityPass = 4;
										}
									}
								});
								
								training_program_up.addItemListener(new ItemListener() {
									public void itemStateChanged(ItemEvent ie) {
										if(training_program_up.getSelectedIndex() == 0) {
											newTrainingProgramPass = 0;

										}
										else if(training_program_up.getSelectedIndex() == 1) {
											newTrainingProgramPass = 1;
											txt_weightGoal.setEditable(false);

										}
										else if(training_program_up.getSelectedIndex() == 2) {
											newTrainingProgramPass = 2;
											txt_weightGoal.setEditable(true);

										}
					
										else {
											newTrainingProgramPass = 3;
											txt_weightGoal.setEditable(true);

										}
									}
								});
								////////////
								
							   
							
								
								btn_updateNow.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent ae) {
								
										dbi = new Database_insert();
										if(weight == -1 || height == -1 || weightGainLoss == -1 || physicalActivity.equals("") || trainingProgram.equals(""))
											JOptionPane.showMessageDialog(null, "Please Input All Fields", "Error", JOptionPane.ERROR_MESSAGE);
										else {
											if(newPhysicalActivityPass == 0) {
												physicalActivity = "";
											}
											else if(newPhysicalActivityPass == 1) {
												physicalActivity ="Sedentary(No exercise)";
											}
											else if(newPhysicalActivityPass == 2) {
												physicalActivity = "Low Active(1-3 times a week)";
											}
											else if(newPhysicalActivityPass == 3) {
												physicalActivity ="Active(4-6 times a week)";
											}
											else if(newPhysicalActivityPass == 4){
												physicalActivity = "Very Active(More than 6 times a week)";
											}
											
											if(newTrainingProgramPass == 0) {
												trainingProgram="";
												txt_weightGoal.setEditable(false);
											}
											else if(newTrainingProgramPass == 1) {
												trainingProgram = "Weight Maintenance";
												weightGainLoss = 0;
												txt_weightGoal.setEditable(false);
											}
											else if(newTrainingProgramPass == 2) {
												trainingProgram = "Weight Loss";
												weightGainLoss = -1;
												weightGainLoss = Double.parseDouble(txt_weightGoal.getText());
												
											}
											else if(newTrainingProgramPass == 3){
												trainingProgram="Weight Gain";
												weightGainLoss = -1;
												weightGainLoss = Double.parseDouble(txt_weightGoal.getText());
											}
											/////////////////
											weight = -1;
											height = -1;
											
											weight = Double.parseDouble(txt_weight.getText());
											height = Double.parseDouble(txt_height.getText());
											JOptionPane.showMessageDialog(null, "Update complete", "Update", JOptionPane.INFORMATION_MESSAGE);
											physicalActivity = "'"+physicalActivity+"'";
											trainingProgram = "'"+trainingProgram+"'";
											username = "'"+username+"'";
											password = "'"+password+"'";
											System.out.println(newPhysicalActivityPass+" "+newTrainingProgramPass);
											System.out.println(weight+" "+height+" "+ physicalActivity+" "+trainingProgram+" "+weightGainLoss);
											dbi.update(weight, height, physicalActivity, trainingProgram, weightGainLoss, username, password);
											update_frame.hide();
											signInUp_frame.show(true);
											signInUp_frame.setSize(400, 400);
											txt_username.setText("");
											txt_password.setText("");
										    txt_weight.setText("");
										   	txt_height.setText("");
										   	txt_weightGoal.setText("");
										}
									}
								});
								
							}
						});
					}
				else {
					txt_username.setText("");
					txt_password.setText("");
					JOptionPane.showMessageDialog(null, "Incorrect Inputs!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			
				}
			
			}
		);
		////////
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txt_username.setText("");
				txt_password.setText("");
				JFrame signUp_frame = new JFrame();
				JPanel signUp_panel = new JPanel();
				String genders[] = {"SELECT", "MALE", "FEMALE"};

				String activity[] = {"SELECT","Sedentary(No exercise)", "Low Active(1-3 times a week)", "Active(4-6 times a week)", "Very Active(More than 6 times a week)"};
				String training[] = {"SELECT", "Weight Maintenance", "Weight Loss", "Weight Gain"};
				JComboBox combo_gender = new JComboBox(genders);
				JComboBox physical_activity = new JComboBox(activity);
				JComboBox training_program = new JComboBox(training);
				
				JTextField txt_firstName = new JTextField(10);
				JTextField txt_surName = new JTextField(10);
				JTextField txt_username2 = new JTextField(10);
				JTextField txt_password2 = new JTextField(10);
				
				JTextField txt_age = new JTextField(10);
				JTextField txt_height = new JTextField(10);
				JTextField txt_weight = new JTextField(10);
				JTextField txt_weight2 = new JTextField(10);
	
				
				JLabel lbl_firstName = new JLabel("First name: ");
				JLabel lbl_gender = new JLabel("Gender: ");
			
				JLabel lbl_surName = new JLabel("Last name: ");
				JLabel lbl_height = new JLabel("Height(CM): ");
				JLabel lbl_weight = new JLabel("Weight(KG): ");
				JLabel lbl_age = new JLabel("Age");
				JLabel lbl_physicalActivity = new JLabel("Physical Activity: ");
				JLabel lbl_trainingProgram = new JLabel("Training Program: ");
				JLabel lbl_weightGainLoss = new JLabel("Weight(Gain/Loss) KG: ");
				JLabel lbl_username2 = new JLabel("Username: ");
				JLabel lbl_password2 = new JLabel("Password: ");
				
				JButton btn_back = new JButton("Back");
				JButton btn_signUp = new JButton("Sign Up");
				
				//////////////////////////////////////////////////////////
				
				signUp_panel.setLayout(new GridLayout(12, 2));
				signUp_panel.add(lbl_username2); signUp_panel.add(txt_username2);
				signUp_panel.add(lbl_password2); signUp_panel.add(txt_password2);
				signUp_panel.add(lbl_firstName); signUp_panel.add(txt_firstName);
				signUp_panel.add(lbl_surName); signUp_panel.add(txt_surName);
				signUp_panel.add(lbl_age); signUp_panel.add(txt_age);
				signUp_panel.add(lbl_gender); signUp_panel.add(combo_gender);
			
				
				/////////////////////////////////////
				signUp_panel.add(lbl_height);  	signUp_panel.add(txt_height);
				signUp_panel.add(lbl_weight); signUp_panel.add(txt_weight); 


				/////////////////////////////////////
			 
			
				////////////////////////////////////
				signUp_panel.add(lbl_physicalActivity); signUp_panel.add(physical_activity);
				////////////////////////////////////
				signUp_panel.add(lbl_trainingProgram); signUp_panel.add(training_program);
				////////////////////////////////////
				signUp_panel.add(lbl_weightGainLoss);	signUp_panel.add(txt_weight2); 
			
				////////////////////////////////////
				signUp_panel.add(btn_back); signUp_panel.add(btn_signUp);

				txt_weight2.setEditable(false);
				signUp_frame.setLayout(new BorderLayout());
				signUp_frame.add(signUp_panel, BorderLayout.CENTER); 
				txt_username.setText("");
				txt_password.setText("");
				signUp_frame.show(true);
				signUp_frame.setSize(400, 400);
				
				btn_back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						signUp_frame.getContentPane().removeAll();
						signUp_frame.repaint();
						signUp_frame.hide();
						signInUp_frame.show(true);
						signInUp_frame.setSize(400, 400);
					}
				});
				
				combo_gender.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						if(combo_gender.getSelectedIndex() == 0) {
							genderPass=0;
					
						}
						else if(combo_gender.getSelectedIndex() == 1) {
							genderPass = 1;
				
						}
						else {
							genderPass = 2;
						}
					}
				});
				

				
				
				physical_activity.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						if(physical_activity.getSelectedIndex() == 0) {
							physicalActivityPass = 0;
				
						}
						else if(physical_activity.getSelectedIndex() == 1) {
							physicalActivityPass = 1;
						}
						else if(physical_activity.getSelectedIndex() == 2) {
							physicalActivityPass = 2;
						}
						else if(physical_activity.getSelectedIndex() == 3){
							physicalActivityPass = 3;
						}
						else {
							physicalActivityPass = 4;
						}
					}
				});
				
				training_program.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						if(training_program.getSelectedIndex() == 0) {
							trainingProgramPass = 0;

						}
						else if(training_program.getSelectedIndex() == 1) {
							trainingProgramPass = 1;
							txt_weight2.setEditable(false);

						}
						else if(training_program.getSelectedIndex() == 2) {
							trainingProgramPass = 2;
							txt_weight2.setEditable(true);
						}
	
						else {
							trainingProgramPass = 3;
							txt_weight2.setEditable(true);
						}
					}
				});
				
				btn_signUp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						firstName = txt_firstName.getText();
						surName = txt_surName.getText();
						username_signUp = txt_username2.getText();
						password_signUp = txt_password2.getText();
						height = Double.parseDouble(txt_height.getText());
						age = Integer.parseInt(txt_age.getText());
						weight = Double.parseDouble(txt_weight.getText());
						if(genderPass == 0) {
							gender = "";
						}
						else if(genderPass == 1) {
							gender = "MALE";
						}		
						else if(genderPass == 2){
							gender = "FEMALE";
						}
						if(physicalActivityPass == 0) {
							physicalActivity = "";
						}
						else if(physicalActivityPass == 1) {
							physicalActivity ="Sedentary(No exercise)";
						}
						else if(physicalActivityPass == 2) {
							physicalActivity = "Low Active(1-3 times a week)";
						}
						else if(physicalActivityPass == 3) {
							physicalActivity ="Active(4-6 times a week)";
						}
						else if(physicalActivityPass == 4){
							physicalActivity = "Very Active(More than 6 times a week)";
						}
						if(trainingProgramPass == 0) {
							trainingProgram="";
						}
						else if(trainingProgramPass == 1) {
							trainingProgram = "Weight Maintenance";
							weightGainLoss = 0;
						}
						else if(trainingProgramPass == 2) {
							trainingProgram = "Weight Loss";
							weightGainLoss = Integer.parseInt(txt_weight2.getText());
							
						}
						else if(trainingProgramPass == 3){
							trainingProgram="Weight Gain";
							weightGainLoss = Integer.parseInt(txt_weight2.getText());
						}
						System.out.println("Username: "+username_signUp);
						System.out.println("Password: "+password_signUp);
						System.out.println("First name: "+firstName);
						System.out.println("Surname: "+surName);
						System.out.println("Age: "+age);
						System.out.println("Gender: "+gender);
						System.out.println("Kilogram Weight: "+weight);
						System.out.println("Height cm"+ height);
						System.out.println("Physical Activity: "+physicalActivity);
						System.out.println("Training Program: "+trainingProgram);
						System.out.println("WeightGainLoss in kg: "+weightGainLoss);
						System.out.println();
						if(firstName.equals("") || surName.equals("") || username_signUp.equals("") || password_signUp.equals("") 
								|| age==0 || gender.equals("") || height==0 || weight==0|| physicalActivity.equals("") || trainingProgram.equals("") || weightGainLoss==-1) {
							JOptionPane.showMessageDialog(null, "Please Input All Fields", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {		
							if(addGymRecord()==true) { 
								signUp_frame.getContentPane().removeAll();
								signUp_frame.repaint();
								signUp_frame.hide();
								signInUp_frame.show(true);
								signInUp_frame.setSize(400, 400);
							}
							else {
								signUp_frame.getContentPane().removeAll();
								signUp_frame.repaint();
								signUp_frame.hide();
								signInUp_frame.show(true);
								signInUp_frame.setSize(400, 400);
							}
						}
					}
				});	
			}
		});
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {				
				signInUp_frame.getContentPane().removeAll();
				signInUp_frame.repaint();
				signInUp_frame.hide();
				mainframe.show(true);
				mainframe.setSize(400, 400);
			}
		});
	}

	protected static boolean addGymRecord() {
		usernameCheck = username_signUp; 
		passwordCheck = password_signUp;
		username_signUp = "'"+username_signUp+"'";
		password_signUp = "'"+password_signUp+"'";
		firstName = "'"+firstName+"'";
		surName = "'"+surName+"'";
		gender = "'"+gender+"'";
		physicalActivity = "'"+physicalActivity+"'";
		trainingProgram = "'"+trainingProgram+"'";
		boolean dbiConfirm = false;
		dbi = new Database_insert();
		dbiConfirm = dbi.insertion();
		physicalActivityPass = -1;
		trainingProgramPass = -1;
		if(dbiConfirm == false) {
			JOptionPane.showMessageDialog(null, "Username/password already taken", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else if(dbiConfirm == true){
			JOptionPane.showMessageDialog(null, "Success", "Signed Up", JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "No.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}		
	}
}
