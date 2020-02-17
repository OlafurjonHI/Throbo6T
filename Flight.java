public class Flight {
    public static int flightId = 1;
    private String flightNo;
    private String from;
    private String to;
    private Date timePlan;
    private String status;
    
    public Flight(String fNo, String from, String to, Date timePlan, String status) {
        this.flightNo = fno;
        this.from = from;
        this.to = to;
        this.timePlan = timePlan;
        this.status = status;
        flightId++;
    }

    public int getFlightId(){
        return flightId;
    }
    public String getFlightNumber(){
        return this.flightNo;
    }
    public void SetFlightNumber(String fNo){
        this.flightNo = fNo;
    }

    public String toString(){
        return new String("FlightNumber: " + this.flightNo + "Arriving from: " + this.from + " Arriving to: " + this.to + " Timeplan: " + this.timePlan + " Status: " + this.status);
    }




    
    public static void main(String[] args) {
        
    }
}