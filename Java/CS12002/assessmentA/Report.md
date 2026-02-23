# Report

## AI Usage Statement

In my submission, I had only used AI at the end of my work timeline to generate the JUnit tests in my AppTest.java, found in the src/test/ directory, as a point of test automation for the test plan if prefered, rather than manually testing each option by following the test plan. I had not utilized AI to generate any of the source code used in the program.

## Self-Evaluation

In my program, I was able to complete all of the requirements that were needed and all but two of the extra requirements, being the file saving and multiple queues. I chose not to do the file saving because I was not keen on the requirement. In terms of the multiple queues, I chose not because while I had somewhat had considered going through with the idea initially, indicated by the commented-out 'Map<String, Deque<Incidents>>' field, I backed off from the consideration that I might experience complications to the logic while working on the program. Overall, I did not find this assignment to be entirely challenging for me as it was similar to a previous work that I had done regarding the use of queues with a small amount of extensions, although I did somewhat run into some trouble regarding the use of the set methods for the trend analysis method, in which I later understood and figured out via the use of the avaliable documentation from Oracle. I had was also not sure on how to go around doing the generic SystemLog class and how to implement it, but after looking through the brief and the example usage, I was able to understand and utilize the class in my program.

## Test Plan

Included in my source code submission is a JUnit AppTest that includes unit tests for each method from my 'IncidentResponse' class, as well as this Test Plan that includes a write-up of each aspect, its test case and expected result.

### 1. Adding Incident to the queue

#### 1.1 Adding non-priority incident

    When a prompted to enter details for non-priority incident is added to the queue, the parameters are validated to ensure they are not null and are prioritized correctly (n). Once validated, it should be added to the TAIL of the queue and the incident should be retrievable in the order it was added.
    
##### Test Case

- Incident Type: Medical
- Incident District: North
- Incident Priority: n (false)

##### Expected Result

    The parameters are validated and added by creating a new 'Incident' object, adding it to the queue from the tail, following FIFO (First In, First Out) conventions. The incident is also logged to the SystemLog class

#### 1.2. Adding priority incident

    When a prompted to enter details for priority incident is added to the queue, the parameters are validated to ensure they are not null and are prioritized correctly (y). Once validated, it should be added to the front of the queue and the priority incident should be retrievable before any non-priority incidents.

##### Test Case

- Incident Type: Medical
- Incident District: North
- Incident Priority: y (true)

##### Expected Result

    The parameters are added by creating a new 'Incident' object and signalled to be prioritized in the queuefrom the 'isPriority' parameter being true, adding it to the HEAD of the queue and overriding the last priority incident. The incident is also logged to the SystemLog class

#### 1.3. Adding null/incorrect incident

    When an null-state/incorrect incident is added to the queue, the validation in the options prior to it being added should prevent from being taken and prompted to re-enter valid parameters. The option should handle this gracefully without throwing an exception.

##### Test Case

- Incident Type: Null
- Incident District: Null
- Incident Priority: abc

##### Expected Result

    The validation in the main class will prevent the User from passing the null/invalid parameters, prompting them to enter valid parameters.

### 2. Viewing Current Incidents

#### 2.1. Displaying filled queue

    The method will return any incidents that are remaining in the queue.

##### Test Case

- Incident(UUID, Timestamp, Type, District, Duration, isPriority)

##### Expected Result

    The method will return the list of all incidents currently in the queue, sorted based on its priority, shown in the format: Incident(XXXXXXXX-XXXX-XXXX, 2026-02-XXTXX:XX:XX, Type, District, Duration in Queue, isPriority).

#### 2.2. Displaying unfilled queue

    The method will return no incidents in the queue.

##### Expected Result

    The method will return only the header and no incidents, signifying that there are currently no incidents in the queue.

### 3. Assigning Incidents

#### 3.1. Assigning Incident in queue

    The app will attempt to display and remove the incident at the HEAD of the queue, based on the User's choice

##### Test Case

- Incident(UUID, Timestamp, Medical, North, 20, true)

##### Expected Result

    The method will display the HEAD incident and prompt the User whether they would like to assign the incident (y/n). If the User chooses 'y', the HEAD incident will be removed. If the user chooses 'n' or any other string, the HEAD incident will remain.

#### 3.2. Assigning No Incidents in queue

    The app will attempt to display and remove an incident from the queue while there are no existing incidents

##### Expected Result

    The method will return 'No incidents to assign' and not return any incident.

### 4. Viewing Occurred Incident Types

#### 4.1. Viewing Incident Types w/Duplicate Type

##### Test Case

- Incident(Type: "Fire", District: "North")
- Incident(Type: "Fire", District: "South")
- Incident(Type: "Medical", District: "East")

##### Expected Result

    The method will return only 'Fire' and 'Medical' as the unique occurred types due to the duplicate type of 'Fire' in two incidents

#### 4.2. Viewing Incident Types w/o Duplicate Type

##### Test Case

- Incident(Type: "Security", District: "North")
- Incident(Type: "Fire", District: "South")
- Incident(Type: "Medical", District: "East")

##### Expected Result

    The method will return 'Security', 'Fire', and 'Medical' as the unique occurred types as there are no duplicate incident types

### 5. Searching Incident Queue

#### 5.1 Searching by Type

    The method will return all incidents that match the filtered incident type (i.e. 'Security', 'Medical', etc)

##### Test Case

    - Incident(Type: "Security", District: "North", false)
    - Search Incident Type: Security

##### Expected Result

    The method will return all incidents containing the incident type: 'Security'

#### 5.2 Searching by District

    The method will return all incidents that match the filtered incident district (i.e. 'North', 'East', etc)
    
##### Test Case

    - Incident(Type: "Fire", District: "South", true)
    - Search Incident District: South

##### Expected Result

    The method will return all incidents containing the district: 'South'

#### 5.3 Searching by Type & District

    The method will search for all incidents containing the specified incident type & district

##### Test Case

    - Incident(Type: "Medical", District: "East")
    - Searched Incident Type: Medical
    - Search Incident District: East

##### Expected Result

    The method will return all incident containing the incident type: 'Medical' & district: 'East'

#### 5.4 Searching by Neither/Null

    The method will attempt to search for null-state incident types/districts

##### Test Case

- Incident Type: Null
- Incident District: Null

##### Expected Result

    The menu will prevent any null searches and prompt the User to enter a new incident type/district.

### 7. System Log

#### 7.1. Adding Object to Log

    The method will take a passed object and add it to the SystemLog

##### Test Case

- Add Incident(Type: "Security", District: "North", false)
- Add Incident(Type: "Fire", District: "South", true)
- Assign Incident(Type: "Fire", District: "South", true)

##### Expected Result

    Once an incident has been added or assigned, the specific incident will be reported, based on whether it has been added or assigned, along with a timestamp of when the action occurred

#### 7.2. Returning Non-Empty Log

    Returning the SystemLog containing no logged actions

##### Test Case

- Selecting "See System Log" option

##### Expect Case

    The SystemLog will return no logged actions

#### 7.3. Returning Empty Log

    Returning the SystemLog containing logged actions

##### Test Case

- Add Incident(Type: "Security", District: "North", false)
- Add Incident(Type: "Fire", District: "South", true)
- Assign Incident(Type: "Fire", District: "South", true)
- Selecting "See System Log" option

##### Expected Result

    The SystemLog will return the logged incident actions, indicating whether it has been added or assigned, along with its timestamp of execution.
