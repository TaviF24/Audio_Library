package org.example.Utils;

import java.util.ArrayList;

/**
 * Utility class for parsing input.
 */
public class InputParser {

    /**
     * Parses the input string to extract arguments.
     *
     * @param arguments the input string containing arguments
     * @return an {@code ArrayList} containing parsed arguments
     */
    public static ArrayList<String> getArgs(String arguments){
        String[] args = arguments.strip().split("\\s");
        boolean inQuotes = false;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> insideQuotes = new ArrayList<>();

        for(int i=0;i<args.length;i++){
            if(args[i].length()>1 && args[i].charAt(0) == '"' && args[i].charAt(args[i].length()-1) == '"' && !inQuotes){
                args[i] = args[i].substring(1,args[i].length()-1);
            }else{
                if(!args[i].isEmpty() && args[i].charAt(0) == '"' && !inQuotes){
                    inQuotes = true;
                }else{
                    if(!args[i].isEmpty() && args[i].charAt(args[i].length()-1) == '"' && inQuotes){
                        insideQuotes.add(args[i]);
                        String s1 = insideQuotes.get(0).substring(1);
                        for(int j=1; j<insideQuotes.size()-1; j++){
                            s1 += " " + insideQuotes.get(j);
                        }
                        s1 += " " + insideQuotes.get(insideQuotes.size()-1).substring(0, insideQuotes.get(insideQuotes.size()-1).length()-1);
                        result.add(s1);
                        inQuotes = false;
                        insideQuotes.clear();
                        continue;
                    }
                }
            }
            if(inQuotes){
                insideQuotes.add(args[i]);
            }else{
                if(!args[i].isEmpty()){
                    result.add(args[i]);
                }
            }
        }
        result.addAll(insideQuotes);
        return result;
    }
}
