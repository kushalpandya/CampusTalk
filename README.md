#CampusTalk

CampusTalk is an Intranet-based academic network for students and professors. It can be deployed and configured for any college and allows students to interact with each other based on their batch and year, share content, create events, etc. Professors of college can interact with students, make important announcements, select moderators from students to manage contents.

## Detailed Description

CampusTalk is a Java Servlets/JSP webapp that runs on Intranet of college (but can be extended to be accessible over the web). It can be adapted to any college during deployment and requires minimal server setup.

During deployment of the CampusTalk, Administrator creates groups based on email IDs of students associated with college, and then during registration, only students with known email ID will be able to register to the network. Registration on CampusTalk can only be done using one of existing accounts on [Facebook](https://www.facebook.com), [Twitter](https://www.twitter.com) or [LinkedIn](http://www.linkedin.com/). Purpose of using external accounts for registration is solely for pulling personal information of user only for one time.

The webapp is single-page and thus makes extensive use of Ajax to show content to avoid multiple page reloads and provide faster user experience.

## Platforms, Tools and Technologies used

* **IDE** - Eclipse 4.2
* **Java** - JDK 1.7
* **Development OS** - Ubuntu 12.04 LTS / Windows 7
* **Third Party Libs** - [JSON](http://json.org) (for JSON in Java), [jQuery](http://jquery.com/), [JSONify](https://github.com/kushalpandya/JSONify), [Backbone](http://backbonejs.org/), [RequireJS](http://requirejs.org/), [Walrus](http://documentup.com/jeremyruppel/walrus/), [Twitter Bootstrap](http://twitter.github.com/bootstrap/), [Less](http://lesscss.org/).

## Packages

* `org.campustalk` - Main package, contains app configuration and properties.
* `org.campustalk.entity` - Top-level Entity Classes.
* `org.campustalk.sql` - Database interaction models.
* `org.campustalk.servlet` - Request handling controllers.
* `org.campustalk.util` - Utility Classes for general operations.
* `org.campustalk.security` - Security provider classes.

## Authors

* [Kushal Pandya](https://github.com/kushalpandya)
* [Faishal Saiyed](https://github.com/faishal)
* [Aaresha Vora](https://github.com/AareshaVora)
* [Shreeji Patel](https://github.com/shreejipatel)
* [Darshil Shah](https://github.com/shreejipatel)
* [Sunny Lalwani](https://github.com/sunny55339)
* [Hiral Shah](https://github.com/hiral31)
* [Ravi Verma](https://github.com/ravi1990)
* [Piyush Gupta](https://github.com/piyush90)
