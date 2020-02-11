package problems;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MerchantTrading {
	
	
	static Map romanData = new HashMap(8);	
	static final int successerCount = 3;
	static final int maxCount = 4;

	static Map subtractFrom = new HashMap(3);
	static List NONRepeatAndNonSubNumbers = new ArrayList<>(3);
	static List  metalsList = new ArrayList<>();	


	public static void main(String[] args) throws Exception {
		//Scanner sn = new Scanner(System.in);
		//System.out.println("Enter your Trading values:");
		//String userInput = sn.next();
		long total = 0;
		//String [] tempargs = {"glob","prok","Gold"};
		String metalName = "ROMANNUMBER";
		Map romanNumberCount = new HashMap(7);				
		valuesInitialization(romanNumberCount);
		
		//for(int j=0;j<args.length;j++) {  
			String somevalue = "";//args[j];
			if(args==null && args.length==0){
				throw new Exception(" Please pass some data of ROMAN letters or  'glob','prok','pish','tegi' of some metal");
			}
			if(args.length==1) {
				somevalue = args[0];
			}else {
				for(int j=0;j<args.length;j++) {
					if(metalsList.contains(args[j].toLowerCase())) {
						metalName = args[j];
					}else {
						if(romanData.get(args[j])!=null)
						 somevalue = somevalue+romanData.get(args[j].toLowerCase());
					}
				}
			}
		 
		
		
		//String somevalue = "MCMXLIV";
		
		int previousValue = 0;
		int currentValue = 0;
		int temp [] = new int[15];
		
		int i = 0;
		String previousCharIs = "EMPTY";
		int previousCharCount = 0;
		for (;i<=somevalue.length()-1;) {
			int nextVal = i+1;
			String charValue = somevalue.substring(i, nextVal);
			int count = (Integer) romanNumberCount.get(charValue);
			currentValue = Integer.valueOf((String) romanData.get(charValue));
			if(previousCharIs.equalsIgnoreCase(charValue) ) {

				if(NONRepeatAndNonSubNumbers.contains(charValue))
					throw new Exception(charValue + " this is Non Repeted Charector, Please enter valid one.");

				if( previousCharCount==3)
					throw new Exception(charValue + " reached its max successerlimit");

				previousValue = Integer.valueOf((String) romanData.get(previousCharIs));
				previousCharCount++;				
			} else {
				previousCharCount=0;
			}

			if(count <= maxCount) {
				if(count < maxCount) {
					count++;
					romanNumberCount.put(charValue, count);
				}else
					throw new Exception(charValue + " reached its max limit");
			}

			previousValue = Integer.valueOf((String) romanData.get(previousCharIs));
			
			if(subtractFrom.get(charValue)!=null && subtractFrom.get(charValue).equals(previousCharIs)) {
				temp[i-1] = currentValue - previousValue;
			}else {
				temp[i] = currentValue;
			}			
			
			previousCharIs = charValue;
			i++;
		}
		
			total = sumofIntArray(temp,metalName);
			System.out.print("Input value is: ");
			for(int j=0;j<args.length;j++) {
				System.out.print(" "+args[j]);
			}
			System.out.println(", out put value in "+metalName+": "+total);
		}

	//}

	

	private static long sumofIntArray(int[] temp, String metalName) {
		// TODO Auto-generated method stub
		long total = 0;
		for (int i : temp) {
			total = total + i;
		}
		int metalLot = Integer.valueOf((String) romanData.get(metalName.toUpperCase()));
		return total * metalLot;
	}



	private static void valuesInitialization(Map map) {
		// TODO Auto-generated method stub
		//roman Data
		romanData.put("I", "1");
		romanData.put("V", "5");
		romanData.put("X", "10");
		romanData.put("L", "50");
		romanData.put("C", "100");
		romanData.put("D", "500");
		romanData.put("M", "1000");
		romanData.put("EMPTY", "0");
		
		romanData.put("glob", "I");
		romanData.put("prok", "V");
		romanData.put("pish", "X");
		romanData.put("tegi", "L");
		romanData.put("SILVER", "17");
		romanData.put("GOLD", "14450");
		romanData.put("IRON", "195");
		romanData.put("EMPTYMETAL", "1");
		romanData.put("ROMANNUMBER", "1");

		//default values
		map.put("I", 0);
		map.put("X", 0);
		map.put("C", 0);
		map.put("M", 0);
		map.put("D", 0);
		map.put("L", 0);
		map.put("V", 0);

		//for Subtraction logic 
		subtractFrom.put("V", "I");
		subtractFrom.put("X", "I");
		subtractFrom.put("C", "X");
		subtractFrom.put("L", "X");
		subtractFrom.put("D", "C");
		subtractFrom.put("M", "C");

		NONRepeatAndNonSubNumbers.add("D");
		NONRepeatAndNonSubNumbers.add("L");
		NONRepeatAndNonSubNumbers.add("V");
		
		//metal List
		metalsList.add("sold");
		metalsList.add("silver");
		metalsList.add("iron");
	}

}
