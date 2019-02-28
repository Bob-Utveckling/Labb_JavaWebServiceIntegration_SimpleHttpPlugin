# Labb_JavaWebServiceIntegration_SimpleHttpPlugin
A simple Http plugin written for a Java based Http Server (lab project) mainly done by Hjalmar at https://github.com/vargen2/WebServer

The purpose of the lab project was to create a Java based Http server that accesses the Http Request Response objects and makes mappings from plugin URLs to the plugins codes.
The main work was done by Hjalmar: https://github.com/vargen2 Other contributors are Maksym + Bob.

The Http server returns pages in Html, JavaScript and Css.
The plugins can be easily added to the web server since annotations and mappings are used.

This plugin is called "SimplePhrases" and accesses and returns rows from an Sqlite3 database through Html.
Some of the other Java plugins currenly writen for the server (which can be found at the main project repo) are:
- Account
- Calculator
- Login
- ServerStats
- SimpleDynamicDate
- SimplePostForm

See the prject main repo for all plugins and respective code.
