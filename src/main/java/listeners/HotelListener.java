package listeners;

import models.Hotel;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author jlombardo
 */
public class HotelListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        
        String hotelName = sc.getInitParameter("hotel_name");
        String street = sc.getInitParameter("street");
        String city = sc.getInitParameter("city");
        String state = sc.getInitParameter("state");
        Hotel hotel = new Hotel(hotelName, street, city, state);
        sc.setAttribute("hotel", hotel);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }

}
