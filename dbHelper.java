import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class dbHelper {
    public static void main(String[] args) throws ClassNotFoundException {
        // load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:throbo.db");
            String pathToCsv = ".\\json\\flights.csv";
            insertIntoFlights(readFromCsv(pathToCsv), connection);
   
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
   
    public static ArrayList<Flight> getFlights(){
        
    }  
}