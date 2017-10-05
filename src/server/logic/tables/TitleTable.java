package server.logic.tables;

import java.util.ArrayList;
import java.util.List;
import server.logic.model.Title;

public class TitleTable {
	
	//private Logger logger = Trace.getInstance().getLogger("opreation_file");
	List<Title> titleList=new ArrayList<Title>();
	
    private static class TitleListCollection {
        private static final TitleTable INSTANCE = new TitleTable();
    }
    
    private TitleTable(){
    	//set up the default list with some instances
    	String[] ISBNList=new String[]{"9781442668584","9781442616899","9781442667181","9781611687910","9781317594277"};
    	String[] titlenameList=new String[]{"By the grace of God","Dante's lyric poetry","Courtesy lost","Writing for justice","The act in context"};
    	for(int i=0;i<ISBNList.length;i++){
    		Title newTitle=new Title(ISBNList[i],titlenameList[i]);
    		titleList.add(newTitle);
		}
    	//logger.info(String.format("Operation:Initialize TitleTable;TitleTable: %s", titleList));
    }
    
    public static final TitleTable getInstance() {
        return TitleListCollection.INSTANCE;
    }
    
    public List<Title> getTitleList() {
		return titleList;
	}
    
    public boolean lookup(String string) {
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBN=(titleList.get(i)).getISBN();
			if(ISBN.equalsIgnoreCase(string)){
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
    
    public boolean createTitle(String ISBN, String bookTitle) {		
		boolean result=true;
		int flag=0;
		for(int i=0;i<titleList.size();i++){
			String ISBNFromList=(titleList.get(i)).getISBN();
			if(ISBNFromList.equalsIgnoreCase(ISBN)){
				flag=flag+1;
			}else{
				flag=flag+0;	
			}
		}
		if(flag==0){
			Title newtitle=new Title(ISBN,bookTitle);
			result=titleList.add(newtitle);
			//logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Success", string,string2));
		}else{
			result=false;
			//logger.info(String.format("Operation:Create New Title;Title Info:[%s,%s];State:Fail;Reason:The ISBN already existed.", string,string2));
		}
		return result;	
	}
}
