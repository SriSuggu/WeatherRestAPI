# Cloudassign2
Cloud Assignment 2 aim is to develop a Restful web service to host weather information.

The project is developed using J2EE (dynamic web project) in eclipse. All the weather information is stored from daily.csv into mysql database
 daily and is accessed from java classes. The project is developed using rest api-which is implemented through jersey framework. 
The methods implemented in the project are GET,POST and DELETE.

Technolgies and tools used:
Eclipse neon EE, Java Rest full Web services, Tomcat Server, MYSQL, Postmaster.

Deployment:
Deployed the project in Amazon EC2 Linux instance and executed on tomcat server.

Implementation:
*To Get All Dates Available in Weather Data*
----
  Returns json array containing all dates in weather data.

* *URL*

http://sri.sytes.net:8080/CloudAssign2/rest/service/historical

* *Method:*

  `GET`
  
* *Output Parameters:*

  JSONArray[JSONObject{"DATE":Date String(yyyyMMdd)}]
  
* *Success Response:*

  * *Code:* 200 <br />
    *Content:* [{"DATE":"20130101"},{"DATE":"20130102"},{"DATE":"20130103"},{"DATE":"20130104"},{"DATE":"20130105"},{"DATE":"20130106"},
	{"DATE":"20130107"},{"DATE":"20130108"},{"DATE":"20130109"},{"DATE":"20130110"},{"DATE":"20130111"},{"DATE":"20130112"},{"DATE":"20130113"},
	{"DATE":"20130114"},{"DATE":"20130115"},{"DATE":"20130116"},{"DATE":"20130117"},{"DATE":"20130118"},{"DATE":"20130119"},{"DATE":"20130120"},
	{"DATE":"20130121"},{"DATE":"20130122"},{"DATE":"20130123"},{"DATE":"20130124"},{"DATE":"20130125"},{"DATE":"20130126"},{"DATE":"20130127"},
	{"DATE":"20130128"},{"DATE":"20130129"},{"DATE":"20130130"},{"DATE":"20130131"},.......
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />

*To Get the Weather Data By Date*
----
  Returns json array containing all dates in weather data.

* *URL*

http://sri.sytes.net:8080/CloudAssign2/rest/service/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

http://sri.sytes.net:8080/CloudAssign2/rest/service/historical/20130102

* *Method:*

  `GET`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Float, "TMIN": Float}
  
* *Success Response:*

  * *Code:* 200 <br />
    *Content:* `{"DATE":"20130102","TMAX":29.5,"TMIN":15.0}`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  
*To Add the Weather Data By Date in Database*
----
  Returns json object containing the date whose data is added.

* *URL*
http://sri.sytes.net:8080/CloudAssign2/rest/service/historical
  
* *SAMPLE URL*

http://sri.sytes.net:8080/CloudAssign2/rest/service/historical/20190101

* *Method:*

  `POST`

* *Input Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}
  
* *Sample Input Parameters:*

  {
	"DATE":"20190901",
	"TMAX":23,
	"TMIN":2
}
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd)}
  
* *Success Response:*

  * *Code:* 201 <br />
    *Content:* `{   "DATE": "20190901" }`
 
* *Error Response:*

  * *Code:*  409 Conflict <br />
    *Content:* `{   "Error": "Duplicate entry" }`
    
*To Delete the Weather Data By Date*
----
  Returns json object containing date whose data is deleted.

* *URL*

  http://sri.sytes.net:8080/CloudAssign2/rest/service/historical/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

 http://sri.sytes.net:8080/CloudAssign2/rest/service/historical/20190901
* *Method:*

  `DELETE`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONObject{"DATE":Date String(yyyyMMdd)}
  
* *Success Response:*

  * *Code:* 200 <br />
  * *Content:* `{   "DATE": "20190901" }`
 
* *Error Response:*

  * *Code:* 404 NOT FOUND <br />
  * *Content:* `{   "Response": "No Data Deleted" }`
  
*To Forecast 7 days of Weather from Given Date*
----
  Returns json array containing json objects of predicted weather data

* *URL*

  http://sri.sytes.net:8080/CloudAssign2/rest/service/forecast/{DATE(yyyyMMdd)}
  
* *SAMPLE URL*

 http://sri.sytes.net:8080/CloudAssign2/rest/service/forecast/20190901

* *Method:*

  `GET`

* *Input Parameters:*

  Pass Date as a path paramter as shown in above URL
  
* *Output Parameters:*

  JSONArray[JSONObject{"DATE":Date String(yyyyMMdd), "TMAX": Double, "TMIN": Double}]
  
* *Success Response:*

  * *Code:* 200 <br />
  * *Content:* 
  `[{"DATE":"20190901","TMAX":80.0,"TMIN":63.0},
  {"DATE":"20190902","TMAX":79.0,"TMIN":62.0},
  {"DATE":"20190903","TMAX":79.0,"TMIN":62.0},
  {"DATE":"20190904","TMAX":79.0,"TMIN":62.0},
  {"DATE":"20190905","TMAX":79.0,"TMIN":62.0},
  {"DATE":"20190906","TMAX":78.0,"TMIN":61.0},
  {"DATE":"20190907","TMAX":78.0,"TMIN":61.0}]`
 
* *Error Response:*

  * *Code:* 400 BAD REQUEST <br />
