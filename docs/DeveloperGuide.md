---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# AB-3 Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI ca be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as a example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to a `MapletParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, a object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command ca communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it ca take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `MapletParser` class creates a `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `MapletParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they ca be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the Maplet data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as a unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `Maplet`, which `Person` references. This allows `Maplet` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* ca save both Maplet data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `MapletStorage` and `UserPrefStorage`, which means it ca be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedMaplet`. It extends `Maplet` with an undo/redo history, stored internally as a `mapletStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedMaplet#commit()` — Saves the current Maplet state in its history.
* `VersionedMaplet#undo()` — Restores the previous Maplet state from its history.
* `VersionedMaplet#redo()` — Restores a previously undone Maplet state from its history.

These operations are exposed in the `Model` interface as `Model#commitMaplet()`, `Model#undoMaplet()` and `Model#redoMaplet()` respectively.

Given below is a example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedMaplet` will be initialized with the initial Maplet state, and the `currentStatePointer` pointing to that single Maplet state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th person in the Maplet. The `delete` command calls `Model#commitMaplet()`, causing the modified state of the Maplet after the `delete 5` command executes to be saved in the `mapletStateList`, and the `currentStatePointer` is shifted to the newly inserted Maplet state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitMaplet()`, causing another modified Maplet state to be saved into the `mapletStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitMaplet()`, so the Maplet state will not be saved into the `mapletStateList`.

</box>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoMaplet()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous Maplet state, and restores the Maplet to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial Maplet state, then there are no previous Maplet states to restore. The `undo` command uses `Model#canUndoMaplet()` to check if this is the case. If so, it will return a error to the user rather
tha attempting to perform the undo.

</box>

The following sequence diagram shows how a undo operation goes through the `Logic` component:

<puml src="diagrams/UndoSequenceDiagram-Logic.puml" alt="UndoSequenceDiagram-Logic" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

Similarly, how a undo operation goes through the `Model` component is shown below:

<puml src="diagrams/UndoSequenceDiagram-Model.puml" alt="UndoSequenceDiagram-Model" />

The `redo` command does the opposite — it calls `Model#redoMaplet()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the Maplet to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `mapletStateList.size() - 1`, pointing to the latest Maplet state, then there are no undone Maplet states to restore. The `redo` command uses `Model#canRedoMaplet()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the Maplet, such as `list`, will usually not call `Model#commitMaplet()`, `Model#undoMaplet()` or `Model#redoMaplet()`. Thus, the `mapletStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitMaplet()`. Since the `currentStatePointer` is not pointing at the end of the `mapletStateList`, all Maplet states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire Maplet.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

This product is aimed at individuals planning to go overseas/frequent travellers.

* has a need to keep track of a large amount of relevant locations and attractions at the locations
* prefer desktop apps over other types
* ca type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* needs to draft a suitable trip pla that stays within budget
* wants detailed information on locations of interest

**Value proposition**: manage locations of interest faster tha typical travel management sites

Maplet ca store useful information on locations and attractions:

For attractions (within the location):
1. Opening hours
2. Price
3. Rating

