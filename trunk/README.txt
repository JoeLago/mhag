MHAG: Monster Hunter Armor Generator 
v.1.0 beta
Author : Tifa@mh3
MHAG Project: code.google.com/mhag

History:
v.1.0 beta 01/01/2011: initial release for test.

Requirement:
1.OS: any OS with JRE
2.Screen Resolution: best for 720p or higher (1280 pixels in width)
3.CPU: (not sure, let me know if you feel slow or have any problems)
4.Browser: Google Chrome/Mozilla Firefox/Safari, Internet Explorer is not recommended

There are three components: a calculator, a viewer and a generator.
1. Calculator:
Output Panel: three output options (text/html/code)
     TEXT :(.txt) plain text format, save format as shown in preview panel.
     HTML :(.html) html format table, viewed in a browser.
     CODE :(.code) set code used to store set information for future use.
     Data File: file name of save data.  If file name doesn't contain file type extension, MHAG automatically add it.
     Save Button: APPENDs set to the save data.  If save file does not exist, a new file will be created.

Rank/Hunter Type/Gender Options:
     Rank : low/high Rank, reset setup once rank option is swtiched.
     Hunter Type : blademaster/gunner. reset setup once hunter type option is swtiched.
     Gender : male/female.  It only affects the armor pieces that are ony for male/female.  The corresponding armor name and its position in the menu wil be changed (not reset) when gender is switched.
     Set Name : Armor set name. "Unnamed Set" by default. It can be changed to any words (best less than 40 characters).  It is not immediately shown in the preview panel until ENTER key is pressed or other setup otions are selected.

Setup Panel: setup details
     At beginning, only the first column of dropdown boxes are available.  They can be used to change the armor pieces/weapon/charm.  When any of them is selected, some additional boxes will appear on the right, depending on the number of jewel slots.  MHAG can prevent users from using too many jewels on one set.  When a charm is selected, users can also choose charm skills for the right skill classes.  If the charm allows two charm skills, MHAG can prevent users from choosing two same skills.
     Jewel menus only show the positive skill name and skill points. They also contain tool tips for jewel names and number of slots.
     All menu lists are sorted by alphatical order, so users can use keyboard to quickly locate the right stuffs.

Preview Panel: preview the armor set.  It changes along with the setup.

2. Viewer: 
Code Book Panel: 
     Load: load code book to Code List Panel.  It ONLY works for existing data file.  It is not recommended to frequently create new code book .(If you really want to create a new code book in MHAG, you can use output panel in the calcuatlor.)
     Save: save all the items in the code list to code book.  Warning! all the old code data in the orginal file will be lost.  MHAG automatically prevent user from saving code book if the code list is empty.

Output Sets Panel: This is the batch mode of calculator.  It first save the code book, then output sets in text or html format.  It is desgined to generate a set book.  The html codes are designed to print one set on each page.

Code List Panel: display code list. Mouse click or up/down key can be used to nevigate sets from the preview panel.

Load to Calculator Button: load the selected set to the calculator, so they can be manually modified.

Add from Calcutalor Button: append the set after the selected set, and move selection to the new set.  It won't delete any set.

Remove/Cut Button: remove the selected set. meanwhile the last five set inforamtions are stored.

Paste Button: append the DELETED set after the selected set, and move selection to the new set. It can only retrieve at most five deleted sets.

Tips:
1). modify a set: select the set -> load to calculator -> modify it -> add from calcualtor -> select the old set (move up) -> remove 
2). swap two adjacent sets: select the upper set -> remove -> paste
3). other set rearrangement : combine remove/paste options. Warning! only 5 sets can be stored.

3. Generator: (TBD)


