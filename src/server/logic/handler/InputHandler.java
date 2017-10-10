package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandler {
	
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
	public static final int CLERK = 2;
    public static final int USER = 3;
    public static final int CREATEUSER=4;
    public static final int CREATETITLE=5;
    public static final int CREATEITEM=6;
    public static final int DELETEUSER=7;
    public static final int DELETETITLE=8;
    public static final int DELETEITEM=9;
    public static final int BORROW=10;
    public static final int RENEW=11;
    public static final int RETURN=12;
    public static final int PAYFINE=13;
    public static final int CLERKLOGIN=14;
    public static final int USERLOGIN=15;

	OutputHandler outputHandler=new OutputHandler();
	
	public ServerOutput processInput(String input, int state) {
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
		 if (state == WAITING) {	// TESTED
	        	output = "Who Are you?Clerk or User?";
	            state = FINISHWAITING;
	            oo.setOutput(output);
	            oo.setState(state);
         }else if (state == FINISHWAITING) { // TESTED
            if (input.equalsIgnoreCase("clerk")) {
            	output="Please Input The Password:";
            	state=CLERKLOGIN;
                oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("user")) { // TESTED
            	output="Please Input Username and Password:'username,password'";
            	state=USERLOGIN;
                oo.setOutput(output);
	            oo.setState(state);
            }else{	// TESTED
            	output = "Who Are you?Clerk or User?";
            	state = FINISHWAITING;
            	oo.setOutput(output);
	            oo.setState(state);
            }
        }else if(state==CLERKLOGIN){ // TESTED
        	o=outputHandler.clerkLogin(input);
 		output=o.getOutput();
 		state=o.getState();
 		oo.setOutput(output);
            oo.setState(state);
        }else if(state==USERLOGIN){ // TESTED
        	o=outputHandler.userLogin(input);
 		output=o.getOutput();
 		state=o.getState();
 		oo.setOutput(output);
            oo.setState(state);
        }else if (state==CLERK){ // TESTED
        	if (input.equalsIgnoreCase("create user")) {
            	output = "Please Input User Info:'username,password'";
            	state=CREATEUSER;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("create title")) { // TESTED
            	output = "Please Input Title Info:'ISBN,title'";
            	state=CREATETITLE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("create item")) { // TESTED
	            output = "Please Input Item Info:'ISBN'";
	            state=CREATEITEM;
	            oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("delete user")) { // TESTED
            	output = "Please Input User Info:'useremail'";
            	state=DELETEUSER;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("delete title")) { // TESTED
	            output = "Please Input Title Info:'ISBN'";
	            state=DELETETITLE;
	            oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("delete item")) { // TESTED
            	output = "Please Input Item Info:'ISBN,copynumber'";
            	state=DELETEITEM;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("log out")){ // TESTED
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){ // TESTED
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{ // TESTED
            	output = "Please select from the menu.Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
            }
        }else if (state==USER){ // TESTED
        	if (input.equalsIgnoreCase("borrow")) {
            	output = "Please Input User Info:'useremail,ISBN,copynumber'";
            	state=BORROW;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("renew")) { // TESTED
            	output = "Please Input Title Info:'useremail,ISBN,copynumber'";
            	state=RENEW;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("return")) { // TESTED
	            output = "Please Input Item Info:'useremail,ISBN,copynumber'";
	            state=RETURN;
	            oo.setOutput(output);
	            oo.setState(state);
            }else if (input.equalsIgnoreCase("pay fine")) { // TESTED
            	output = "Please Input User Info:'useremail'";
            	state=PAYFINE;
            	oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("log out")){ // TESTED
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
            }else if(input.equalsIgnoreCase("main menu")){ // TESTED
        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{ // TESTED
            	output = "Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                oo.setOutput(output);
	            oo.setState(state);
            }
        	
        }else if(state==CREATEUSER){
        	if(input.equalsIgnoreCase("log out")){ // TESTED
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){ // TESTED
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{ // TESTED
        		o=outputHandler.createUser(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==CREATETITLE){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.createTitle(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==CREATEITEM){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.createItem(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==DELETEUSER){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.deleteUser(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==DELETETITLE){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.deleteTitle(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==DELETEITEM){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.";
                state = CLERK;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.deleteItem(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==BORROW){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.borrow(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==RENEW){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.renew(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==RETURN){ // TESTED
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.returnBook(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
        }else if(state==PAYFINE){ // 
        	if(input.equalsIgnoreCase("log out")){
            	output = "Successfully Log Out!";
                state = WAITING;
                oo.setOutput(output);
	            oo.setState(state);
        	}else if(input.equalsIgnoreCase("main menu")){
        		output = "What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.";
                state = USER;
                oo.setOutput(output);
	            oo.setState(state);
        	}else{
        		o=outputHandler.payFine(input);
        		output=o.getOutput();
        		state=o.getState();
        		oo.setOutput(output);
	            oo.setState(state);
        	}
		 }
	     return oo;
	}
}
