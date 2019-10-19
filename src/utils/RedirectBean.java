package utils;

import actions.UserActions;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class RedirectBean {
    private static final Logger LOGGER = Logger.getLogger(RedirectBean.class.getName());

    public static void redirectToHome() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
        context.redirect("http://www.google.com");
        return;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }
}
