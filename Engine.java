package model;

import java.math.BigInteger;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.Math;


public class Engine
{
	public static final String DRONE_URL = "https://maps.googleapis.com/maps/api/geocode/xml?address=";
	public static final String API_KEY = "&key=AIzaSyByA6lVf54NwliIeaMSBUQZzfOeRcs-CJw";
	
	public static final String FIRST_RIDE_URL = "https://maps.googleapis.com/maps/api/distancematrix/xml?origins=";
	public static final String SECOND_RIDE_URL = "&destinations=";
	public static final String THIRD_RIDE_URL = "&departure_time=now";
	
	
	private static Engine instance = null;
	private static final int GPS_VAL = 12742;
	private BigInteger comparison = new BigInteger("0");
	public StudentDAO myDAO;
	
	public Engine()
	{
		myDAO = StudentDAO.getInstance();
	}

	public synchronized static Engine getInstance()
	{
		if (instance == null) instance = new Engine();
		return instance;
	}
	
	public BigInteger doPrime(String lower, String upper) throws Exception
	{
		int intUpper = Integer.parseInt(upper);
		int intComparison = comparison.intValue();
		//System.out.println(intRandomPrime);
		if (intUpper<(intComparison)) throw new Exception ("No more primes in this range");
		
		if(Integer.parseInt(upper)<Integer.parseInt(lower)) throw new Exception ("Invalid Range");
		
		try
		{
			BigInteger lowerInt = new BigInteger(lower); 
			//BigInteger upperInt = new BigInteger(upper);
			BigInteger randomPrime = lowerInt.nextProbablePrime();
			comparison = randomPrime.nextProbablePrime();
			
			return randomPrime;
		} 
		catch (Exception e)
		{
			throw new Exception("The entry must be int!");
		}
		
	}
	
	
	public String doSis(String prefix, String minGPA, String sortBy)throws Exception{
	       
	        String prfx =rmvTroubleMaker(prefix),
	                mngp = rmvTroubleMaker(minGPA),
	                srtby = rmvTroubleMaker(sortBy);
	       
	        StudentDAO dao = new StudentDAO();
	        List<StudentBean> beans  = dao.retrieve(prfx, mngp, srtby);
	        String table = "";
	       
	        table+= "<h3 style='text-align:left;color:blue;'>Sorted By: "+ srtby.toUpperCase() + "</h3><br/>";
	       
	        table+= "<table style=\\\"margin:1;\\\" border=\\\"1\\\">";
	        for(StudentBean b : beans) {
	           
	            table+= "<tr>";
	            table+= "<td style=\\\"padding: 6px;\\\">" + b.getName() + "</td>";
	            table+= "<td style=\\\"padding: 6px;\\\">"+ b.getMajor() + "</td>";
	            table+= "<td style=\\\"padding: 6px;\\\">"+ b.getCourses() + "</td>";
	            table+= "<td style=\\\"padding: 6px;\\\">"+ b.getGpa() + "</td>";
	            table+= "</tr>";
	        }
	        table+= "</table>";
	       
	        return table;
	}
	
	 private String rmvTroubleMaker(String input) {
	       
	        return input.replace(";", "").replace("\"", "").replace("+", "").replace("=","").trim();
	    }
	
	
	
}
