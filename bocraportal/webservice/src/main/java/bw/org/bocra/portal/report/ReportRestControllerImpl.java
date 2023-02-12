// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by SpringWSImpl.java.vsl in andromda-webservices.
//
package bw.org.bocra.portal.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@CrossOrigin()
public class ReportRestControllerImpl extends ReportRestControllerBase {

    public ReportRestControllerImpl() {

        super();
    }

    @Override
    public ResponseEntity<?> handleCreateWordDocument(Map data) {
        try {
            XWPFDocument document = new XWPFDocument();

            System.out.println(data.entrySet());

            System.out.println(data.get("formName"));
            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setText(data.get("formName").toString());
            titleRun.addBreak();

            ArrayList<HashMap> reportElements = (ArrayList<HashMap>) data.get("reportElements");

            for (HashMap element : reportElements) {
                ArrayList<HashMap> charts = (ArrayList<HashMap>) element.get("charts");

                for (HashMap chart : charts) {

                    String image64 = (String) chart.get("image");

                    image64 = image64.substring(image64.indexOf(','));

                    byte[] imageData = Base64.getDecoder().decode(image64);
                }
            }

            System.out.println(reportElements);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.write(out);
            out.close();
            document.close();

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType
                            .parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"tester.docx\"")
                    .body(new ByteArrayResource(out.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}