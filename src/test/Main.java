package test;

import actions.UtilsActions;
import entity.MealEntity;
import entity.MealFoodsEntity;
import staticObjectForFrontend.NutrientStats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static actions.MealFoodViewActions.generateNutrientStatList;
import static actions.MealFoodViewActions.getNutrientStatsFromDB;

public class Main {
    public static void main(String[] args) {
        List<NutrientStats> nutrientStatsList = new ArrayList<NutrientStats>();
        List<Tuple> tuples = getNutrientStatsFromDB();
        if (tuples != null && !tuples.isEmpty()) {
            generateNutrientStatList(nutrientStatsList, tuples);
        }
       System.out.println(nutrientStatsList);
    }
}
