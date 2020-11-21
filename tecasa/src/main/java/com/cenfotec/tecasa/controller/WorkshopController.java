package com.cenfotec.tecasa.controller;

import com.cenfotec.tecasa.domain.Activity;
import com.cenfotec.tecasa.domain.Category;
import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.service.ActivityService;
import com.cenfotec.tecasa.service.CategoryService;
import com.cenfotec.tecasa.service.WorkshopService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class WorkshopController {
    @Autowired
    WorkshopService workshopService;
    @Autowired
    ActivityService activityService;
    @Autowired
    CategoryService categoryService;

//---------------------------------------------------------------------------------
    private Optional<Workshop> getWorkshop(Long id){
        return workshopService.findById(id);
    }
    private Optional<Category> getCategory(Long id){
        return categoryService.findById(id);
    }
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
    private String convertTimeToString(String time1, String time2) throws ParseException {
        time2 = time2 + ":00";
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Time sqlTimeW = new Time(sdf.parse(time1).getTime());
        Time sqlTimeA = new Time(sdf.parse(time2).getTime());

        int wHours = sqlTimeW.getHours() + sqlTimeA.getHours();
        int wMinutes = sqlTimeW.getMinutes() + sqlTimeA.getMinutes();

        do {
            if (wMinutes > 59) {
                wHours++;
                wMinutes = -60;
            }
            if(wMinutes < 0){
                wMinutes = 0;
            }
        }while(wMinutes < 60);

        return "" + wHours + ":" + wMinutes + ":00";
    }

//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------


    @RequestMapping("/")
    public String home(Model model){
        return "index";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/createWorkshop", method = RequestMethod.GET)
    public String insertWorkshopView(Model model){
        model.addAttribute("category", categoryService.getAllCategories());
        model.addAttribute(new Workshop());
        return "insertWorkshop";
    }

    @RequestMapping(value = "/createWorkshop", method = RequestMethod.POST)
    public String insertWorkshopAction(Workshop workshop, Model model){
        if(workshop.getId() == null){
            workshop.setExecutionTime("00:00:00");
        }
        workshopService.saveWorkshop(workshop);
        return "index";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readWorkshops", method = RequestMethod.GET)
    public String readWorkshopsView(Model model) throws ParseException, java.text.ParseException{
        model.addAttribute("workshop", workshopService.getAllWorkshops());
        return "readWorkshops";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readWorkshops/{id}", method = RequestMethod.GET)
    public String workshopProfile(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
        Workshop workshop = getWorkshop(id).get();
        model.addAttribute("workShopTitle", workshop.getName());
        model.addAttribute("workshopAuthor", workshop.getAuthor());
        model.addAttribute("workshopDurationTime", workshop.getExecutionTime());

        model.addAttribute("activities", workshop.getActivities());

        return "workshopProfile";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readWorkshops/edit/{id}", method = RequestMethod.GET)
    public String workshopEdition(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
        Workshop workshop = getWorkshop(id).get();
        model.addAttribute("workshop", workshop);

        return "insertWorkshop";
    }
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------

    @RequestMapping(value = "/createActivity/{id}", method = RequestMethod.GET)
    public String insertActivityView(@PathVariable("id") Long id, Model model){
        model.addAttribute(new Activity());

        return "insertActivity";
    }

    @RequestMapping(value = "/createActivity/{id}", method = RequestMethod.POST)
    public String insertActivityAction(@PathVariable("id") Long id, Activity activity, Model model)
    throws ParseException, java.text.ParseException{
        Workshop workshop = getWorkshop(id).get();

        activity.setWorkshop(workshop);
        activityService.saveActivity(activity);

//        workshop.setExecutionTime(convertTimeToString(workshop.getExecutionTime(), activity.getaDuration()));

        model.addAttribute("workShopTitle", workshop.getName());
        model.addAttribute("workshopAuthor", workshop.getAuthor());
        model.addAttribute("workshopDurationTime", workshop.getExecutionTime());

        model.addAttribute("activities", workshop.getActivities());

        return "workshopProfile";
    }
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------
//---------------------------------------------------------------------------------

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String homeCategory(Model model){ return "indexCategory"; }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/createCategory", method = RequestMethod.GET)
    public String insertCategoryView(Model model){
        model.addAttribute(new Category());
        return "insertCategory";
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String insertCategoryAction(Category category, Model model){
        if(category.getId() == null) {
            category.setStatus("ACTIVE");
        }
        categoryService.saveCategory(category);
        return "indexCategory";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readCategories", method = RequestMethod.GET)
    public String readCategoriesView(Model model) throws ParseException, java.text.ParseException{
        model.addAttribute("category", categoryService.getAllCategories());
        return "readCategories";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readCategories/{id}", method = RequestMethod.GET)
    public String categoryProfile(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
        Category category = getCategory(id).get();
        model.addAttribute("categoryTitle", category.getcName());
        model.addAttribute("categoryStatus", category.getStatus());

        return "categoryProfile";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readCategories/edit/{id}", method = RequestMethod.GET)
    public String categoryEdition(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
        Category category = getCategory(id).get();
        model.addAttribute("category", category);

        return "insertCategory";
    }
//---------------------------------------------------------------------------------
    @RequestMapping(value = "/readCategories/delete/{id}", method = RequestMethod.GET)
    public String categoryDeletion(@PathVariable("id") Long id, Model model) throws ParseException, java.text.ParseException{
        categoryService.deleteCategory(id);

        model.addAttribute("category", categoryService.getAllCategories());
        return "readCategories";
    }
}
