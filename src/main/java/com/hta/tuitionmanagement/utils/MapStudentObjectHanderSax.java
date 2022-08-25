package com.hta.tuitionmanagement.utils;

import com.hta.tuitionmanagement.model.Student;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MapStudentObjectHanderSax extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();

//
//    public static List<String> getListPropertiesName(Class<?> clazz) throws Exception {
//        List<String> propeties = new ArrayList<>();
//        Field[] fields = clazz.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            propeties.add(fields[i].getName());
//        }
//        return propeties;
//    }

    List<Student> listData;
    Student currentObject;

    public List<Student> getListData() {
        return listData;
    }

    public Student getCurrentObject() {
        return currentObject;
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

        if(qName.equalsIgnoreCase("KHOA_HOC")) {
            // courseId
            String courseId = attributes.getValue("MA_KHOA_HOC");
            currentObject.setCourseId(courseId);
        }

        if (qName.equalsIgnoreCase("NGUOI_LX")) {
            currentObject = new Student();
            // registration id
            String registrationId = attributes.getValue("MA_DK");
            currentObject.setRegistrationId(registrationId);
            //firstName
            String firstName = attributes.getValue("HO_TEN_DEM");
            currentObject.setFirstName(firstName);
            //lastName
            String lastName = attributes.getValue("TEN");
            currentObject.setFirstName(lastName);
            //fullName
            String fullName = attributes.getValue("HO_VA_TEN");
            currentObject.setFirstName(fullName);
        }

        if(qName.equalsIgnoreCase("HO_SO")) {
            // courseId
            String imageStr = attributes.getValue("ANH_CHAN_DUNG");
            currentObject.setImage(imageStr);
        }



    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        // end of loop
        if (qName.equalsIgnoreCase("NGUOI_LX")) {
            listData.add(currentObject);
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);

    }
}
