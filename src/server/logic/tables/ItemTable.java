package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import server.logic.model.Item;
//import org.apache.log4j.Logger;
//import utilities.Trace;

public class ItemTable {
	
	//private Logger logger = Trace.getInstance().getLogger("opreation_file");
	
	List<Item> itemList=new ArrayList<Item>();
    
	private static class ItemListCollection {
        private static final ItemTable INSTANCE = new ItemTable();
    }
    
    private ItemTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277","9781317594277"};
    	String[] copiesList=new String[]{"1","1","1","1","1","2"};
    	for(int i=0;i<ISBNList.length;i++){
			Item newItem=new Item(i,ISBNList[i],copiesList[i]);
			itemList.add(newItem);
		}
    	//logger.info(String.format("Operation:Initialize ItemTable;ItemTable: %s", itemList));
    };
    
    public static final ItemTable getInstance() {
        return ItemListCollection.INSTANCE;
    }
    
    public List<Item> getItemList() {
		return itemList;
	}
    
    public boolean lookup(String ISBN, String copyNumber) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBNFromList=(itemList.get(i)).getISBN();
			String copiesFromList=(itemList.get(i)).getCopyNumber();
			if(ISBNFromList.equalsIgnoreCase(ISBN) && copiesFromList.equalsIgnoreCase(copyNumber)){
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
    
    public boolean createItem(String ISBN) {
		boolean result=true;
		result=TitleTable.getInstance().lookup(ISBN);
		if(result){
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			if(itemList.get(i).getISBN().equalsIgnoreCase(ISBN)){
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		Item newitem=new Item(itemList.size(),ISBN,String.valueOf(flag+1));
		itemList.add(newitem);
		System.out.println(newitem.getCopyNumber());
		//logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Success", string,String.valueOf(flag+1)));
		}else{
			result=false;
			//logger.info(String.format("Operation:Create New Item;Item Info:[%s,%s];State:Fail;Reason:No such ISBN existed.", string,"N/A"));
		}
		return result;
	}
    
    public Object delete(String ISBN, String copyNumber) {
		//Since the itemid and copynumber in is automatically assigned to the item,upon its creation.
		//Each item has a unique itemid and copynumber.Even it is deleted,they can not be assigned to other item.
		//To maintain the correctness of the data,here instead delete index from the List.
		//I choose to remove the item's information instead the whole index.
		String result="";
		int index=0;
		int flag=0;
		for(int i=0;i<itemList.size();i++){
			String ISBNfromList=(itemList.get(i)).getISBN();
			String copynumberFromList=(itemList.get(i)).getCopyNumber();
			if(ISBNfromList.equalsIgnoreCase(ISBN) && copynumberFromList.equalsIgnoreCase(copyNumber)){
				index=i;
				flag=flag+1;
			}else{
				flag=flag+0;
			}
		}
		if(flag!=0){
			boolean loan=LoanTable.getInstance().checkLoan(ISBN,copyNumber);
			if(loan){
			itemList.get(index).setCopyNumber("N/A");
			result="success";
			//logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Success", string,"N/A"));
			}else{
				result="Active Loan Exists";
				//logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The item is currently on loan.", string,string2));
			}
		}else{
			result="The Item Does Not Exist";
			//logger.info(String.format("Operation:Delete Item;Item Info:[%s,%s];State:Fail;Reason:The Item Does Not Exist.", string,string2));
		}
		return result;
	}
    
    public void deleteAll(String ISBN) {
		for(int i=0;i<itemList.size();i++){
			if(ISBN.equalsIgnoreCase(itemList.get(i).getISBN())){
				itemList.get(i).setISBN("N/A");
				itemList.get(i).setCopyNumber("N/A");
				//logger.info(String.format("Operation:Delete Item Due to Title Deletion;ISBN Info:[%s];State:Success", string));
			}
		}
	}
}
