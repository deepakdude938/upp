$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:testcases/newLead.feature");
formatter.feature({
  "name": "UPP feature",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Create Basic details with TC1",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.step({
  "name": "User is on LoginPage",
  "keyword": "Given "
});
formatter.step({
  "name": "Login to the application",
  "keyword": "Then "
});
formatter.step({
  "name": "Create new deal POC with basic details with given \u003cTcId\u003e.",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "TcId"
      ]
    },
    {
      "cells": [
        "1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Create Basic details with TC1",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Regression"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User is on LoginPage",
  "keyword": "Given "
});
formatter.match({
  "location": "com.upp.stepdefinition.DashBoardPage.user_is_on_LoginPage()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.step({
  "name": "Login to the application",
  "keyword": "Then "
});
formatter.match({
  "location": "com.upp.stepdefinition.DashBoardPage.login_to_the_application()"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.step({
  "name": "Create new deal POC with basic details with given 1.",
  "keyword": "And "
});
formatter.match({
  "location": "com.upp.stepdefinition.DashBoardPage.create_new_deal_POC_with_basic_details_with_given(java.lang.Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.afterstep({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});