# Exchange rates project
Exchange rates application which allows to convert money amount from source currency to the target one.

# How to start application
First of all make a build my maven.
- Install Java 22
- Install maven version 3.9.x or higher https://maven.apache.org/install.html
- Call following command in the command line under the root folder:
  `mvn clean package`
- Start service by following command under the root folder:
  `java -jar target/exchangeRates-0.1.jar`
- Type CTRL + C to stop service when it is needed

# How to test application
- Start application by guid above
- Open in browser http://localhost:8080/
- Fill in `Amount`, for example, 10
- Type in field `From` EUR
- Type in field `TO` USD
- Press `Convert` button

You should see the result of conversion. 
Feel free to use other currencies. Current version is based on SWOP https://swop.cx/ free plan and conversion is supported from EUR only.

# Development tips
Project uses Lombok library. Make following steps if you are using IntelliJ IDEA:
1. Install Lombok Plugin.
- Open Preferences -> Plugins and install a Lombok plugin.
2. Enable annotation processing.
- Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors
- Select Enable annotation processing
