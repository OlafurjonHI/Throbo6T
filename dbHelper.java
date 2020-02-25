import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class dbHelper {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        ArrayList<Flight> flights = new ArrayList<Flight>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:throbo.db");
            String pathToCsv = ".\\json\\flights.csv";
            insertIntoFlights(readFromCsv(pathToCsv), connection);
            flights = getFlightsFromDB(connection);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();

            } catch (SQLException e) {
                // connection close failed.
                System.err.println("+" + e);
            }
        }

        
        System.out.println(flights.get(0).toString());
        System.out.println(flights.get(1).toString());
    }

    public static void insertIntoFlights(String[] flights, Connection connection) throws SQLException {
        int i = 1;
        
        for (String flight : flights) {
            System.out.print(flight);
            String[] data = flight.split(",");
            if(data.length > 1) {
                String flightNr = (data[0]);
                String from = (data[1]);
                String to = (data[2]);
                String dt = (data[3]);
                String status = (data[4]);
                System.out.println(status);
                Statement stmt = connection.createStatement();
                String sql = String.format("Insert into Flights values(%d, %s, %s, %s, %s, %s)", i, flightNr, from, to, dt, status);
                System.out.println("inserting:" + sql);
                stmt.executeUpdate(sql);
                i++;
            }
        }        

    }
    public static String[] readFromCsv(String pathToCsv) throws IOException {
        String[] ret = new String[0];
        try {
            File csvFile = new File(pathToCsv);
            if (csvFile.isFile()) {
                BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));
                String row;
                String data = "";
                csvReader.readLine();
                while ((row = csvReader.readLine()) != null) {
                    data += row +" foo "; 
                }
                csvReader.close();
                String sym = "foo";
                return data.split(sym);
            }
            else {
                System.out.println("PathToCSV is not correct");
                return ret;   
            }

        } catch (Exception e) {
            System.out.println("Reading Fail: "+e.getMessage());
        }
        return ret;
    }
   
    public static ArrayList<Flight> getFlightsFromDB(Connection connection) throws SQLException, ParseException {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * from Flights";
        ResultSet rs = stmt.executeQuery(sql);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        while(rs.next()){
                String flightNr = (rs.getString(2));
                String from = (rs.getString(3));
                String to = (rs.getString(4));
                String dt = (rs.getString(5));
                String status = (rs.getString(6));
                Date date = df.parse(dt); 
                String[] meta = {""};
                flights.add(new Flight(flightNr,from,to,date,status,meta));     
        }
        return flights;

    } 
}