package view;

import actions.UtilsActions;
import actions.MealActions;
import entity.MealEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class MealView implements Serializable {
    private MealEntity meal;
    private List<MealEntity> mealList;

    public List<MealEntity> getMealList() {
        return mealList;
    }

    public void setMealList(List<MealEntity> mealList) {
        this.mealList = mealList;
    }

    @PostConstruct
    public void init() {
        meal = new MealEntity();
        mealList = MealActions.getAll();
    }

    public void edit(MealEntity item) {
        item.setEditable(true);
    }

    public void cancelEdit(MealEntity item) {
        item.setEditable(false);
    }

    public void save(MealEntity item) {
        mealList.set(mealList.indexOf(item), item);
        UtilsActions.update(item);
        item.setEditable(false);
    }

    public void delete(Object myObj, int objId) {
        mealList.remove(myObj);
        UtilsActions.delete(myObj, objId);
    }


    public MealEntity getMeal() {
        return meal;
    }

    public void setMeal(MealEntity meal) {
        this.meal = meal;
    }
}
