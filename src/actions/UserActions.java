package actions;

import entity.UserEntity;
import view.UserView;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Named
public class UserActions implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(UserActions.class.getName());

    @Inject
    UserView userView;

    public void register(){
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("from UserEntity where username = :username");
        query.setParameter("username", userView.getUsername());
        if(query.getResultList()==null || query.getResultList().isEmpty()){
            createUser(em);
        }else{
            String errorMsg= "Ce nom d'utilisateur est déjà utilisé";
            addGlobalError(errorMsg);
        }
        UtilsActions.endTransaction(em);
      }

    public void loggin(){
        EntityManager em = UtilsActions.getEntityManager();
        Query query = em.createQuery("from UserEntity where username = :username and password = :password ");
        query.setParameter("username", userView.getUsername());
        query.setParameter("password", userView.getPassword());
        if(query.getResultList() == null || query.getResultList().isEmpty()){
            String errorMsg= "Problème d'identifiant ou de mot de passe";
            addGlobalError(errorMsg);
        }else{
            setSessionWithCredentials(query);
        }
        UtilsActions.endTransaction(em);
    }

    private void addGlobalError(String errorMsg) {
        ArrayList<String> errors = new ArrayList<>();
        errors.add(errorMsg);
        userView.setErrors(errors);
    }

    private void createUser(EntityManager em) {
        UserEntity newUser = new UserEntity();
        newUser.setUsername(userView.getUsername());
        newUser.setPassword(userView.getPassword());
        em.merge(newUser);
        UtilsActions.endTransaction(em);
        userView.setSubscribing(false);
    }

    private static void setSessionWithCredentials(Query query) {
        UserEntity myUser = (UserEntity)query.getResultList().get(0);

        HttpServletRequest req =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = req.getSession();
        session.setAttribute("userSession", (myUser));
        String loggedUrl = req.getContextPath() + "/index.xhtml";
        HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            resp.sendRedirect(loggedUrl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    public void loggout(){
        HttpServletRequest req =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = req.getSession();
        session.removeAttribute("userSession");

        String authenticateUrl = req.getContextPath() + "/authenticate/authenticate.xhtml";

        HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            resp.sendRedirect(authenticateUrl);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

}
