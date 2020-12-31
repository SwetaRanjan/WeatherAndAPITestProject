package com.ndtv.pageObjects;


import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ndtv.testUtils.TestUtil;

public class HomePage {

	WebDriver driver;
	
	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='notif_box']//span[text()='Breaking']")
	public WebElement txt_Breaking;
	
	@FindBy(xpath = "//div[@class='notif_box']")
	public WebElement notification_box;

	@FindBy(xpath = "//*[@id='h_sub_menu']")
	private WebElement link_submenu;

	@FindBy(xpath = "//a[text()='WEATHER']")
	private WebElement link_weather;
	

	public void click_submenu() throws HeadlessException, IOException, AWTException {
		TestUtil.Wait(link_submenu, 20);
		link_submenu.isDisplayed();
		link_submenu.click();
	}
	
	public void click_weather_link() throws HeadlessException, IOException, AWTException {
		TestUtil.Wait(link_weather, 20);
		link_weather.isDisplayed();
		link_weather.click();
	}
}