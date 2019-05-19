//package lab1;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateMap {
	
	InvertedIndex invertedIndexObj	=	new InvertedIndex();
	
	ArrayList<Integer> fileIds	=	new ArrayList<Integer>();
	public void createMapfromWords(String strWords,Integer fileid) {
		fileIds.add(fileid);
		String[] arrayWords = strWords.split("\\s+");
		for (String a : arrayWords) {
          // System.out.println("createmap"+a); 
			
			if(invertedIndexObj.mapWordvsDocId.containsKey(a)) {
				
				ArrayList<Integer> tempArrayList	=	invertedIndexObj.mapWordvsDocId.get(a);
				if(!tempArrayList.contains(fileid)) {
					tempArrayList.add(fileid);
					invertedIndexObj.mapWordvsDocId.put(a, tempArrayList);
				}
			}
				
			else {
				invertedIndexObj.mapWordvsDocId.put(a, fileIds);
			}
		}
		//System.out.println("invertedIndexObj.mapWordvsDocId"+invertedIndexObj.mapWordvsDocId); 
		
	}
	
	public static void main(String[] args) {
		
	}
	  

}

