package com.rent.mewbook.controller;
 
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import com.rent.mewbook.java.*;
import com.rent.mewbook.mysql.*;

@Controller
public class MewbookController 
{
	String message = "Welcome to Spring MVC!";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String str
	(
	) 
	{
		return "hello";
	}
	
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public ResponseEntity<String> bar
	(
	) 
	{
	    final HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(MediaType.parseMediaType( "application/json" ));
	    return new ResponseEntity<String>("{\"test\": \"jsonResponseExample\"}", httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/pdf", method = RequestMethod.GET)
	public ModelAndView getpdf
	(
	) 
	{
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("generatorPdf");
		return mv;
	}
	
	@RequestMapping(value = "/pdf", method = RequestMethod.POST, produces = "application/pdf")
	public ResponseEntity<byte[]>  bartoo
	(
		@RequestParam
		(value = "o",  required = false, defaultValue = "o") String o,
		@RequestParam
		(value = "oo",   required = false, defaultValue = "oo") String oo,
		@RequestParam
		(value = "ooo", required = false, defaultValue = "ooo") String ooo,
		@RequestParam
		(value = "oooo",  required = false, defaultValue = "oooo") String oooo
	)   throws IOException
	{
		pdfbox pdf = new pdfbox();
		pdf.writeText(o, 26, 220, 750);
		pdf.writeText(oo, 16, 80, 700);
		pdf.writeText(ooo, 16, 80, 650);
		pdf.writeText(oooo, 80, 600);

	    final HttpHeaders httpHeaders = new HttpHeaders();
	    httpHeaders.setContentType(MediaType.parseMediaType( "application/pdf" ));
	    //downloadABLE
	    //String filename = "output.pdf";
	    //httpHeaders.setContentDispositionFormData(filename, filename);
	    //httpHeaders.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    return new ResponseEntity<byte[]>(pdf.getBytePdf(), httpHeaders, HttpStatus.OK);
	
	}
	
	@RequestMapping("/hello")
	public ModelAndView showMessage
	(
		@RequestParam
		(value = "name", required = false, defaultValue = "World") String name
	) 
	{
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup
	(
	) 
	{
		System.out.println("in controller");
 
		ModelAndView mv = new ModelAndView("signup");
		return mv;
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public String signupDB
	(
		@RequestParam
		(value = "email",  required = false, defaultValue = "test@test.test") String email,
		@RequestParam
		(value = "name",   required = false, defaultValue = "test") String name,
		@RequestParam
		(value = "gender", required = false, defaultValue = "1") String gender,
		@RequestParam
		(value = "phone",  required = false, defaultValue = "0987645321") String phone

	) 
	{
		ConnMysql conn = new ConnMysql();
		conn.connection();
		conn.queryUpdate("INSERT INTO `member_info` VALUES( NULL, '" + email + "', '" + name + "', " + gender + ", '" + phone + "', NULL, 1, 1 )");
		conn.close();
		
		return "ok";
	}
	
	@RequestMapping("/insertPage")
	public ModelAndView insertPage
	(
	) 
	{
		System.out.println("in controller insert");
 
		ModelAndView mv = new ModelAndView("insertPage");
		return mv;
	}
	
	@RequestMapping(value = "/insertValue", method = RequestMethod.GET)
	public ModelAndView insertValueGET
	(
	)
	{
		ModelAndView mv = new ModelAndView("insertCompelete");
		return mv;
	}
	
	@RequestMapping(value = "/insertValue", method = RequestMethod.POST)
	@ResponseBody
	public String insertValuePOST
	(
		@RequestParam
		(value = "fn", required = false, defaultValue = "no") String fn,
		@RequestParam
		(value = "ft", required = false, defaultValue = "no") String ft
	) 
	{
		ConnMysql conn = new ConnMysql();
		conn.connection();
		conn.queryUpdate("INSERT INTO `facility_info` VALUES( NULL, '" + fn + "', '" + ft + "' )");
		conn.close();
		
		return "ok";
	}
}
