# BerlinTime #

Simple example project to create a BerlinTime formatter
	 
Run
------------------
Please use the following command to compile the project and run it with a simple argument of 18:49:58 ( hh:mm:ss )
It is important to note the quotes around the run command and it's arguments.

    $ activator clean compile
    $ activator "run 18:49:58"
    > test	 
	
Test
------------------
The unit tests can be run using the following command

	$ activator test


Test a single TestSuite
------------------
In order to execute a single test please use the following syntax

	$ activator test-only package.subpackage.Class
	
Package 
-------------------
Package the library

	$ activator clean compile package

Execute Jar package
-------------------

	$ java -jar berlintime-0.1.jar 21:12:21
	