# DD2480-assignment-1

### A hypothetical anti-ballistic missile system
A DECIDE()-function was created that uses radar-tracking information (a set of planar data points and a set of conditions) to resolve several _Launch Interceptor Conditions_ (LIC's). DECIDE() then combines these with a _Logical Connector Matrix_ (LCM) in order to consider combinations of LICs and the result is used to generate a launch-unlock signal.   

### Structure
The code can be found in the __/src/main__ folder, and all the tests, every LIC has its own test, can be found in the __/src/test__ folder.

### Getting started
#### Step one
Fork or download the project.
#### Step two
The tests are written in JUnit and can be run using an IDEA of your choice or using a JUnit binary from the command-line.

##### Running the tests from the command-line
Download a JUnit standalone .jar file. Use the following makefile to run all tests.

###### Makefile
    clean:
	    rm -r bin/*
    build:
	    javac -d bin -cp [path to JUnit standalone .jar] src/main/* src/test/*
    test: build
	    java -jar [path to JUnit standalone .jar] -cp bin/ --scan-classpath

## Statement of contributions
* Arthur Carl Vignon
Your contribution
* Axel Larusson
Your contribution
* Joel Ekelöf
Your contribution
* Joel Gille Vikström
Your contribution
* Paul Roland Jacques Griesser
Your contribution
