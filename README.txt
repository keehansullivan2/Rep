Sean Sullivan
October 7th, 2018
Whos Repp’n

Description: Whos Repp’n is a simple app that allows its user’s to look up who their active members of congress are. The methods in which they can do this is by giving a zip code, access to their current location, or by choosing a random location. The first page, as displayed below: 
The app also includes a nice image of a government building on the front.



The app has a welcome message being displayed, and instructions on what should be provided (not that the instructions are needed because its very self explanatory). If current location is selected but permission is not granted, a notification will appear. Similarly if a invalid zip code is given, a toast will appear. After successfully setting a location you will reach a recycler view of all the congress members for that specified location, successfully handling excess house representatives. These cards within the recycler view are color coated to show democratic/republic affiliation. In smaller text, their party, phone, website, and contact information are provided. Each of these cards can be clicked to take you to a new activity, the arrow on the right is to provide a visual queue of this, however the whole card can be clicked.


Once clicked, the cards will take you to a larger display of information as well as clickable links for their website and contact information if provided. If not provided “NOT AVAILABLE” will be displayed. 
 Behind the scenes info: 

I have a text file of every single zip code within the US, that I parse into a hashes and check if contains for valid zip codes. 
I have designed a separate class for legislature info to keep thing simple and easy reference/clustering of information. 
images used were cited in the workcited
hyperlinks have their own texts boxes. 


WorkCited : 
https://www.geocod.io/docs/?shell#reverse-geocoding
https://www.youtube.com/watch?v=y2xtLqP8dSQ
https://codinginflow.com/tutorials/android/volley-simple-get-request
https://medium.com/@eugenebrusov/using-of-constraintlayout-to-build-out-cardview-with-material-design-e111e64575c2
https://www.101apps.co.za/articles/passing-data-between-activities.html
https://www.youtube.com/watch?v=JlMxbTMutkA
http://e-mats.org/2016/01/android-changing-the-title-of-an-activity-settitle-works-androidlabel-does-not/
https://alltechsolution.wordpress.com/2012/06/17/how-do-i-make-links-in-a-textview-clickable/
https://cdn3.iconfinder.com/data/icons/buttons/512/Icon_22-512.png
