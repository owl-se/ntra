<b>How to launch</b>
* Triangle base test, <i>mvn clean verify -Dtest=BaseTest</i>
* Triangle contract test, <i>mvn clean verify -Dtest=BaseTest</i>
* Display allure report for it, <i>mvn allure:serve</i>

<b>Test cases</b>
* Verify it's possible to create equilateral triangle
* Verify it's possible to view created triangle 
* Verify perimeter calculation for triangle 
* Verify area calculation for equilateral triangle 
* Verify it's possible to delete triangle 
* Verify it's not possible to create a triangle with zero values 
* Verify it's not possible to store more than 10 entities

<b>Bugs</b>
<p>1) It is possible to create more than 10 entities</p>
<i>Steps to reproduce:</i>
<p>- Create 10 triangles via request to "/triangle"</p>
<p>- Try to add one more</p>
<p><i>expected:</i></p>
<p>error 422</p>
<p><i>actual:</i></p>
<p>it's possible to create 10+ elements</p>

---
<p>2) It is possible to create a triangle with zero values </p>
<i>Steps to reproduce:</i>
<p>- Send post request to "/triangle" with all input values as 0</p>
<p><i>expected:</i></p>
<p>error 422</p>
<p><i>actual:</i></p>
<p>it's possible to create triangle zero length sides </p>
