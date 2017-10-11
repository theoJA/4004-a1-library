package server.logic.tables;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.User;
import utilities.Trace;

public class UserTable {
	
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<User> userList=new ArrayList<User>();
	
	private static class UserListCollection {
        private static final UserTable INSTANCE = new UserTable();
    }
	
	private UserTable(){
    	// Initializing the user table
    	String[] passwordList=new String[]{"zhibo","yu","michelle","kevin","sun"};
    	String[] usernameList=new String[]{"zhibo@carleton.ca","yu@carleton.ca","michelle@carleton.ca","kevin@carleton.ca","sun@carleton.ca"};
    	for(int i = 0; i < usernameList.length; i++){
			User deuser = new User(i, usernameList[i], passwordList[i]);
			userList.add(deuser);
		}
    	logger.info(String.format("Operation:Initialize UserTable;UserTable: %s", userList));
    };
    
    public static final UserTable getInstance() {
        return UserListCollection.INSTANCE;
    }
    
    public List<User> getUserList() {
		return userList;
	}
    
    public int checkUserExists(String username, String password) {
		int result=0;
		int flag=0;
		int index=0;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(username)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;
			}
		}
		boolean userPassword=userList.get(index).getPassword().equalsIgnoreCase(password);
		if(flag!=0 && userPassword){
			result=0; // User exists!
		}else if(flag==0){
			result=2; // User does not exist!
		}else if(userPassword==false){
			result=1; // Password does not match!
		}
		return result;
	}
    
    public int lookup(String username) {
		int userid=-1;
		for(int i=0;i<userList.size();i++){
			if(userList.get(i).getUsername().equalsIgnoreCase(username)){
				userid=i;
			}
		}
		return userid;
	}
    
    public boolean lookup(int passedUserId) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<userList.size();i++){
			int userid=(userList.get(i)).getUserId();
			if(userid==passedUserId){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			result=false;
		}
		return result;
	}
    
    public boolean createUser(String username, String password) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<userList.size();i++){
			String usernameFromList=(userList.get(i)).getUsername();
			if(usernameFromList.equalsIgnoreCase(username)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			User newuser=new User(userList.size(),username,password);
			result=userList.add(newuser);
			logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Success", username,password));
		}else{
			result=false;
			logger.info(String.format("Operation:Create New User;User Info:[%s,%s];State:Fail;Reason:The User already existed.", username,password));
		}
		return result;	
	}
    
    public Object delete(int userId) {
		//Since the userid in "User Creation" is automatically assigned to the user,upon its creation.
		//Each user has a unique userid.Even it is deleted,its userid can not be assigned to other user.
		//To maintain the correctness of the data,here instead delete index from the List.
		//I choose to remove the user's information instead the whole index.Keep its userid as reference.
		String result="";
		boolean loan=LoanTable.getInstance().checkUser(userId);
		int flag=0;
		int index=0;
		for(int j=0;j<userList.size();j++){
			if(userList.get(j).getUserId()==userId){
				index=j;
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		
		if(flag==0){
			result="The User Does Not Exist";
			logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:The User Does Not Exist.", "N/A","N/A"));
		}else{
			boolean fee=FeeTable.getInstance().lookup(userId);
			String string=userList.get(index).getUsername();
			String string2=userList.get(index).getPassword();
			if(fee && loan){
				userList.get(index).setUserId(userId);
				userList.get(index).setPassword("N/A");
				userList.get(index).setUsername("N/A");
				result="success";
				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Success", string,string2));
			}else if(fee==false){
				result="Outstanding Fee Exists";
				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:Outstanding Fee Exists.", string,string2));
			}else if(loan==false){
				result="Active Loan Exists";
				logger.info(String.format("Operation:Delete User;User Info:[%s,%s];State:Fail;Reason:Active Loan Exists.", string,string2));
			}
		}
		return result;

	}

}
