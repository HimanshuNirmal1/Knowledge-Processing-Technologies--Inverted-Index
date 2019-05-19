/**
ISTE.612.01
612Lab01_2185
HIMANSHU VINOD NIRMAL

*/
//package lab1;
import java.util.*;
import java.io.*;

   public class InvertedIndex {              
   //attributes
   static String str1= new String();
   private String[] myDocs;               //input docs
   private ArrayList<String> termList;    //dictionary
   private ArrayList<ArrayList<Integer>> docLists;       //LBE02
   private ArrayList<Integer> docList;                   //LBE02
   public static HashMap<String,ArrayList<Integer>> mapWordvsDocId;
   
   public InvertedIndex() {
	  
   }
/**

* InvertedIndex function shows the index of every word in the file
* @param docs1 stores all the stemmed words

*/   

           
   public InvertedIndex(ArrayList<String> docs1) {            //LBE02
    // String[] docs=new String[10000];
	  String[] docs=new String[docs1.size()];
     Object temp[] = docs1.toArray();
     int r=0;
     for (Object obj:temp){
     docs[r++]=(String)obj;
     // docs[r++]=" ";
      }
     //System.out.println("mydocs.lenght  "+docs.length);
     myDocs = docs; 
     
     termList = new ArrayList<String>();
     //docLists = new ArrayList<int[]>();             //LBE02
     docLists = new ArrayList<ArrayList<Integer>>();
     docList = new ArrayList<Integer>();
     
     for(int i=1;i<myDocs.length-1;i++) {
     //System.out.println("testing   "+myDocs[i]);
      String[] words = myDocs[i].split(" ");
      for(String word:words) {
         if(!termList.contains(word)) {
            termList.add(word);
           // int[] docList = new int[myDocs.length];
            docList = new ArrayList<Integer>();
            docList.add(new Integer(i));
            //docList[i] = 1;
            docLists.add(docList);
          }
         else {
            int index =termList.indexOf(word);
            
         }
      }
     }
   }


  
   
    public String toString() {
      String outputString = new String();
      for(int i=0;i<termList.size();i++) {
         outputString += String.format("%-15s", termList.get(i));
         ;
      }
      return outputString;
   } 
   
    
   
   
   public static void main(String[] args) {
	   
     	   
      
      String keyword="";
      ArrayList docs = new ArrayList<String>();
      ArrayList docs1 = new ArrayList<String>();
      mapWordvsDocId = new HashMap<String,ArrayList<Integer>>(); // to store word and docId
      ParserB p = new ParserB();
      Stemmer st = new Stemmer();
      st.stem();
      String[] fileNamesArray	=	new String[5];
      
      ArrayList<ArrayList<Integer>> indices	=	new ArrayList<ArrayList<Integer>>();
      
      fileNamesArray[0]	=	"C:\\Users\\Superman\\Desktop\\FinalLab1\\LAB1_final\\Lab1_Data\\cv000_29416.txt";
      fileNamesArray[1]	=	"C:\\Users\\Superman\\Desktop\\FinalLab1\\LAB1_final\\Lab1_Data\\cv001_19502.txt";
      fileNamesArray[2]	=	"C:\\Users\\Superman\\Desktop\\FinalLab1\\LAB1_final\\Lab1_Data\\cv002_17424.txt";
      fileNamesArray[3]	=	"C:\\Users\\Superman\\Desktop\\FinalLab1\\LAB1_final\\Lab1_Data\\cv003_12683.txt";
      fileNamesArray[4]	=	"C:\\Users\\Superman\\Desktop\\FinalLab1\\LAB1_final\\Lab1_Data\\cv004_12641.txt";
      
      
      for(int h =0; h<fileNamesArray.length ; h++) {
    	  docs	=	mainProcessing(fileNamesArray[h],h);
      }
      System.out.println("Stemmed Words : "+str1);
      System.out.println("Inverted Index: "+mapWordvsDocId);
      Boolean flag=false;
      int position=0;
      if(args.length > 0) {
    	 // if(args[0].contains("AND")) {
    		  String[] strSplit	/*=	new String[args.length]*/;
    		  strSplit=args[0].split(" ");
//    		  for(String st1 : args)
//    			  System.out.println("strSplit"+st1);
    		  for (int n =0 ; n<args.length ;n++) {
    			  int k	=	n;
		    	  if(mapWordvsDocId.containsKey(args[k]) && args[k].equalsIgnoreCase("AND")==false) {
		    		 System.out.println("\nSearching for \n" + args[k]+" found in document : "+mapWordvsDocId.get(args[k]));
		    		 indices.add(mapWordvsDocId.get(args[k]));
		    	  } 
		    		 
		    	 
   		  
		    	  else if(args[k].equalsIgnoreCase("AND")){
		    		  keyword	=	"AND";
		    	  }
		    	  else if(args[k].equalsIgnoreCase("OR")){
		    		  keyword	=	"OR";
		    	  }
		    	 
    		  else if(args[k].equalsIgnoreCase("AND")==false){
	    		  System.out.println(args[k] + " not found!");
	    		  ArrayList<Integer> temp	=	new ArrayList<Integer>();
	    		  temp.add(999);
	    		  indices.add(temp);
	    	  }
      }
    		  //System.out.println("indices"+indices);
    		  andOrFunction(keyword,indices);
      }
   ArrayList<ArrayList<Integer>> indices1	=	new ArrayList<ArrayList<Integer>>();
   indices1.add(mapWordvsDocId.get("plot"));
   indices1.add(mapWordvsDocId.get("half"));
   
   System.out.println("");
   System.out.println("Test Case: plot AND half");
   //System.out.println("indices1"+indices1);;
   andOrFunction("and",indices1);
   System.out.println("");
   System.out.println("Test Case: plot OR half");
   andOrFunction("or",indices1);
             
   }
 
/**
* mainProcessing function displays the text files and also the file id which is assigned to text file
 
@param fileName stores the name of each file that is passed to mainProcessing in main()
@param fileId stores the index position of each file
   
*/

   public static ArrayList<String> mainProcessing(String fileName,Integer fileId) 
   {
	   //int i;
	   ArrayList docs = new ArrayList<String>();
	   String[] details;
		ParserB p = new ParserB();
		Stemmer st = new Stemmer();
		st.stem();
	      try {
           //(for i=1; i<fileNamesArray.length;i++){
	    	  System.out.println("Reading the following file : "+fileName);
	    	  System.out.println("="+fileId);
           File file = new File(fileName);
	    	
	         ArrayList<String> stemmed = p.parseB(file);
	         for(String stm:stemmed) {
	           //  System.out.println("stnm /n"+stm);
	             docs.add(stm);
	             //docs.add(" ");
	             
	           
	         }
	         
	      }
	      catch (IOException ioe) {
	         ioe.printStackTrace();
	      }    

	      InvertedIndex im = new InvertedIndex(docs);     //LBE02
	      
	     
	      

		  try {
			  BufferedReader in = new BufferedReader(new FileReader(fileName));
			  String str;

			  ArrayList<String> list = new ArrayList<String>();
			  while((str = in.readLine()) != null){
			      list.add(str);
			  }
			  String[] array = list.toArray(new String[list.size()]);

			  //String[] stringArr = list.toArray(new String[0]);
			 // System.out.println("array"+array[1]);
			  
			  String res = String.join(" ", array);
		      
		      
		      for(int k=0;k<list.size();k++) {
		    	  str1	=	String.valueOf(im);
		      }
		      
		      //System.out.println("str "+str);
	            //}
		      
		      
		     // new CreateMap().createMapfromWords(str1,fileId);
/**			  

createMapfromWords() function is called from the CreateMap class 
@param res stores the content from each file in string format
@param fileId stores the file id for that file
   
*/          
            new CreateMap().createMapfromWords(res,fileId);   
		      
		      
	
		   
      }
      
	   catch (IOException  e) 
	   { 
	 
	     // do something 
	     e.printStackTrace(); 
	   }
	return docs;
   }
  
/**
*andOrFunction function processes the query as AND or OR
*@param keyword stores the AND or OR as per user input
*@param indices stores the file id of the given argument given by the user


*/  
   
      public static void andOrFunction(String keyword,ArrayList<ArrayList<Integer>> indices){
	   Set<Integer> set = new HashSet<Integer>();
	   ArrayList<Integer> finalIndices	=	new ArrayList<Integer>();
	   
	   ArrayList<Integer> intList	=	new ArrayList<Integer>();
      
	   
	  
	  ArrayList<Integer> intList1	=	new ArrayList<Integer>();
      if(keyword.equalsIgnoreCase("OR")){
    	  
		  for(int temp = 0; temp <  indices.size(); temp++)
			  intList.addAll(indices.get(temp));
		  intList.remove(new Integer(999));
    	  set.addAll(intList);
		  System.out.println("Search query(OR) found in: \n"+set);
		   }
	   
	   
   
	   if(keyword.equalsIgnoreCase("AND")){

		   ArrayList<Integer> temp	=	new ArrayList<Integer>();
		   temp.addAll(0, indices.get(0));
		   for(int index =0 ; index <indices.size();index ++)
		   {
			   if(index + 1 < indices.size()) {
				   
				   temp.retainAll(indices.get(index+1));
				   
			   }
			   
		   }
			  
		   
	
		   System.out.println("Search query(AND) found in: \n"+temp);
		   }
	  
	   
	   }
	   
   }
   

