package actions;

import entity.MealEntity;
import form.UserView;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class UserActions extends BasicActions implements Serializable {


    @Inject
    UserView userView;



    public void register(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userView.getUser());
        em.getTransaction().commit();
        em.close();
        userView.setSubscribing(false);


    }

    public void loggin(){
        HttpServletRequest req =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = req.getSession();
        session.setAttribute("userSession", userView.getUser());

        String loggedUrl = req.getContextPath() + "/index.xhtml";

        HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            resp.sendRedirect(loggedUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
