package com.ftn.railwayapp.util;

import com.lowagie.text.DocumentException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.context.Context;


import java.io.*;
import java.util.HashMap;

public class PDFGenerator {

    public static String parseThymeleafTemplate(HashMap<String, Object> variables, String templatePath) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        for (String key : variables.keySet()) {
            context.setVariable(key, variables.get(key));
        }

        return templateEngine.process(templatePath, context);
    }

    public static void generatePdfFromHtml(String html, String outFilePath) throws IOException, DocumentException {
        String outputFolder = outFilePath;
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

}
