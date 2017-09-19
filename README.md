# shoestoremanheim

Preliminary design for Shoe Store Manheim assessment.

The framework is based on the page-object model with dependency handling by Maven and testing with Selenium and TestNG.  The sure-fire plug-in is also included to be able to run tests during build.  

  mvn clean test 

There's logic to handle firefox in local, but the default is set to Chrome through a Config implementation.  The framework is integrated with SauceLabs for iterative testing. SauceLabs gets kicked off and is able to be monitored in the following account:

  username: hongiaher
  password: java123

Using the following command:

  mvn clean test -Dhost = saucelabs -Dbrowser = {browser} -DbrowserVersion = {browserVersion} -Dplatform = {platform}

Currently (09-19-2017) TODO:
- January object and test classes can be re-factored dynamically to be generalized as 'month' by using dataproviders to feed in month names
- Add logic/xpath to handle multiple locators to identify and validate show prices
- Find multi-threading solution (sure-fire/xml)
- Need to find solution for Nordstrom content handling, WebDriverWait for shoe_image may be solution
- Dynamically integrate repetitive portions of page

