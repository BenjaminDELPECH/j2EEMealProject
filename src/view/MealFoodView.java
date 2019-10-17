package view;

import actions.MealFoodViewActions;
import actions.UtilsActions;
import entity.MealEntity;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
public class MealFoodView implements Serializable {
    private List<MealEntity> mealFoodList;
    private MealEntity meal;

    @PostConstruct
    public void init(){
        String parameter =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("meal");
        int parameterInt = Integer.parseInt(parameter);
        meal = MealFoodViewActions.getMeal(parameterInt);
    }

    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }
}
