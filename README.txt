MHAG: Monster Hunter Armor Generator 
v.1.1
Author : Tifa@mh3
MHAG Project: code.google.com/mhag
Proposed MHAG code/output wiki : mhag.wetpaint.com

About MHAG:
MHAG is a FREE armor set manager for Video Game series: Monster Hunter.  It is designed for Monster Hunter Tri (Wii), and it supports Monster Hunter Portable/Freedom 3rd (PSP).  MHAG has a calculator, a viewer and a generator, making it an outstanding tool to manage and design your own armor sets. 
     1. Why MHAG:
     There are a few calculators available online already. So why am I developing MHAG?
     1) MHAG is a light-weighted FREE JAVA program, so once you have it, you have it forever.  Remember our favorite armor database set http://mhdb.xengi.org/mh3/armory.php is gone, together with our armor data.
     2) MHAG has/will have more functions.  I will complete the generator in v2.0.
     3) MHAG has/will have most accurate data.  Well, I can't say it is error-free now, but my goal is to make it as perfect as possible.  The data are all in plain text format, so it can be easily modified by anyone.  When I compiled the data, I looked up wikia site, Japanese wiki site and the wii game itself.  Both wiki sites actually have many errors. (about 70% errors are from wikia, 30% from Japanese wiki) 
     4) MHAG data can be SHARED.  With MHAG, users can easily manage and share set data.  I may use the wiki site mhag.wetpaint.com to share code data. (the site is not good to display text/html outputs. I may try to find a better place, or let me know if you know a better place)  With the set data, I will make set books for the community (in PDF). (maybe every week or two)
     5) MHAG is cross-platform, as it's completely written in Java.  Besides, it's open source. (If you are developer, you must know how to get the source code)

     2. Why Now:
     For those who know me in the game, I recently stopped playing MH3 for a while.  That's why I have time to think about doing something interesting.  I know it may be a little late to release a version for MH3.  But it will be great for MHP3/MHF3 once the game is out.

     3. Where to find/follow MHAG:
     1) Capcom Unity , Monster Hunter Tri forum & Tifa's Blogs http://www.capcom-unity.com/tifa@mh3
     2) MHAG Google Code Project: http://code.google.com/mhag
     3) (maybe) My youtube channel with tutorial, http://www.youtube.com/mh3journey
     I will also make source code packages for stable versions. (v1.0, v2.0)

History:
v.1.2 beta 1 09/01/2011:
   1.new feature : new generator panel, jewel optimization (experimental)
v.1.1c 08/27/2011:
   1.bug : combobox menu error for the 2nd talisman skill
   2.tweak gui
v.1.1b 08/11/2011:
   1.adjust talisman skill point menu for Auto-Guard
   2.bug : sometimes an armor piece cannot be selected in the calculator
v.1.1a 08/03/2011:
   1.change charm slot menu (more user friendly)
   2.adjust gui preview frame
v.1.1 08/02/2011:
   1.new feature :  New talisman system (mainly for mhp3rd), max skill point data from official strategy book
   2.bug : change output typo: SKill to Skill
   3.bug :  wrong tooltips when swtiching female/male
   4.bug : backward support to java 1.5 (fixed save & exit window)
   5.error : guild+ armor data error , vangis coat typo
v.1.0 Final Release, 01/10/2011:
   1.new feature : add backward support to java 1.5 and Mac OS
   2.new feature : add "save before exit" dialog when code list is changed.
   3.new feature : automatically keep user's data file name and code book name.  So, if MHAG finds a name different from "MyData", it memorizes it for future use. 
   4.new feature : Help panel is now more user-friendly.  It contains a list and a content panel.  All help data are automatically extracted from USAGE.txt
   5."load" in the viewer now resets preview panel.
   6.error: redundant items found in MHP3rd's charm data. deleted
v.1.0 beta 4, 01/06/2011:
   1.new feature : add support to mhp3rd (experimental); a simple version of charm system  
   2.add mhp3rd data, translation is partly based on www.mhf3.com. include code book for full sets.
   3.bug: "load to calculator" load error for low rank set or sets with empty part(s). fixed
   4.add new MHAG logo.
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
   1.bug fixed: in HTML output, the first character is missing for the activated skill names
v.1.0 beta 1, 01/01/2011: 
   1.initial release for test; It has a calculator and a viewer.

Requirement:
1.OS: any OS with JRE 1.5 or above. (tested on windows 7, linux Ubuntu and Mac Os 10.4)
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

Known Issues:
1). Current support for MHP3.(experimental)
* Translation : skill/jewels have same name translation as those in www.mhf3.com.  Check the site for the original Japanese name.
* armor pieces : all armor sets are available to all players except for one female-only set.(Nadeshiko/Kikyo set) MHAG doesn't exclude it in male mode.
* charm: current version allows flexible input of charm skill & charm skill point.  The data is based on "MHP3rd Official Strategy Book".

Acknowledgements
Many thanks to Capcom unity, Bobo's www.mhf3.com and holywoodchunk's charm information.
