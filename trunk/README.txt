MHAG: Monster Hunter Armor Generator 
v.1.0 beta 3
Author : Tifa@mh3
MHAG Project: code.google.com/mhag
Proposed MHAG code/output wiki : mhag.wetpaint.com

About MHAG:
MHAG is a FREE armor set manager for Video Game series: Monster Hunter.  It is designed for Monster Hunter Tri (Wii), and it will support Monster Hunter Portable/Freedom 3rd (PSP).  MHAG has a calculator, a viwer and a generator, making it an outstanding tool to manage and design your own armor sets. 
     1. Why MHAG:
     There are a few calculators available online already. So why am I developing MHAG?
     1) MHAG is a light-weighted FREE JAVA program, so once you have it, you have it forever.  Remember our favorate armor database set http://mhdb.xengi.org/mh3/armory.php is gone, together with our armor data.
     2) MHAG has/will have more functions.  Try MHAG NOW! And, I will complete the generator in v2.0.
     3) MHAG has/will have most accurate data.  Well, I can't say it is error-free now, but my goal is to make it as perfect as possible.  The data are all in plain text format, so it can be easily modified by anyone.  When I compiled the data, I looked up wikia site, japanese wiki site and the wii game itself.  Both wiki sites actually have many errors. (about 70% errors are from wikia, 30% from japanese wiki) 
     4) MHAG data can be SHARED.  With MHAG, users can easily manage and share set data.  I may use the wiki site mhag.wetpaint.com to share code data. (the site is not good to display text/html outputs. I may try to find a better place, or let me know if you know a better place)  With the set data, I will make set books for the community (in PDF). (maybe every week or two)
     5) MHAG is cross-platform, as it's completely written in Java.  Besides, it's open source. (If you are developer, you must know how to get the source code)

     2. Why Now:
     For those who know me in the game, I recently stopped playing MH3 for a while.  That's why I have time to think about doing somthing interesting.  I know it may be a little late to release a version for MH3.  But it will be greate for MHP3/MHF3 once I get data.

     3. Where to find/follow MHAG:
     1) Capcom Unity , Monster Hunter Tri forum & Tifa Blogs http://www.capcom-unity.com/tifa@mh3
     2) MHAG Google Code Project: http://code.google.com/mhag
     3) (maybe) My youtube channel with tutorial, http://www.youtube.com/mh3journey
     I will also make source code packages for stable versions. (v1.0, v2.0)

History:
v.1.0 beta 3, 01/03/2011:
   1.new feature : add new tool tips for armor pieces. tools tips contains skill names, skill points and slot information.
   2.Add cover page for HTML output.
   3.Add code books for blademaster/gunner full set.
   4.bug: "save & export" button in the viewer doesn't save code book. fixed
   5.bug: when loading code book in the viewer, MHAG claims code error in preview panel. fixed
   6.bug: debug info shows in console when using cut/paste function. deleted
   7.README.txt and about text are revised.
   8.error: skill data error found in gathering skill. fixed
v.1.0 beta 2, 01/01/2011:
   1.bug fixed: in HTML output, the first chracter is missing for the activated skill names
v.1.0 beta 1, 01/01/2011: 
   1.initial release for test; It has a calculator and a viewer.

Requirement:
1.OS: any OS with JRE
2.Screen Resolution: best for 720p or higher (1280 pixels in width)
3.CPU: (not sure, let me know if you feel slow or have any problems)
4.Browser: Google Chrome/Mozilla Firefox/Safari, Internet Explorer is not recommended

Installation:
1. Make sure your computer have JRE (Java Runtime Environment). If not, download and install JRE at http://www.java.com
2. Unzip mhag pack to a folder, then enter mhag folder.
3. Run MHAG:
     1) windows : in File Explorer, double click the icon of Mhag.jar
     2) linux : use command line "java -jar Mhag.jar" in a console.
     3) Mac OS : (not tested yet, let me know if you happen to run it on Mac)

