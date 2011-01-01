MHAG: Monster Hunter Armor Generator 
v.1.0 beta
Author : Tifa@mh3
MHAG Project: code.google.com/mhag
Proposed MHAG code/output wiki : mhag.wetpaint.com

About MHAG:
MHAG is a FREE armor set manager for Video Game series: Monster Hunter.  It is designed for Monster Hunter Tri (Wii), and it will support Monster Hunter Portable/Freedom 3rd (PSP)  MHAG has a calculator, a viwer and a generator, making it an outstanding tool to manage and design your own armor sets. 
     1. Why MHAG:
     There are a few calculators available online already. So why am I developing MHAG?
     1) MHAG is a light-weighted FREE JAVA program, so once you have it, you have it forever.  Remember our favorate armor database set http://mhdb.xengi.org/mh3/armory.php is gone, together with our armor data.
     2) MHAG has/will have more functions.  Try MHAG NOW! And, I will complete the generator in v2.0.
     3) MHAG has/will have most accurate data.  Well, I can't say it is error-free now, but my goal is to make it as perfect as possible.  The data are all in plain text format, so it can be easily modified by anyone.  When I compiled the data, I looked up wikia site, japanese wiki site and the wii game itself.  Both wiki sites actually have many errors. (about 70% errors are from wikia, 30% from japanese wiki) 
     4) MHAG data can be SHARED.  With MHAG, users can easily manage and share set data.  I may use the wiki site mhag.wetpaint.com to share code data. (the site is not good to display text/html outputs. I may try to find a better place, or let me know if you know a better place)  Once we got some set data, I will releasee a set book for the community (in PDF). (maybe every week or two)
     5) MHAG is cross-platform, as it's completely written in Java.  Besides, it's open source. 

     2. Why Now:
     For those who know me in the game, I recently stopped playing MH3 for a while.  That's why I have time to think about doing somthing interesting.  I know it may be a little late to release a version for MH3.  But it will be greate for MHP3/MHF3 once I get data.

     3. Where to find/follow MHAG:
     1) Capcom Unity , Monster Hunter Tri forum & Tifa Blogs www.capcom-unity.com/tifa@mh3
     2) MHAG Google Code Project: code.google.com/mhag
     3) (maybe) My youtube channel with tutorial, www.youtube.com/mh3journey
     I will also make source code packages for stable versions. (v1.0, v2.0)

History:
v.1.0 beta 01/01/2011: initial release for test; It has a calculator and a viewer.

Requirement:
1.OS: any OS with JRE
2.Screen Resolution: best for 720p or higher (1280 pixels in width)
3.CPU: (not sure, let me know if you feel slow or have any problems)
4.Browser: Google Chrome/Mozilla Firefox/Safari, Internet Explorer is not recommended

Installation:
1. Make sure your computer have JRE (Java Runtime Environment). If not, download and install JRE at www.java.com/en/download/
2. Unzip mhag pack to a folder, then enter mhag folder.
3. Run MHAG:
     1) windows : in File Explorer, double click the icon of Mhag.jar
     2) linux : in File Explorer, right click and then choose an option like "Open with Sun Java 6 Runtime".  Or alternatively, use command line "java -jar Mhag.jar" in a console.
     3) Mac OS : (not tested yet, let me know if you happen to run it on Mac)

Usage:
There are three components: a calculator, a viewer and a generator.
1. Calculator:
Output Panel: three output options (text/html/code)
     TEXT :(.txt) plain text format, same as the one shown in preview panel.
     HTML :(.html) html format table; it can be viewed in a browser.  MHAG HTML codes were tweaked in Google Chrome, and it also works fine in Mozilla Firefox and Safari, but not Internet Explorer.
     CODE :(.code) set code used to save set information for future use.
     Data File: file name of save data.  If file name doesn't contain file type extension, MHAG AUTOMATICALLY add it.
     Save Button: APPEND set to the save data.  If save file does not exist, a NEW file will be created.

Rank/Hunter Type/Gender Options:
     Rank : low/high Rank; it resets setup once rank option is swtiched.  The low rank option limits setup to low rank stuffs.
     Hunter Type : blademaster/gunner; it resets setup once hunter type option is swtiched.
     Gender : male/female; it only affects the armor pieces that are only for male/female.  The corresponding armor name and its position in the menu wil be changed (not reset) when gender is switched.
     Set Name : armor set name, "Unnamed Set" by default. It can be changed to any words (best less than 40 characters).  It is not immediately shown in the preview panel until ENTER key is pressed or other setup otions are selected afterwards.

Setup Panel: setup details
     At beginning, only the first column of dropdown boxes are available.  They can be used to change the armor pieces/weapon slot/charm.  When any of them is selected, some additional boxes will appear on the right, depending on the number of jewel slots.  MHAG can prevent users from using too many jewels on one set.  When a charm is selected, users can also choose charm skills for the right skill classes.  If the charm allows two charm skills, MHAG can prevent users from choosing two same skills.
     To make it concise and save space, jewel menus only show the positive skill name and skill point.  But meanwhile, each jewel option also has tool tip for jewel names and number of slots.  Put mouse over a jewel option to show the tip.
     All menu lists are sorted by alphatical order, so users can use keyboard to quickly locate the right stuffs.

Preview Panel: preview the armor set.  It changes along with the setup.  Use the vertical scroll bar if the armor set involves too many armor skills.

2. Viewer: 
Code Book Panel: 
     Load: load code book to Code List Panel.  It ONLY works for existing data file.  I didn't design a "New Book" button, because I don't want to encourage people to create a lot of  new code book .(If you really want to create one in MHAG, you can use output panel in the calcuatlor.)
     Save: save all the items in the code list to code book.  Warning! all the old code data in the orginal file will be lost.  MHAG prevent user from saving code book if the code list is empty.

Export Sets Panel: This is the batch calculator.  It first saves the code book, then exports sets in text or html format.  It can be used to generate a easy-to-read set book, so users can print the sets or further convert it to PDF format.(print/PDF functions are not included)   The html codes are designed to print one set per page.

Code List Panel: display code list; mouse click or up/down key can be used to nevigate sets from the (2nd) preview panel.

Load to Calculator Button: load the selected set to the calculator, so they can be manually modified there.

Add from Calcutalor Button: append the set after the selected set, and move selection to the new set.  It does not delete any set.

Remove/Cut Button: remove the selected set, and move selection to the next set.  The last five removed sets can be retrieved later.

Paste Button: append the DELETED set after the selected set, and move selection to the new set. It can only retrieve at most five deleted sets.

Tips:
1). modify a set: select the set -> load to calculator -> modify it -> add from calcualtor -> select the old set (move up) -> remove 
2). swap two adjacent sets: select the upper set -> remove -> paste
3). Set rearrangement : combine remove/paste options. Warning! only 5 sets can be stored.
4). Don't FORGET to SAVE code book before exit the program!

3. Generator: (TBD)


