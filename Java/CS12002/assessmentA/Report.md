# Report

## Test Plan

Included in my source code submission

### 1. Adding Incident to the queue

#### 1.1 Adding non-priority incident

    When a non-priority incident is added to the queue, it should be added to the end of the queue. The incident should be retrievable in the order it was added.
    
##### Test Cases

- Incident Type: Medical
- Incident District: North
- Incident Priority: false

##### Expected Result

    The 

#### 1.2. Adding priority incident

    When a priority incident is added to the queue, it should be added to the front of the queue. The priority incident should be retrievable before any non-priority incidents.

##### Test Cases

- Incident Type: Medical
- Incident District: North
- Incident Priority: true

##### Expected Result

    The 

#### 1.3. Adding null-state/empty incident

    When a null-state or empty incident is added to the queue, it should not be added to the queue. The method should handle this gracefully without throwing an exception.

##### Test Cases

## AI Usage Statement

In my submission, I had only used AI
