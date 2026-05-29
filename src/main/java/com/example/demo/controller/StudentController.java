package com.example.demo.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Admission;
import com.example.demo.service.StudentService;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    // 🔥 VERY IMPORTANT FIX
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("photo", "document");
    }

    // ================= INDEX =================
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // ================= SAVE =================
    @PostMapping("/admission")
    public String saveAdmission(
            @ModelAttribute Admission admission,
            @RequestParam("photo") MultipartFile photo,
            @RequestParam("document") MultipartFile document,
            Model model) {

        try {
            // FILE SET manually
            admission.setPhoto(photo.getBytes());
            admission.setDocument(document.getBytes());
            admission.setDocumentName(document.getOriginalFilename());

            Admission saved = service.saveAdmission(admission);

            return "redirect:/view/" + saved.getId();

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "Error while saving");
            return "index";
        }
    }

    // ================= VIEW =================
    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {

        Admission admission = service.getAdmissionById(id);
        model.addAttribute("admission", admission);

        return "viewadmission";
    }

    // ================= PHOTO =================
    @GetMapping("/photo/{id}")
    @ResponseBody
    public byte[] getPhoto(@PathVariable int id) {
        return service.getAdmissionById(id).getPhoto();
    }

    // ================= VIEW PDF =================
    @GetMapping("/document/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable int id) {

        Admission a = service.getAdmissionById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=" + a.getDocumentName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(a.getDocument());
    }

    // ================= DOWNLOAD =================
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable int id) {

        try {
            Admission a = service.getAdmissionById(id);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);

            doc.open();

            ByteArrayOutputStream temp = new ByteArrayOutputStream();
            Document d = new Document();
            PdfWriter.getInstance(d, temp);
            d.open();

            d.add(new Paragraph("Name: " + a.getName()));
            d.add(new Paragraph("Course: " + a.getChoosecourse()));

            if (a.getPhoto() != null) {
                Image img = Image.getInstance(a.getPhoto());
                img.scaleToFit(120, 120);
                d.add(img);
            }

            d.close();

            copy.addDocument(new PdfReader(temp.toByteArray()));

            if (a.getDocument() != null) {
                copy.addDocument(new PdfReader(a.getDocument()));
            }

            doc.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=admission.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}