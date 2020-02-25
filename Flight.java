import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Flight {
    public static int flightId = 1;
    private String flightNo;
    private String from;
    private String to;
    private Date timePlan;
    private String status;
    private ArrayList<String> flightMeta = new ArrayList<String>();

    public Flight(String fNo, String from, String to, Date timePlan, String status, String[] fMeta) {
        this.flightNo = fNo;
        this.from = from;
        this.to = to;
        this.timePlan = timePlan;
        this.status = status;
        for (String m : fMeta) {
            this.flightMeta.add(m);
        }
        flightId++;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return this.flightNo;
    }

    public void SetFlightNumber(String fNo) {
        this.flightNo = fNo;
    }

    public Date getTimePlan() {
        return this.timePlan;
    }

    public void setTimePlan(Date newDate) {
        this.timePlan = newDate;
    }

    public String[] getFlightMeta() {
        String[] meta = new String[this.flightMeta.size()];
        int i = 0;
        for (String m : this.flightMeta) {
            meta[i] = m;
            i++;
        }
        return meta;
    }

    public void addFlightMeta(String[] metas) {
        for (String m : metas) {
            this.flightMeta.add(m);
        }
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return new String("FlightNumber: " + this.flightNo + " Arriving from: " + this.from + " Arriving to: " + this.to
                + " Timeplan: " + df.format(this.timePlan) + " Status: " + this.status);
    }

    public static void main(String[] args) throws ParseException {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = df.format(new Date());
        Date dd = df.parse("25/02/20 11:15");
        flights.add(new Flight("AA111", "Reykjavík (REY)", "Isafjörður (ISA)", dd  , "ja", new String[]{""}));
        System.out.println(df.format(dd));
        System.out.println(flights.get(0).toString());
        
        
    }
}