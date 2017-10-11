package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Loan;
import utilities.Trace;
import utilities.Config;

public class LoanTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Loan> loanList=new ArrayList<Loan>();
	
    private static class LoanListCollection {
        private static final LoanTable INSTANCE = new LoanTable();
    }
    
    private LoanTable(){
    	//set up the default list with some instances
    	Loan loan0 = new Loan(0,"9781442668584","1",new Date(),"0");
    	loanList.add(loan0);
    	Loan loan1 = new Loan(1,"9781442667181","1",new Date(),"0");
    	loanList.add(loan1);
    	logger.info(String.format("Operation:Initialize LoanTable;LoanTable: %s", loanList));
    };
    
    public static final LoanTable getInstance() {
        return LoanListCollection.INSTANCE;
    }
    
    public List<Loan> getLoanList() {
		return loanList;
	}
    
    public boolean lookLimit(int userId) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int userIdFromList=(loanList.get(i)).getUserId();
			if(userIdFromList==userId){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean lookup(int j, String ISBN, String copyNumber) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBNfromList=(loanList.get(i)).getISBN();
			String copynumberFromList=(loanList.get(i)).getCopyNumber();
			if(ISBNfromList.equalsIgnoreCase(ISBN) && copynumberFromList.equalsIgnoreCase(copyNumber)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    private String dateformat(Date date){
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String datestr=format1.format(date);
		return datestr;
	}
    
    public boolean checkUser(int userId) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int useridFromList=(loanList.get(i)).getUserId();
			if(useridFromList==userId){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean checkLoan(String ISBN, String copyNumber) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBNfromList=(loanList.get(i)).getISBN();
			String copynumberFromList=(loanList.get(i)).getCopyNumber();
			if(ISBNfromList.equalsIgnoreCase(ISBN) && copynumberFromList.equalsIgnoreCase(copyNumber)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean checkLoan(String ISBN) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			String ISBNfromList=(loanList.get(i)).getISBN();
			if(ISBNfromList.equalsIgnoreCase(ISBN)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			result=false;
		}
		return result;
	}
    
    public boolean checkLimit(int userId) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<loanList.size();i++){
			int useridFromList=(loanList.get(i)).getUserId();
			if(useridFromList==userId){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag>=Config.MAX_BORROWED_ITEMS){
			result=false;
		}
		return result;
	}
    
    public Object createLoan(int userId, String ISBN, String copyNumber, Date date) {
		String result="";
		boolean user=UserTable.getInstance().lookup(userId);
		boolean isbn=TitleTable.getInstance().lookup(ISBN);
		boolean copynumber=ItemTable.getInstance().lookup(ISBN,copyNumber);
		boolean oloan=LoanTable.getInstance().lookup(userId,ISBN,copyNumber);
		boolean limit=LoanTable.getInstance().checkLimit(userId);
		boolean fee=FeeTable.getInstance().lookup(userId);
		if(user==false){
			result="User Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid User.", userId,ISBN,copyNumber,dateformat(date)));
		}else if(isbn==false){
			result="ISBN Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid ISBN.", userId,ISBN,copyNumber,dateformat(date)));
		}else if(copynumber==false){
			result="Copynumber Invalid";
			logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Invalid Copynumber.", userId,ISBN,copyNumber,dateformat(date)));
		}else{
			if(oloan){
				if(limit && fee){
				Loan loan=new Loan(userId,ISBN,copyNumber,date,"0");
				loanList.add(loan);
				result="success";
				logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Success", userId,ISBN,copyNumber,dateformat(date)));
				}else if(limit==false){
					result="The Maximum Number of Items is Reached";
					logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Maximun Number of Items is Reached.", userId,ISBN,copyNumber,dateformat(date)));
				}else if(fee==false){
					result="Outstanding Fee Exists";
					logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Outstanding Fee Exists.", userId,ISBN,copyNumber,dateformat(date)));
				}
			}else{
				result="The Item is Not Available";
				logger.info(String.format("Operation:Borrow Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Item is Not Available.", userId,ISBN,copyNumber,dateformat(date)));
			}
		}
    	return result;
	}
    
    public Object returnItem(int userId, String ISBN, String copyNumber, Date date) {
		String result="";
		int flag=0;
		int index=0;
		for(int i=0;i<loanList.size();i++){
			String ISBNfromList=(loanList.get(i)).getISBN();
			String copynumberFromList=(loanList.get(i)).getCopyNumber();
			int userid=(loanList.get(i)).getUserId();
			if((userid==userId) && ISBNfromList.equalsIgnoreCase(ISBN) && copynumberFromList.equalsIgnoreCase(copyNumber)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;	
			}
		}
		if(flag!=0){
			long time = date.getTime()-loanList.get(index).getDate().getTime();
			loanList.remove(index);
			logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s,%s];State:Success", userId,ISBN,copyNumber,dateformat(date)));
			if(time>Config.OVERDUE*Config.STIMULATED_DAY){
				FeeTable.getInstance().applyFee(userId,time);
			}
			result="success";
		}else{
			result="The Loan Does Not Exist";
			logger.info(String.format("Operation:Return Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Loan Does Not Exist.", userId,ISBN,copyNumber,dateformat(date)));
		}
		
		return result;
	}
    
    public Object renewal(int userId, String ISBN, String copyNumber, Date date) {
		String result="";
		int flag=0;
		int index=0;
		boolean limit=LoanTable.getInstance().checkLimit(userId);
		boolean fee=FeeTable.getInstance().lookup(userId);
		for(int i=0;i<loanList.size();i++){
			String ISBNfromList=(loanList.get(i)).getISBN();
			String copynumberFromList=(loanList.get(i)).getCopyNumber();
			int userid=(loanList.get(i)).getUserId();
			if((userid==userId) && ISBNfromList.equalsIgnoreCase(ISBN) && copynumberFromList.equalsIgnoreCase(copyNumber)){
				flag=flag+1;
				index=i;
			}else{
				flag=flag+0;	
			}
		}
		if(limit && fee){
			if(flag!=0){
				if(loanList.get(index).getRenewState().equalsIgnoreCase("0")){
					loanList.get(index).setUserId(userId);
					loanList.get(index).setISBN(ISBN);
					loanList.get(index).setCopyNumber(copyNumber);
					loanList.get(index).setDate(new Date());
					loanList.get(index).setRenewState("1");
					result="success";
					logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Success", userId,ISBN,copyNumber,dateformat(date)));
				}else{
					result="Renewed Item More Than Once for the Same Loan";
					logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Renewed Item More Than Once for the Same Loan.", userId,ISBN,copyNumber,dateformat(date)));
					}
			}else{
				result="The loan does not exist";
				logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The loan does not exist.", userId,ISBN,copyNumber,dateformat(date)));
			}
			
		}else if(limit==false){
			result="The Maximum Number of Items is Reached";
			logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:The Maximun Number of Items is Reached.", userId,ISBN,copyNumber,dateformat(date)));
		}else if(fee==false){
			result="Outstanding Fee Exists";
			logger.info(String.format("Operation:Renew Item;Loan Info:[%d,%s,%s,%s];State:Fail;Reason:Outstanding Fee Exists.", userId,ISBN,copyNumber,dateformat(date)));
		}
		return result;
	}
}
