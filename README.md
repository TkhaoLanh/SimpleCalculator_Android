# SimpleCalculator
## Overview
Calculator app is a special version of a standard calculator with some extra features. The calculator has two versions, standard and advance.

### Part 1 Stander Version implementation
• All operands used in this calculator have one digit only, so for example we can do 4 + 3 or 8 7 but not 11 2.
• All operators in this calculator are calculated in the order of entering from left to right (no priority), for example 11 - 2 * 4 = 36, 3 + 5 4 * 2 =8
#### Part 1 Stander Version UI
• Simple Calculator app has one activity only.
• Designed the UI using Linear Layout ONLY, add those components for the stander verson
• 10 buttons for the digits (0 9)
• 5 buttons for the operators (+ --* /
• Clear button
• Text View for the result
#### Part 1 Stander Version logic
• Simple Calculator app need a logic to be placed in a Java Class called Calculator.
• Managed the logic of this calculator in Calculator class not in Main Activity class.

### Part 2 Advance Version implementation
• Clicking on "Advance With History" button moves the calculator to an advance mode, where the calculator could save the history of operators in a Text View.
• When the user click on that button, its title must be changed to be "Standard No History"
• After each calculation, and when the user click on "=" button, the new operator inserted in the history text.
• If the user return to standard version, the history text must disappear.

## Prerequisites
- Android
- Java
