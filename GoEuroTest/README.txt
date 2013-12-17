Compiled on:

JDK v1.7
Jersey v2.4.1
Jackson v2.2

Required libaries are in \lib

This is a sample application that calls a web service with a location. The web service returns information on this location in JSON format.
The results are then written to a CSV file.

The web service is called using Jersey, the JSON results are mapped using Jackson to POJOs. The CSV file is written to disk by a utility class.


To build the Application run the following ant command from the workspace:

ant jar

The generated jar will be the in \build\jar folder.

To run a default configuration

ant GoEuro

To run the testcases run the following ant command workspace

ant GoEuroUnitTest