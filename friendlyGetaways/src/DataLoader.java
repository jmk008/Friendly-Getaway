import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
public class DataLoader {

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public static void main(String[] args) throws Exception {

        String csvFile = "C:/Users/adhiraj/listings.csv";

        BufferedReader br = new BufferedReader(new FileReader(new File(csvFile))); 
        int count = 0;
        String line,tempo;
        FileWriter fw = new FileWriter("C:/Users/adhiraj/newlist1.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
        while ((line = br.readLine())!= null) {
        	if(count == 0)
        	{
        		
        		count++;
        		continue;
        	}
            System.out.println(line);
            List<String> linen = parseLine(line);
            int i = 0;
            bw.write("insert into temp_property (property_id,description,house_rules,host_id,host_url,host_name,city,state,zipcode,country,property_type,bathrooms,bedrooms,amenities,price,review_scores_rating) values(");
            try{
            while(linen.get(i)!=null)
            {
            tempo = linen.get(i).replace("'", "").replace("&", " and ").replace(";", "");	
            //tempo = linen.get(i).replace("&", "and");
            //tempo = linen.get(i).replace(";", "");
            if(tempo.equals("#NAME?"))	
            	writer("",bw,i);
            else if(!tempo.isEmpty() && tempo.charAt(0) == '"')	
            	writer(tempo.substring(1),bw,i);
            else
            writer(tempo,bw,i);
            i++;
            }
            }
            catch(Exception e){
            	count++;
            	i=0;
            }
            
            bw.write(");");
            bw.write(System.lineSeparator());
          
            
        }
        System.out.println(count);
        bw.close();
        fw.close();

    }
    
    
    public static void writer(String temp, BufferedWriter bw,int i){
    	if(i==0)
    	{
    	try{
    	bw.write("'");
    	bw.write(temp);
    	bw.write("'");
    	}
    	catch (Exception e){
    		System.out.println("error");
    	}
    	}
    	else
    	{
    		try{
    			
    			bw.write(",");
    	    	bw.write("'");
    	    	bw.write(temp);
    	    	bw.write("'");
    	    	}
    	    	catch (Exception e){
    	    		System.out.println("error");
    	    	}
    	}
    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }

}