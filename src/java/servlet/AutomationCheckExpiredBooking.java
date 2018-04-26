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

/**
 *
 * @author Yuu
 */
@WebListener
public class AutomationCheckExpiredBooking implements ServletContextListener {

    private static ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            try {
                List<BookingDTO> listExpiredBooking = getListExpiredBooking();
                if (listExpiredBooking != null) {                    
                    updateExpiredBooking(listExpiredBooking);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(AutomationCheckExpiredBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }, 1, 1, TimeUnit.MINUTES);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }

    }

    private static List<BookingDTO> getListExpiredBooking() throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        return dao.getListExpiredBooking();
    }

    private static void updateExpiredBooking(List<BookingDTO> list) throws SQLException, ClassNotFoundException {
        BookingDAO dao = new BookingDAO();
        dao.updateExpiredBooking(list);
    }

}
