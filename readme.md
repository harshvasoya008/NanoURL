README for NanoURL

In this project, I have created NanoURL - a service which will primarily shorten url for a given long url and give long url for a short url.

# Requirements:
This URL shortening system ensembles following requirements:
1. Given a URL, this service will generate a unique, shorter alias of it, called as short URL.
2. When users click on the short URL, this service will return corresponding long URL if it is existing or will give an error message, otherwise.
3. Each short URL will get expire after a predefined amount of time and will be removed from the storage.
4. Analytic report will get generated on the day-to-day basis.

# Technical Specifications:
NanoURL service utilises following technologies for the development and proper functioning:
1. Language - Java
2. Framework - Spring
3. Database - MySQL
4. Server - Apache Tomcat
5. Cache - Redis
