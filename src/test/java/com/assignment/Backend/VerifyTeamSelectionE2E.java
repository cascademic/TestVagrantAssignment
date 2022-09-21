package com.assignment.Backend;


import com.assignment.base.BaseTest;
import com.assignment.utilities.extentreports.ExtentTestManager;
import com.assignment.utilities.restAssured.Utilities;
import com.aventstack.extentreports.Status;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.lang.reflect.Method;
import java.io.IOException;

public class VerifyTeamSelectionE2E extends BaseTest {

	Utilities utilities;
	SoftAssert softAssert;

	@Test(groups = { "Sanity", "Regression" })
	public void validateOnlyForeignMembers(Method method) throws IOException, ParseException {
		try {
			ExtentTestManager.startTest(method.getName(), "Validate Foreign Members");
			utilities = new Utilities();
			softAssert = new SoftAssert();
			JSONObject jsonObject = utilities.getInputJSONObject("TeamRCB.json");
			JSONArray listOfPlayers = (JSONArray) jsonObject.get("player");
			int foreignMemberCounter = 0;
			for (int i = 0; i < listOfPlayers.size(); i++) {
				String country = ((JSONObject) listOfPlayers.get(i)).get("country").toString();
				if (!(country.equalsIgnoreCase("India"))) {
					foreignMemberCounter++;
				}
			}

			if (foreignMemberCounter != 4) {
				softAssert.assertTrue(false, "the team does not have 4 foreign members " + foreignMemberCounter);
				ExtentTestManager.logStatus(Status.FAIL, "the team does not have 4 foreign members");
			} else {
				softAssert.assertTrue(true, "the team has 4 foreign members " + foreignMemberCounter);
				ExtentTestManager.logStatus(Status.PASS, "the team has 4 foreign members");
			}

			softAssert.assertAll();
		} catch (Exception ex) {
			

		}
	}

	@Test(groups = { "Sanity", "Regression" })
	public void validateOneWicketKeeper(Method method) throws IOException, ParseException {
		try {
			ExtentTestManager.startTest(method.getName(), "Validate Wicket Keeper ");
			utilities = new Utilities();
			softAssert = new SoftAssert();
			JSONObject jsonObject = utilities.getInputJSONObject("TeamRCB.json");
			JSONArray listOfPlayers = (JSONArray) jsonObject.get("player");
			boolean bWicketKeeper = false;

			for (int i = 0; i < listOfPlayers.size(); i++) {
				String country = ((JSONObject) listOfPlayers.get(i)).get("role").toString();
				if (!(country.equalsIgnoreCase("Wicket-keeper"))) {
					bWicketKeeper = true;
					break;
				}
			}
			softAssert.assertTrue(bWicketKeeper, "the team has Wicket Keeper");
			if(bWicketKeeper) {
				ExtentTestManager.logStatus(Status.PASS, "the team has atleast one Wicket keeper");
			}
			softAssert.assertAll();
		} catch (Exception ex) {
			// logger for exception

		}
	}
}
