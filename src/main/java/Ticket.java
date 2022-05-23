import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    /**
     * origin airport
     */
    private String origin;

    /**
     * origin city
     */
    private String origin_name;

    /**
     * destination airport
     */
    private String destination;

    /**
     * destination city
     */
    private String destination_name;

    /**
     * departure date
     */
    private String departure_date;

    /**
     * departure time
     */
    private String departure_time;

    /**
     * arrival date
     */
    private String arrival_date;

    /**
     * arrival time
     */
    private String arrival_time;

    /**
     * carrier company
     */
    private String carrier;

    /**
     * count of stops
     */
    private int stops;

    /**
     * price
     */
    private int price;


    public String getDeparture_date() {
        return departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public String getOrigin() {
        return origin;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public String getCarrier() {
        return carrier;
    }

    public int getStops() {
        return stops;
    }

    public int getPrice() {
        return price;
    }

    /**
     * default constructor
     */
    public Ticket(){

    }



    /**
     * calculate duration of the flight
     * @return duration of the flight
     * @throws ParseException
     */
    public long getDuration() throws ParseException {
        /**
         * create new date format
         */
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyHH:mm");

        /**
         * cast strings to correct date format
         */
        String departureDateString = this.getDeparture_date() + this.getDeparture_time();
        Date departureDate = formatter.parse(departureDateString);
        String arrivalDateString = this.getArrival_date() + this.getArrival_time();
        Date arrivalDate = formatter.parse(arrivalDateString);

        /**
         * calculate flight duration
         */
        return arrivalDate.getTime() - departureDate.getTime();
    }
}
