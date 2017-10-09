package server.logic.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;

import server.logic.model.Loan;
//import utilities.Trace;
import utilities.Config;

public class LoanTable {
	//private Logger logger = Trace.getInstance().getLogger("opreation_file");
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
    	//logger.info(String.format("Operation:Initialize LoanTable;LoanTable: %s", loanList));
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
}
