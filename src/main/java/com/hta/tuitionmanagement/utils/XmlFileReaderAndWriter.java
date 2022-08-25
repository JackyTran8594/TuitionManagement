package com.hta.tuitionmanagement.utils;

import com.hta.tuitionmanagement.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class XmlFileReaderAndWriter {

    @Value("${app.path.xmlFile:#{null}}")
    private static String pathXml;

    private XmlFileReaderAndWriter() {

    }

    public File writer(final MultipartFile file) throws IOException {
        String filename = file.getName() + Timestamp.valueOf(LocalDateTime.now()).getTime() + ".xml";
        String folder = "C:\\XmlFileBaoCao1\\";
        if (DataUtils.isNullOrEmpty(pathXml)) {
            pathXml = folder + filename;
        }
        File newFile = new File(pathXml + filename);
        FileOutputStream fos = new FileOutputStream(newFile);
        if (!Files.isDirectory(Paths.get(folder))) {
            new File(folder).mkdir();
            newFile.createNewFile();
        } else {
            newFile.createNewFile();
        }
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos)) {
            bufferedOutputStream.write(file.getBytes());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
        }
        return newFile;
    }


    public List<Student> reader(File file) throws IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        List<Student> students = new ArrayList<>();
        try (FileInputStream inputStream = new FileInputStream(file)) {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            MapStudentObjectHanderSax handler = new MapStudentObjectHanderSax();
            saxParser.parse(inputStream, handler);
            students = handler.getListData();
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            log.error(exception.getMessage(), exception);
        }
        return students;
    }


}
