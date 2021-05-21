package kr.ac.jejunu;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class UserRequestListner implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("************ request destroy **************");

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("************ request init **************");

    }
}
