---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 c/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

   * 5. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PRIORITY_NUMBER`, `p/PRIORITY_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an attraction: `add`

Adds a attraction to the address book.

Format: `add n/NAME p/PHONE_NUMBER c/CONTACT a/ADDRESS [t/TAG]…​`

<box type="tip" seamless>

**Tip:** A attraction can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 c/johnd@example.com a/John street, block 123, #01-01`
* `add n/Betsy Crowe t/friend c/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all attractions : `list`

Shows a list of all attractions in the address book.

Format: `list`

### Editing a attraction : `edit`

Edits an existing attraction in the Maplet.

Format: `edit INDEX [n/NAME] [p/PHONE] [c/CONTACT] [a/ADDRESS] [t/TAG]…​`

* Edits the attraction at the specified `INDEX`. The index refers to the index number shown in the displayed attraction list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the attraction will be removed i.e adding of tags is not cumulative.
* You can remove all the attraction’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/9 c/johndoe@example.com` Edits the priority value and contact detail of the 1st attraction to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Golden Star t/` Edits the name of the 2nd attraction to be `Golden Star` and clears all existing tags.

### Locating attractions by name: `find`

Finds attractions whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `milk` will match `Milk`
* The order of the keywords does not matter. e.g. `Tea Milk` will match `Milk Tea`
* Only the name is searched.
* Only full words will be matched e.g. `Gold` will not match `Golden`
* Attractions matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Golden Stop` will return `Golden Studios`, `Golden Star`

Examples:
* `find Golden` returns `Golden Studios` and `Golden Fries`
* `find Coffee Tea` returns `Runald's Coffee`, `Milk Tea Central`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a attraction : `delete`

Deletes the specified attraction from Maplet.

Format: `delete INDEX`

* Deletes the attraction at the specified `INDEX`.
* The index refers to the index number shown in the displayed attraction list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd attraction in the address book.
* `find Cat Cafe` followed by `delete 1` deletes the 1st attraction in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from Maplet.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Maplet data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Maplet data are saved automatically as a JSON file `[JAR file location]/data/maplet.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, Maplet will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Maplet to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PRIORITY_NUMBER c/CONTACT a/ADDRESS [t/TAG]…​` <br> e.g., `add n/Universal Village p/4 c/UniVillage@example.com a/123, Clementi Rd, 1234665 t/movies t/leisure`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PRIORITY_NUMBER] [c/CONTACT] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/Golden Studios c/GoldenSS@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find Golden Studios`
**List**   | `list`
**Help**   | `help`
