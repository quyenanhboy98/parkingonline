/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import entity.BookingDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.BookingDAO;
import model.UserDetailsDAO;
import utils.Mailer;

/**
 *
 * @author Yuu
 */
@WebListener
public class AutomationCheckBookingLockedPark implements ServletContextListener {

    private static ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                List<BookingDTO> listBookingLockedPark = getListBookingLockedPark();
                if (listBookingLockedPark != null) {
                    for (BookingDTO booking : listBookingLockedPark) {
                        if (updateBookingLockedPark(booking)) {
                            Mailer.sendBookingLockedPark(getUserEmail(booking.getPhone()), booking.getParkName());
                        }
                    }

                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AutomationCheckBookingLockedPark.class.getName()).log(Level.SEVERE, null, ex);
            }
        }, 1, 1, TimeUnit.MINUTES);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }

    }

    private static List<BookingDTO> getListBookingLockedPark() throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getListBookingLockedPark();
    }

    private static String getUserEmail(String phone) throws SQLException, ClassNotFoundException {
        UserDetailsDAO dao = new UserDetailsDAO();
        return dao.getUserEmail(phone);
    }

    private static boolean updateBookingLockedPark(BookingDTO booking) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.updateBookingLockedPark(booking);
    }
}