Usage:
There are three components: a calculator, a viewer and a generator.
1. Calculator:
Output Panel: three output options (text/html/code)
     TEXT :(.txt) plain text format, same as the outputs in preview panel.
     HTML :(.html) tables in HTML format; it can be viewed in a browser.  MHAG HTML codes were tweaked in Google Chrome, and it also works nicely in Mozilla Firefox and Safari, but not Internet Explorer.
     CODE :(.code) set code used to save set information for future use.
     Data File: file name of save data.  If file name doesn't contain file type extension, MHAG automatically adds it.
     Save Button: it APPENDs set to the save data.  If save file does not exist, a NEW file will be created.

Rank/Hunter Type/Gender Options:
     Rank : low/high Rank; it resets all setup menus when rank option is swtiched.  The low rank option limits setup to low rank stuffs.
     Hunter Type : blademaster/gunner; it resets all setup menus when hunter type option is swtiched.
     Gender : male/female; it only affects the armor pieces that are only for male/female.  The corresponding armor name and its position in the menu wil be changed (not reset) when gender is switched.
     Set Name : armor set name; "Unnamed Set" by default. It can be changed to any words (best less than 40 characters).  It is not immediately shown in the preview panel until ENTER key is pressed or other setup otions are selected afterward.

Setup Panel: setup details
     At beginning, only the first column of drop-down boxes are available.  They can be used to change the armor pieces/weapon slot/charm.  Each armor piece option has tool tip for skill names, skill points and number of slots.  Move mouse over the armor options (but not click on it) to show the tips.
     When a armor piece, weapon slot or charm is selected, some additional boxes will appear on the right, depending on the number of jewel slots.  MHAG prevents users from gemming too many jewels.  When a charm is selected, users can also choose charm skills from the right skill classes.  If the charm allows two charm skills, MHAG prevents users from choosing two same skills.
     To make it concise and save space, jewel menus only show the positive skill name and skill point.  But meanwhile, each jewel option also has tool tip for jewel names and number of slots.  Put mouse over a jewel option to show the tip.
     All menu lists are sorted by alphatical order, so users can use keyboard to quickly locate the right stuffs.

Preview Panel: preview the armor set, when changing armor setup.  Use the vertical scroll bar if the armor set involves a lot of armor skills.

2. Viewer: 
Code Book Panel: 
     Load: load code book to Code List Panel.  It ONLY works for existing data file.  I didn't design a "New Book" button, because I don't want to encourage people to create a lot of  new code book .(If you really want to create one in MHAG, you can use output panel in the calcuatlor.)
     Save: it saves all the items in the code list to code book.  Warning! Mhag overwrites all the old code data. MHAG prevents user from saving code book if the code list is empty.

Export Sets Panel: This is the batch calculator.  It first saves the code book, then exports sets in text or html format.  It can be used to generate an easy-to-read set book, so users can print the sets or further convert it to PDF format.(print/PDF functions are not included)   The html codes are designed to print one set per page.

Code List Panel: displays code list; Use mouse click or up/down key to nevigate sets from the (2nd) preview panel.

Load to Calculator Button: loads the selected set to the calculator, so they can be manually modified there.

Add from Calcutalor Button: appends the set after the selected set, and moves selection to the new set.  It does not delete any set.

Remove/Cut Button: removes the selected set, and moves selection to the next set.  The last five removed sets can be retrieved later.

Paste Button: appends the DELETED set after the selected set, and moves selection to the new set. It can only retrieve at most five deleted sets.

Tips:
1). modify a set: select the set -> load to calculator -> modify it -> add from calcualtor -> select the old set (move up) -> remove 
2). swap two adjacent sets: select the upper set -> remove -> paste
3). Set rearrangement : combine remove/paste options. Warning! only 5 sets can be stored.
4). Don't FORGET to SAVE code book before exiting the program!

3. Generator: (TBD)


