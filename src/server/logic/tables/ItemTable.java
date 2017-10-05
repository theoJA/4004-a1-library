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
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910"};
    	String[] copiesList=new String[]{"1","1","1","1"};
    	for(int i=0;i<ISBNList.length;i++){
			Item newItem=new Item(i,ISBNList[i],copiesList[i]);
			itemList.add(newItem);
		}
    	//logger.info(String.format("Operation:Initialize ItemTable;ItemTable: %s", itemList));
    };
    
    public static final ItemTable getInstance() {
        return ItemListCollection.INSTANCE;
    }
}
