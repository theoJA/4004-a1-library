package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import server.logic.model.Fee;
import server.logic.model.Loan;
import utilities.Config;
import utilities.Trace;

public class FeeTable {
	private Logger logger = Trace.getInstance().getLogger("opreation_file");
	
	List<Fee> feeList=new ArrayList<Fee>();
    
	private static class FeeListCollection {
        private static final FeeTable INSTANCE = new FeeTable();
    }
    
	private FeeTable(){
    	//set up the default list with some instances
    	Fee fee=new Fee(0,5);
    	feeList.add(fee);
    	Initialization();
    };
    
    public void Initialization(){
    	List<Loan> loanList=LoanTable.getInstance().getLoanList();
    	for(int i=0;i<loanList.size();i++){
    		applyFee(loanList.get(i).getUserId(), new Date().getTime()-loanList.get(i).getDate().getTime());
    	}
    	logger.info(String.format("Operation:Initialize FeeTable;FeeTable: %s", feeList));
	}
    
    public static final FeeTable getInstance() {
        return FeeListCollection.INSTANCE;
    }
    
    public List<Fee> getFeeList() {
		return feeList;
	}
    
    public void applyFee(int userId, long time) { // time is in milliseconds
		int flag=0;
		int index=0;
		for(int i = 0;i<feeList.size();i++){
			int userIdFromList=(feeList.get(i)).getUserId();
			if(userIdFromList==userId){
				flag=flag+1;
				index=i;
			}
		}
		int currFee=(int) ((time/(Config.STIMULATED_DAY))-Config.OVERDUE);
		// If userId exists in the fee list, add currFee to the existing fee
		if(flag!=0){
			// the existing userId's loan has gone past 5 days
			if(currFee>=0){ 
				feeList.get(index).setFee(currFee+feeList.get(index).getFee());
				feeList.get(index).setUserId(userId);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", userId,currFee+feeList.get(index).getFee()));
			}else{	
				// the existing userId's loan hasn't gone past 5 days
				feeList.get(index).setFee(feeList.get(index).getFee());
				feeList.get(index).setUserId(userId);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", userId,currFee+feeList.get(index).getFee()));
			}
		// If userId doesn't exist in the fee list, we add that userId and a new fee to it
		}else{
			// add new userId to the list, and the corresponding accrued fee (loan has gone past 5 days)
			if(currFee>=0){	
				Fee newFee=new Fee(userId,currFee);
				feeList.add(newFee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", userId,currFee));
			}else{	
				// add new userId and a fee of $0 (this is for loans that hasn't gone beyond 5 days) 
				Fee fee=new Fee(userId,0);
				feeList.add(fee);
				logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", userId,0));
			}
		}
    }
    
    // this method is private but is used in the lookup methods
    private boolean checkUserExists(int userId) {
		boolean result=true;
		int fee = 0;
		for(int i=0;i<feeList.size();i++){
			int userIdFromList=(feeList.get(i)).getUserId();
			if(userIdFromList==userId){
				fee=fee+1;
			}else{
				fee=fee+0;
			}
		}	
		if(fee==0){
			result=false;
		}
		return result;
	}
    
    public boolean lookup(int userId) {
		boolean result=true;
		int fee = 0;
		boolean user=FeeTable.getInstance().checkUserExists(userId);
		if(user){
			for(int i=0;i<feeList.size();i++){
				int userIdFromList=(feeList.get(i)).getUserId();
				if(userIdFromList==userId){
					fee=fee+feeList.get(i).getFee();
				}
			}	
		}else{	// user doesn't exist, so no fee, thus true
			fee=0;
		}
		if(fee!=0){
			result=false; // fee exists?
		}
		return result;
	}
    
    public Object lookupfee(int userId) {
		int fee=0;
		boolean user=FeeTable.getInstance().checkUserExists(userId);
		if(user){
		for(int i=0;i<feeList.size();i++){
			int userIdFromList=(feeList.get(i)).getUserId();
			if(userIdFromList==userId){
				fee=fee+feeList.get(i).getFee();
			}
		}
		}else{
			fee=0;
		}
		return fee;
	}
    
    public Object payFine(int userId) {
		String result="";
		boolean oloan=LoanTable.getInstance().lookLimit(userId);
		int fee=0;
		int index=0;
		boolean user=FeeTable.getInstance().checkUserExists(userId);
		if(user){
			for(int m=0;m<feeList.size();m++){
				if(feeList.get(m).getUserId()==userId){
					fee=feeList.get(m).getFee();
					index=m;
				}else{
					fee=0;
				}
			}
		}else{
			fee=0;
		}
		if(oloan==false){
			result="Borrowing Items Exist";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Fail;Reason:Borrowing Items Exist.", userId,fee));
		}else{
			feeList.get(index).setUserId(userId);
			feeList.get(index).setFee(0);
			result="success";
			logger.info(String.format("Operation:Pay Fine;Fee Info:[%d,%d];State:Success", userId,fee));
		}
		return result;
	}
    
}
