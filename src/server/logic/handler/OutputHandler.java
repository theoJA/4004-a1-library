package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.tables.ItemTable;
import server.logic.tables.TitleTable;
import server.logic.tables.UserTable;

public class OutputHandler {

	public static final int CREATEUSER=4;
	public static final int CLERK = 2;
	public static final int CREATETITLE=5;
	public static final int CREATEITEM=6;
	
	// Helper method 
	public static boolean isInteger(String value) {
		char[] ch = value.toCharArray();
		boolean isNumber=true;
		if(value.length()==13){
			for (int i = 0; i < ch.length; i++) {
				isNumber = Character.isDigit(ch[i]);
			}
		}else{
			isNumber=false;
		}
		return isNumber;
	}
	
	
	public Output createUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should in this format:'username,password'");
        	output.setState(CREATEUSER);
        }else{
        	result=UserTable.getInstance().createUser(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The User Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output createTitle(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=2 || number!=true){
        	output.setOutput("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number");
        	output.setState(CREATETITLE);
        }else{
        	result=TitleTable.getInstance().createTitle(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
	
	public Output createItem(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean number=isInteger(strArray[0]);
        Object result="";
        if(strArray.length!=1 || number!=true){
        	output.setOutput("Your input should in this format:'ISBN',ISBN should be a 13-digit number");
        	output.setState(CREATEITEM);
        }else{
        	result=ItemTable.getInstance().createItem(strArray[0]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The Title Does Not Exists!");
//        		output.setState(CREATETITLE);
        	}
        	output.setState(CLERK);
        }
		return output;
	}

}
