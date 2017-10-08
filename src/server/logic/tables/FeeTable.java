package server.logic.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import org.apache.log4j.Logger;

//import org.apache.log4j.Logger;

import server.logic.model.Fee;
import server.logic.model.Loan;
import utilities.Config;
//import utilities.Trace;

public class FeeTable {
	//private Logger logger = Trace.getInstance().getLogger("opreation_file");
	
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
    		applyfee(loanList.get(i).getUserId(), new Date().getTime()-loanList.get(i).getDate().getTime());
    	}
    	//logger.info(String.format("Operation:Initialize FeeTable;FeeTable: %s", feeList));
	}
    
    public static final FeeTable getInstance() {
        return FeeListCollection.INSTANCE;
    }
    
    public List<Fee> getFeeList() {
		return feeList;
	}
    
    public void applyfee(int userId, long time) {
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
		// If passed in userId exists in the fee list, add currFee to the existing fee
		if(flag!=0){
			if(currFee>=0){ // if currFee >= 0, add currFee to existing fee
				feeList.get(index).setFee(currFee+feeList.get(index).getFee());
				feeList.get(index).setUserId(userId);
				//logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}else{	// if no fee at all?  
				feeList.get(index).setFee(feeList.get(index).getFee());
				feeList.get(index).setUserId(userId);
				//logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,a+feeList.get(index).getFee()));
			}
		// If userId doesn't exist in the fee list, we add a new fee to it
		}else{
			if(currFee>=0){	// add new currFee to the list
				Fee newFee=new Fee(userId,currFee);
				feeList.add(newFee);
				//logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,currFee));
			}else{	// add the userId that doesn't exist to the fee list. This is to ensure that the userId exist in the list but hasn't gotten any
					// 	fees yet. This just makes it easier to add new fees to this specific userId?
				Fee fee=new Fee(userId,0);
				feeList.add(fee);
				//logger.info(String.format("Operation:Apply OutStanding Fee;Fee Info:[%d,%d];State:Success", j,0));
			}
		}
    }
    
}
