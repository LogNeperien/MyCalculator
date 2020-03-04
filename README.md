# MyCalculator


##TP2 

Create a calculator that contains a button for every digit, the operations (+, -, *, /) and "=" .
Coding constraints : <br>
1- Follow the specific layout given below (operation is twice the size in width of the result button; "0" ans "/" have the same size) <br>
2- Only one method for handling all button clicks (investigate the tag attribute) <br>
3- create the layout in XML using a LinearLayout <br>
4- Modify the layout by removing the button "=" from the XML file and adding it at runtime alongside handling its click event. <br>
![Calculatrice Image](C:/Users/D E L L/Desktop/ING4/Mobile/Calculatrice.jpg)

##TP3

Modify your calculator in order to run the "equal" click handler in a sepreate thread using this two solutions
- Handler
- Asynctask

##TP4

Use the calculator from the previous lab and modify it as follows :

1- once "=" is clicked, the app gets connected to a server app on your machine (IP address 10.0.2.2 if using the emulator), sends the operation and receives the result for display (add the uses-permission for internet in the manifest - look it up !) <br>
2- Use an options menu (menu created by default when the project is generated) to start a new activity which displays the last operationand its result. This activity should also provide space for entering a URL and a button to run a new activity which shows the given URL in a webview.

