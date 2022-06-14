Coverage: 84.2%  
[Jira](https://pezantish.atlassian.net/jira/software/projects/P2/boards/1)  
[Presentation Recorded](https://drive.google.com/file/d/13TaJj7Jh7g2r3GxZIcouiGv9unEqwTiR/view?usp=sharing)

#### Note: Minor edits have meant that the about page in the website may not be up to date. 
# ZMS

ZMS is an Inventory Management System, which allows staff members to manage animals within a zoo. Editors will be able to add new animals into the system, or update, delete and view existing entries. The client has assured me that password protection is not necessary, but if I have time I shall add that in. I have also created a somewhat basic website, with informational pages.
##Getting Started
Through the webpage navbar, navigate to the ZMS page. Buttons and Functionality:  
Refresh: This will refresh the table, showing all entries currently in the database.  
Search by ID: This will refresh the table, showing only the entry with ID specified in the search bar.  
Delete: This will delete the item from the database.  
Edit: This will open up the update form if not already open; it will take the place of the New Entry form.  
Add New: This will open up the create form if not already open; it will take the place of the Edit Id form.  
Create: This will create a new entry, containing values from the input boxes above.  
Update: This will update an existing entry, containing values from the input boxes above.  
## Prerequisites
- SpringBoot, with dependencies:
++ spring-boot-starter-data-jpa  
++ spring-boot-starter-validation  
++ spring-boot-starter-web  
++ spring-boot-devtools  
++ h2  
++ mysql-connector-java  
++ spring-boot-starter-test  

- Maven  

- Java JDK  

- Lombok  

- JavaScript  

- MySQL
## Installing
Install the .jar from github. Using the .jar file, run the backend. The html pages can be opened using any internet browser by following this [link](http://localhost:8080/zms.html).
Alternatively, the project can be cloned down to a local repository using "git clone https://github.com/pezantish/ProjectTwo". This allows all code to be accessible on your local device. From here, I would use an IDE such as eclipse. Using eclipse, running the tests can be performed by right clicking the zms folder and selecting "run coverage as > junit testing".  
Code can be compliled by navigating to the zms folder, and running "mvn clean package". Alternatively, "mvn clean install" will package and copy the resulting jar into the local repository. This can then be ran using "java -jar zms.jar".

## Testing
Unit testing was completed using Mockito, Mvc and JUnit.
Mockito is used when doing unit tests. Unit tests check each unit, independant of any other units it would normally interact with. Mockito is used to simulate interacting with other units, without accidentally testing other units concurrently.
Mvc is used for the controller unit and integration testing, and checks that web-requests return the expected result.
JUnit is used for service testing. It tests that the results are what was expected, within the program only. It is similar to mvc, but not web based.

## Versioning
I have used GitHub, linked below, for version control.

## Authors
Elias Sadek, Not A Real Zoo Owner: [Github](https://github.com/pezantish).

## License
This project is licensed under the MIT license.

## Acknowledgments
- Thanks to Anoush Lowton, for explaining how to do all this stuff, and helping me when I inevitably failed.
- Thanks to my cohort for helping me out and giving me ideas. I'd list names, but then someone would get jealous.
- Thanks to my Mum, for telling me I'm handsome. I certainly feel handsome now, at least. 

## Notes for future
- Git management was not were it should have been; a variety of issues on dev and main branch resulted in "emergency" patches to main, which should be avoided.  
- Jira linking to git has been performed post project. This means no commits have been smart commited, but I know how to do this.