Attractions are grouped by locations and have activities associated with them.
### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                | I want to …​                   | So that I can…​                                                                  |
|----------|------------------------|--------------------------------|----------------------------------------------------------------------------------|
| `* * *`  | user                   | add a attraction              | record information about an attraction                                           |
| `* * *`  | user                   | delete a attraction           | remove unneeded / outdated information                                           |
| `* * *`  | user                   | list attractions               | locate details on that attraction without going through the entire list          |
| `* * *`  | user                   | list all attractions           | keep track of all attractions of interest                                        |
| `* *`    | user                   | Edit attraction details        | correct a error, or to add new information about an existing entry              |
| `* *`    | user                   | Filter attractions by distance | find the closest attraction to me                                                |
| `* *`    | user                   | Filter attractions             | find attractions that fit my specifications                                      |
| `* *`    | user                   | Add attraction to location     | group attractions by location                                                    |
| `* *`    | user                   | compare locations              | see which location better fits my needs                                          |
| `*`      | Financially aware user | Add cost (if applicable)       | See the cost related to each attraction for planning                             |
| `*`      | Trip planner           | Add opening hours              | track when the location opens for future reference                               |
| `*`      | Trip planner           | Edit opening hours             | update opening hours on new information / errors                                 |
| `*`      | Trip planner           | Check opening hours            | check if the attraction is open at the time of my intended visit                 |
| `*`      | Trip planner           | Add activities to attractions  | record the associated activities of a attraction                                |
| `*`      | Trip planner           | Sort attractions by activities | Find available attractions for the activity to be planned                        |
| `*`      | Trip planner           | create itinerary               | pla a list of activities and attractions for the day, verified by opening hours |
| `*`      | Trip planner           | edit itinerary                 | edit a list of activities and attractions for the day, verified by opening hours |
| `*`      | Trip planner           | delete itinerary               | remove a list of activities and attractions for the day                          |
| `*`      | Experienced User       | user single letter commands    | more quickly perform operations                                                  |
| `*`      | Experienced User       | export trip details            | access my trip details without opening the program                               |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Maplet` and the **Actor** is the `user`, unless specified otherwise)


**Use case: UC1 - List attractions**

**MSS**

1.  User chooses to list attractions at a location
2.  Maplet shows a list of attractions at the location

    Use case ends.

**Extensions**

* 1a. User does not specify location

    * 1a1. Maplet shows a list of all attractions
  
      Use case ends.

* 2a. The list is empty.

  Use case ends.


**Use case: UC2 - Add attraction**

**MSS**

1.  User chooses to add attraction
2.  User enters the required attraction details
3.  Maplet adds new attraction to list

    Use case ends.

**Extensions**

* 2a. Details are in the wrong format

    * 2a1. Maplet prompts the user for correct format
    * 2a2. User enters correct attraction details

      Steps 2a1-2a2 are repeated until the details entered are correct.</br>
      Use case resumes at step 3.


**Use case: UC3 - Delete attraction**

**MSS**

1.  User chooses to <u>list attractions (UC1)</u> to see index of attractions
2.  User chooses to delete a attraction in the list 
3.  User enters index of attraction to delete
4.  Maplet deletes the attraction

    Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. Maplet prompts the user for valid index
    * 3a2. User enters valid index
  
      Steps 3a1-3a2 are repeated until index entered is valid.</br>
      Use case resumes at step 4.


**Use case: UC4 - Find attraction**

**MSS**

1.  User chooses to find a attraction with a keyword
2.  User enters keyword
3.  Maplet shows a list of attractions related to keyword

    Use case ends.

**Extensions**

* 2a. No keyword is given.

    * 2a1. Maplet prompts the user for valid keyword
    * 2a2. User enters valid keyword

      Steps 2a1-2a2 are repeated until keyword entered is valid.</br>
      Use case resumes at step 3.

* 3a. The list is empty

  Use case ends.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `17` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands tha using the mouse.
4. When retrieving or saving location data, the system should respond within 2 seconds.
5. When the user filters locations based on a order (distance, rating, …), the system should take less than 3 seconds
6. The system must should be able to handle 100 simultaneous users query without degradation
7. All user data (travel plans, ratings, reviews, …) should be stored securely in the database
8. The system should require user authentication before retrieve personal data
9. The system should be able to handle 1000 users in the database without failing the performance NFRs.
10. The system should be able to handle growing number of locations and user-content
11. New user should take less tha 5 minutes to set up account and start using application’s features
12. User with disabilities should have access to all features


*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **Attraction**: A single place of interest
* **Activity**: A thing to be done at a attraction
* **Location**: A location ca contain any number of attractions
* **Itinerary**: An itenerary refers to a list of attractions to be visited, as well as the time spent at each attraction


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into a empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to a optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger tha the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
