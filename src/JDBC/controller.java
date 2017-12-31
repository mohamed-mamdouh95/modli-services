/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

/**
 *
 * @author Administrator
 */

import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import modli.servicesApp.home;

public class controller 
{
	Connection con ; 
        ArrayList<Integer> refuesd  ;
        int refusedSoldier ;
        int sayra;
        int mktb ; 

        public int getSayra() {
            return sayra;
        }

        public int getMktb() {
            return mktb;
        }

        public void setSayra(int sayra) {
            this.sayra = sayra;
        }

        public void setMktb(int mktb) {
            this.mktb = mktb;
        }
	public controller()
	{
		try
		{
                        refuesd = new ArrayList<Integer>();
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/modli","root","root");
		}
		catch(Exception e){ System.out.println(e);}  
	}  
        
        public Connection getConnection()
        {
            return this.con;
        }
	public ResultSet selectServicesByDate (String d )
	{
		Statement stmt;
		ResultSet rs = null ;
		try 
		{
			stmt = this.con.createStatement();
			rs=stmt.executeQuery("select * from servicestoday where serviceDate = '"+d +"' order by serviceDate , serviceName , shift");
			return rs ;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs ;
		}
		
	}
	public ResultSet selectAll(String name, String orderBy){

		Statement stmt;
		ResultSet rs = null ;
		try 
		{
			stmt = this.con.createStatement();
			if(orderBy != null)
			{
				rs=stmt.executeQuery("select * from "+name +" order by "+orderBy);
				return rs ;
			}
			rs=stmt.executeQuery("select * from "+name);
			return rs ;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs ;
		}
	}
	public void findByID(String name, int id){
		Statement stmt;
		try 
		{
			stmt = this.con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from "+name+" where id ="+id);
			while(rs.next())  
			{
				System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
        public Boolean SoliderAccepted(int id , int saryaDaysCount, int mktbDaysCount , int job ,int day )
	{
		Statement stmt;
		try 
		{
			stmt = this.con.createStatement();
			int count = 0 ;
			ResultSet rs=stmt.executeQuery("select * from history where solID ="+id+" order by serviceDate");
                        if ( job == 0 )
                        {
                            while(rs.next())
                            {
                                count ++;	
                            }
                            if(count >= saryaDaysCount){
                                refuesd.add(id);
                                refusedSoldier = id ;
                                return false;
                        }
                            return true ; 
                        }
                        else{
                            long millis = System.currentTimeMillis();
                            millis = millis + ((day - 1) * (24*60*60*1000)) - (24*60*60*1000);
                            java.sql.Date yesterday =new java.sql.Date(millis);
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            while(rs.next())
                            {
                                if(rs.getString(6).equals(sdf.format(yesterday)))
                                {
                                //    System.out.println("mwgod ambar7");
                                    refuesd.add(id);
                                    refusedSoldier = id ;
                                    return false ;
                                }
                                count ++;	
                            }
                            if(count >= mktbDaysCount){
                                refuesd.add(id);
                                refusedSoldier = id ;
                                return false;
                            }

                            return true ;
                        }
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                        return false;
		}
	}
	public Boolean IsToday(int soldierID,String date )
	{
		Statement stmt ; 
		try {
			stmt = this.con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from history where solID = "+soldierID+" order by serviceDate");
                        int fetchSize = rs.getFetchSize();
			while (rs.next())
			{
                            if(date.equals(rs.getString(6)))
                            {
                                System.out.println("found");
                                    return true;
                            }

			}
			return false ; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ; 
		}		
	}
	public void findByType(String name, int type){
		Statement stmt;
		try 
		{
			stmt = this.con.createStatement();
			String str = Integer.toString(type);
			ResultSet rs=stmt.executeQuery("select * from "+name+" where type ="+ str);
			while(rs.next())  
			{
				System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet findByVacation(String name, int vac){
		Statement stmt;
		try 
		{
			ResultSet rs1=null;
			stmt = this.con.createStatement();
			String str = Integer.toString(vac);
			rs1=stmt.executeQuery("select * from "+name+" where vacGroup ="+str);
			return rs1;
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void dayOff ()
	{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		Date date = new Date();
		Statement stmt;
		try 
		{
			stmt = this.con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from vac ");
			int count = 1 ;
			java.sql.PreparedStatement preparedStmt = null ;
			while(rs.next())  
			{
			   if(sdf.format(date).equals(rs.getString(2)))  
			   {
				   preparedStmt = con.prepareStatement("UPDATE soldiers SET available = ? "+ " WHERE vacGroup = ?  ");
				   preparedStmt.setInt(1, 0);
				   preparedStmt.setInt(2, count);
				   preparedStmt.executeUpdate(); 
                                   preparedStmt = con.prepareStatement("UPDATE corporal SET available = ? "+ " WHERE vacGroup = ?  ");
				   preparedStmt.setInt(1, 0);
				   preparedStmt.setInt(2, count);
				   preparedStmt.executeUpdate();
					if(count != 1)
					{
					   preparedStmt = con.prepareStatement("UPDATE soldiers SET available = ? "+ " WHERE vacGroup = ?  ");
					   preparedStmt.setInt(1, 1);
					   preparedStmt.setInt(2, count-1);
					   preparedStmt.executeUpdate();
                                           preparedStmt = con.prepareStatement("UPDATE corporal SET available = ? "+ " WHERE vacGroup = ?  ");
					   preparedStmt.setInt(1, 1);
					   preparedStmt.setInt(2, count-1);
					   preparedStmt.executeUpdate();
					}
					if (count == 1)
					{
						preparedStmt = con.prepareStatement("UPDATE soldiers SET available = ? "+ " WHERE vacGroup = ?  ");
						preparedStmt.setInt(1, 1);
						preparedStmt.setInt(2, 5);
						preparedStmt.executeUpdate();
						preparedStmt = con.prepareStatement("UPDATE corporal SET available = ? "+ " WHERE vacGroup = ?  ");
						preparedStmt.setInt(1, 1);
						preparedStmt.setInt(2, 5);
						preparedStmt.executeUpdate();
					}
					if(count <= 4  )
					{
						Calendar c = Calendar.getInstance();
						c.setTime(new Date()); 
						c.add(Calendar.DATE, 28); // Adding 28 days
						preparedStmt = con.prepareStatement("UPDATE vac SET vDate = ? "+ " WHERE vGroup = ?  ");
						java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
						preparedStmt.setDate(1,sqlDate);
						preparedStmt.setInt(2, count -1);
						preparedStmt.executeUpdate();
						
						
					}
					if (count == 5 )
					{
						
						Calendar c = Calendar.getInstance();
						c.setTime(new Date()); 
						c.add(Calendar.DATE, 35); // Adding 28 days
						preparedStmt = con.prepareStatement("UPDATE vac SET vDate = ? "+ " WHERE vGroup = ?  ");
						java.sql.Date sqlDate = new java.sql.Date(c.getTime().getTime());
						preparedStmt.setDate(1,sqlDate);
						preparedStmt.setInt(2, 5);
						preparedStmt.executeUpdate();
						
					}
			   }
			 
			   count ++;
			   
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public ResultSet vacationToday(){
		Statement stmt;
		ResultSet rs = null;
		try 
		{
			stmt = this.con.createStatement();
			rs=stmt.executeQuery("select * from soldiers where available = 0");
			return rs ; 
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs ;
		}
		
	} 
	public ResultSet availableToday(){
		Statement stmt;
		ResultSet rs = null;
		try 
		{
			stmt = this.con.createStatement();
			rs=stmt.executeQuery("select * from soldiers where available = 1 AND exceptions = 0");
                        return rs ; 
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs ;
		}
		
	}
        public ResultSet availableCorporal(){
		Statement stmt;
		ResultSet rs = null;
		try 
		{
			stmt = this.con.createStatement();
			rs=stmt.executeQuery("select * from corporal where available = 1 AND exceptions = 0");
                        return rs ; 
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return rs ;
		}
		
	} 
	public ArrayList<Integer> convertResultSetToIDArrayList(ResultSet x)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			while(x.next())
			{
				list.add(x.getInt(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList< java.sql.Date> convertResultSetToDateArrayList(ResultSet x)
	{
		ArrayList< java.sql.Date> list = new ArrayList< java.sql.Date>();
		try {
			while(x.next())
			{
				list.add(x.getDate(6));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<Integer> convertResultSetToJobArrayList(ResultSet x)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			while(x.next())
			{
				list.add(x.getInt(4));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
        
        public void updateAvilability(int soldierId )
        {
            String updateTableSQL = "UPDATE soldiers SET available = ? WHERE id = ?";
            PreparedStatement preparedStatement;
            try {
                preparedStatement = this.con.prepareStatement(updateTableSQL);
                preparedStatement.setInt(1, 0);
                preparedStatement.setLong(2,soldierId);
                // execute insert SQL stetement
                preparedStatement .executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
        public void undoAvilability(ArrayList<Integer> sols)
        {
             String updateTableSQL = "UPDATE soldiers SET available = ? WHERE id = ?";
            PreparedStatement preparedStatement;
            try {
                for ( int i = 0 ; i < sols.size() ; i++)
                {
                    preparedStatement = this.con.prepareStatement(updateTableSQL);
                    preparedStatement.setInt(1, 1);
                    preparedStatement.setLong(2,sols.get(i));
                    // execute insert SQL stetement
                    preparedStatement .executeUpdate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	public Boolean InRest(int soldierID)
	{
		Statement stmt ; 
		try {
			stmt = this.con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from history where solID = "+soldierID);
			long millis=System.currentTimeMillis();  
		    java.sql.Date date=new java.sql.Date(millis);
		    ArrayList< java.sql.Date> dates = convertResultSetToDateArrayList(rs);
			if (rs.next())
			{
				if(dates.get(dates.size()-1).equals(date))
				{
					return true;
				}
				else if (date.compareTo(dates.get(dates.size()-1)) == 1 && ( dates.get(dates.size()-1).compareTo(dates.get(dates.size()-1))==1))
				{
					return true;
				}
				else{
					return false ; 
				}
			}
			return false ; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ; 
		}
	}
	public boolean distributeServices()
	{
        FileOutputStream fileOut;
        Boolean w = false ; 
	DateFormat df = new SimpleDateFormat("EEEE" , new Locale("ar"));
    	Date today = Calendar.getInstance().getTime();        
    	String dayName = df.format(today);
    	if (!(dayName.equals("الاثنين")))
    	{
    		return true;
    	}
        
        long startTime = System.currentTimeMillis();
		try {
			Statement statement;
			statement = con.createStatement();
			statement.executeUpdate("truncate history");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet avi = availableToday();
		ResultSet job2 = availableToday();
                ResultSet corporalAvi = availableCorporal();
		ResultSet serv = selectAll("services",null);
		ArrayList<Integer> aviList = convertResultSetToIDArrayList(avi);
		ArrayList<Integer> aviCorporal = convertResultSetToIDArrayList(corporalAvi);
		ArrayList<Integer> job = convertResultSetToJobArrayList(job2);
                ArrayList<Integer> soldiersInSerivce = new ArrayList<Integer>();

		try {
                    Random randomGenerator = new Random();
                    int index = 0 ;
                    int soldierID = 0;
                    int counter = 0 ; 
                    for ( int days = 1 ; days < 8 ; days++){
                        long millis = System.currentTimeMillis();
                        millis = millis + ((days - 1) * (24*60*60*1000));
                        java.sql.Date date = new java.sql.Date(millis);
                        while(serv.next())
                        {
        
                            for(int i=1; i<4;i++){
                                for(int j = 0 ;j <serv.getInt(3);j++ ){
                                    index = randomGenerator.nextInt(aviList.size()) ; 
                                    soldierID = aviList.get(index);
                                    while(!(SoliderAccepted(soldierID, sayra , mktb , job.get(index),days)))
                                    {
                                       
                                        updateAvilability(refusedSoldier);
                                        avi = availableToday();
                                        job2 = availableToday();
                                        aviList = convertResultSetToIDArrayList(avi);
                                        job = convertResultSetToJobArrayList(job2);
                                        if(aviList.isEmpty())
                                        {
                                            undoAvilability(soldiersInSerivce);
                                            undoAvilability(refuesd);
                                            return false;
                                        }
                                        index = randomGenerator.nextInt(aviList.size()) ; 
                                        soldierID = aviList.get(index);
                                    }
                                    
                                    String query = " insert into history (solID, servID, shift, leaderID, serviceDate)"
                                            + " values (?, ?, ?, ?, ?)";
                                    java.sql.PreparedStatement preparedStmt = con.prepareStatement(query);
                                    preparedStmt.setInt(1, soldierID); 
                                    preparedStmt.setInt (2,serv.getInt(1));
                                    preparedStmt.setInt (3, (i + (j* 3)));//3'afra
                                    if ( serv.getInt(1) == 4  || serv.getInt(1) == 7 )
                                    {
                                      preparedStmt.setInt(4, aviCorporal.get((((counter-1) + (days -1 ))% aviCorporal.size())));//7ekemdar

                                    }
                                    
                                    else{
                                      preparedStmt.setInt(4, aviCorporal.get(((counter + (days -1 ))% aviCorporal.size())));//7ekemdar

                                    }
                                    preparedStmt.setDate (5,date);
                                    preparedStmt.execute();
                                    soldiersInSerivce.add(soldierID);
                                    updateAvilability(soldierID);
                                    avi = availableToday();
                                    job2 = availableToday();
                                    aviList = convertResultSetToIDArrayList(avi);
                                    job = convertResultSetToJobArrayList(job2);
                                       if(aviList.isEmpty())
                                       {
                                            undoAvilability(soldiersInSerivce);
                                            undoAvilability(refuesd);
                                            return false;
                                        }
                                    }
                                }
                            counter ++ ;
                            }
                    serv.first();
                    serv.previous();
                    undoAvilability(soldiersInSerivce);
                    undoAvilability(refuesd);
                    
                }
                Writer writer = null;
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter("done.txt",false)); 
                    out.write("true");
                    out.close();
                } catch (IOException ex) {
                  // report
                } finally {
                   try {writer.close();} catch (Exception ex) {/*ignore*/}
                } 
                 return true;
                 
          
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
                        return false;
		}
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    undoAvilability(soldiersInSerivce);
                    undoAvilability(refuesd);
                    return false;
                }
		
	}

	public void exceptionUpdate(String milNum){
		
		Statement statement;
		try {
			statement = con.createStatement();
			statement.executeUpdate("UPDATE soldiers SET exceptions = 1 WHERE milNum = "+milNum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}}