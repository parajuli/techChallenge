Installation and Configuration:


1.	Download and install Java JDK from
	  https://www.oracle.com/java/technologies/javase-downloads.html

2.	Set JAVA_HOME Environment Variable
a.	In Windows “System Properties” window, go to “Advanced” -> “Environment Variables…”
b.	Click on “New…” under “System variables”
c.	Enter JAVA_HOME as “Variable name” and the path to your Java JDK directory under “Variable value”

3.	Update System PATH
a.	In “Environment Variables” window under “System variables” select Path
b.	Click on “Edit…”
c.	In “Edit environment variable” window click “New”
d.	Type in  ‘%JAVA_HOME%\bin’

4.	Download and Install Eclipse from
    https://eclipse.org/downloads

5.	Download project from GitHub repository into your computer. You can either extract the archive file in your preferred location or leave it as it is.

6.	Open Eclipse and go to “File” -> “Open Projects from File Systems…”
a.	Under ‘Import source:’, choose either project “Directory…” or “Archive…” and click on “Finish” button

7.	To get started with REST Assured, simply add it as a dependency in pom.xml file of your Maven project (you may need to download latest Maven dependencies from the internet). You can change the version number to the one you want to use. 
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>4.3.3</version>
    </dependency>

8.	To run the project, click on ‘Run’ under “Run” menu or simply hit ‘CTRL+F11’ simultaneously on your keyboard. The result is displayed in the ‘Console’ window.
