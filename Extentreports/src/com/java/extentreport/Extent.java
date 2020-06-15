package com.java.extentreport;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Extent {
	
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeSuite
	public void launchbrowser(){
		
		extent = new ExtentReports();
		ExtentHtmlReporter extentreporter = new ExtentHtmlReporter("Extentreport.html");
		extent.attachReporter(extentreporter);
		
		System.setProperty("webdriver.chrome.driver", "E:\\New folder (3)\\Browser\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	@Test(priority=1)
	public void google() throws IOException{
		
		
		test = extent.createTest("verify google");
		driver.get("https://www.google.com/");
		String title = driver.getTitle();
		
		if(title.equals("Google")){
			
			test.log(Status.PASS, "Testcase is passed");
		}else{
			
			test.log(Status.FAIL, "Testcase is not passed");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File("google.png");
			FileHandler.copy(sourcefile, destinationFile);
			test.addScreenCaptureFromPath("google.png");
		}
		
	}
	
	@Test(priority=2)
	public void bing() throws IOException{
		
		test = extent.createTest("verify bing");
		driver.get("https://www.bing.com/?cc=in");
		String title = driver.getTitle();
		
		if(title.equals("bing")){
			
			test.log(Status.PASS, "Testcase is passed");
		}else{
			
			test.log(Status.FAIL, "Testcase is not passed");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File sourcefile = screenshot.getScreenshotAs(OutputType.FILE);
			File destinationFile = new File("bing.png");
			FileHandler.copy(sourcefile, destinationFile);
			test.addScreenCaptureFromPath("bing.png");
					
			
		}
		
		
	}
	
	@AfterSuite
	public void closebrowser(){
		
		
		driver.close();
		extent.flush();
	}

	
}
