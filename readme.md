<b>How to launch</b>
* Triangle base test, <i>mvn clean verify -Dtest=BaseTest</i>
* Triangle contract test, <i>mvn clean verify -Dtest=BaseTest</i>
* Display allure report for it, <i>mvn allure:serve</i>

<b>Test cases</b>
* Verify it's possible to create equilateral triangle +
* Verify it's possible to view created triangle -+
* Verify perimeter calculation for triangle +
* Verify area calculation for equilateral triangle +
* Verify it's possible to delete triangle +
* Verify it's possible to create versatile triangle -
* Verify area calculation for versatile triangle -
* Verify perimeter calculation for versatile triangle -
* Verify it's not possible to create a triangle with negative sides -
* Verify contract for web-service -