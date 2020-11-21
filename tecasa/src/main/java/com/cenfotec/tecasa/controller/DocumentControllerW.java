package com.cenfotec.tecasa.controller;

import com.cenfotec.tecasa.domain.Activity;
import com.cenfotec.tecasa.domain.Workshop;
import com.cenfotec.tecasa.service.ActivityService;
import com.cenfotec.tecasa.service.WorkshopService;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/readWorkshops/reportWord/{id}")
public class DocumentControllerW {
    @Autowired
    WorkshopService workshopService;
    @Autowired
    ActivityService activityService;

    private Optional<Workshop> getWorkshop(Long id){
        return workshopService.findById(id);
    }

    @GetMapping
    public void getGeneratedDocument(@PathVariable("id") Long id, HttpServletResponse response) throws IOException{
        Workshop workshop = getWorkshop(id).get();
        response.setHeader("Content-disposition", "attachment; filename=TeCaSaWorkshop-" + workshop.getName() + "_" + workshop.getId() + ".docx");

        XWPFDocument documentWord = new XWPFDocument();

        XWPFParagraph title = documentWord.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Workshop " + workshop.getName() + "#" + workshop.getId());
        titleRun.setColor("0a0a0a");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        XWPFParagraph subTitle = documentWord.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("Objective: " + workshop.getObjective());
        subTitleRun.setColor("0a0a0a");
        subTitleRun.setFontFamily("Courier");
        subTitleRun.setFontSize(16);
        subTitleRun.setTextPosition(20);
        subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

        XWPFParagraph sectionTitle = documentWord.createParagraph();
        XWPFRun sectionTRun = sectionTitle.createRun();
        sectionTRun.setText("Author: " + workshop.getAuthor());
        sectionTRun.setColor("0a0a0a");
        sectionTRun.setBold(true);
        sectionTRun.setFontFamily("Courier");

        XWPFParagraph sectionSubTitle = documentWord.createParagraph();
        XWPFRun sectionSubTitleRun = sectionSubTitle.createRun();
        sectionSubTitleRun.setText("Workshop duration: " + workshop.getExecutionTime());
        sectionSubTitleRun.setColor("0a0a0a");
        sectionSubTitleRun.setBold(true);
        sectionSubTitleRun.setFontFamily("Courier");

        XWPFParagraph sectionSubTitle2 = documentWord.createParagraph();
        XWPFRun sectionSubTitleRun2 = sectionSubTitle2.createRun();
        sectionSubTitleRun2.setText("Category: " + workshop.getCategory());
        sectionSubTitleRun2.setColor("0a0a0a");
        sectionSubTitleRun2.setBold(true);
        sectionSubTitleRun2.setFontFamily("Courier");

        XWPFParagraph sectionSubTitle3 = documentWord.createParagraph();
        XWPFRun sectionSubTitleRun3 = sectionSubTitle3.createRun();
        sectionSubTitleRun3.setText("Tags: " + workshop.getKeyWords());
        sectionSubTitleRun3.setColor("0a0a0a");
        sectionSubTitleRun3.setBold(true);
        sectionSubTitleRun3.setFontFamily("Courier");

        XWPFTable activityTable = documentWord.createTable();
        activityTable.setCellMargins(180,250,180,250);
        activityTable.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(6000));
        activityTable.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(2000));

        XWPFTableRow activityTRow = activityTable.getRow(0);
        activityTRow.getCell(0).setText("Id");
        activityTRow.addNewTableCell().setText("Title");
        activityTRow.addNewTableCell().setText("Description");
        activityTRow.addNewTableCell().setText("Public text");
        activityTRow.addNewTableCell().setText("Duration");

        for(Activity obj: workshop.getActivities()){
            XWPFTableRow activityTableRow = activityTable.createRow();
            activityTableRow.getCell(0).setText(obj.getId().toString());
            activityTableRow.getCell(1).setText(obj.getaName());
            activityTableRow.getCell(2).setText(obj.getDescription());
            activityTableRow.getCell(3).setText(obj.getVisibleText());
            activityTableRow.getCell(4).setText(obj.getaDuration());
        }

        documentWord.write(response.getOutputStream());
    }
}
