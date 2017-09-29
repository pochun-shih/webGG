package com.rent.mewbook.java;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.contentstream.PDFGraphicsStreamEngine;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.rendering.PageDrawer;
import org.apache.pdfbox.rendering.PageDrawerParameters;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.Vector;

public class pdfbox
{
	private PDDocument doc;
	private PDPage page;
	private PDPageContentStream content;
	
	public pdfbox() throws IOException
	{
		doc = new PDDocument();
        page = new PDPage();
        
        doc.addPage(page);
        
        content = new PDPageContentStream(doc, page);
	}
	
	public void writeText( String text, int font_size, int x, int y ) throws IOException
	{
		content.beginText();
        content.setFont(PDType1Font.HELVETICA, font_size);
        content.moveTextPositionByAmount(x, y);
        content.showText(text);
        content.endText();
	}
	
	public void writeText( String text, int x, int y ) throws IOException
	{
		content.beginText();
        content.moveTextPositionByAmount(x, y);
        content.showText(text);
        content.endText();
	}
	
	public byte[] getBytePdf() throws IOException
	{
		content.close();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		doc.save(output);
        doc.close();
		
		return output.toByteArray();
	}
}