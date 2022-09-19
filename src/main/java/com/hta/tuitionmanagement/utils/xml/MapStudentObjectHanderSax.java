package com.hta.tuitionmanagement.utils.xml;

import com.hta.tuitionmanagement.model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MapStudentObjectHanderSax extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();

    List<Student> listData;
    Student currentObject;

    private String courseId;

    public List<Student> getListData() {
        return listData;
    }

    public Student getCurrentObject() {
        return currentObject;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public void startDocument() {
        listData = new ArrayList<>();
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        currentValue.setLength(0);
        // start of loop

        if (qName.equalsIgnoreCase("NGUOI_LX")) {
            // create object
            currentObject = new Student();

        }


    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        if (qName.equalsIgnoreCase("MA_KHOA_HOC")) {
            // courseId
            this.setCourseId(currentValue.toString());

        }


        if (qName.equalsIgnoreCase("MA_DK")) {
            // registration id
            currentObject.setRegistrationId(currentValue.toString());
        }

        if (qName.equalsIgnoreCase("HO_TEN_DEM")) {
            //firstName
            currentObject.setFirstName(currentValue.toString());

        }

        if (qName.equalsIgnoreCase("TEN")) {
            // lastName
            currentObject.setLastName(currentValue.toString());

        }

        if (qName.equalsIgnoreCase("HO_VA_TEN")) {
            //fullName
            currentObject.setFullName(currentValue.toString());

        }

        if (qName.equalsIgnoreCase("ANH_CHAN_DUNG")) {
            // image
            byte[] imgByte = Base64.getDecoder().decode(currentValue.toString());
            currentObject.setImage(imgByte);
        }


        // end of loop
        if (qName.equalsIgnoreCase("NGUOI_LX")) {
            currentObject.setCourseId(this.getCourseId());
            listData.add(currentObject);
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }
}
