#Scenario 1: Entering data into the factoring calculator
1. Open the factoring calculator page.
2. Apply cookies.
3. Enter the data (Invoice amount, Advance rate, Interest rate,
   Payment term, Commission fee).
4. Check that the entered data is displayed correctly.

#Scenario 2: Result displays correct value
1. Enter the data (Invoice amount, Advance rate, Interest rate,
   Payment term, Commission fee).
2. Submit entered data.
3. Control that calculated value is correct.

#Scenario 3: Result display correctness
1. Enter the data (Invoice amount, Advance rate, Interest rate,
   Payment term, Commission fee).
2. Submit entered data.
3. Check that result is correctly displayed and is not
out of bounds (based on the location of the unit). 

#Scenario 4: Errors presentation when invalid input
1. Enter the -1 value into field.
2. Check for error representation.
3. Enter the 0 value into field.
4. Check for error representation.
5. Enter the 1 value into field.
6. Check for error representation.
7. Repeat for every input (not select) field.

#Scenario 5: Errors type when invalid input
1. Enter the -1 value into field.
2. Checking for the correct error output.
3. Enter the 0 value into field.
4. Checking for the correct error output.
5. Enter the 1 value into field.
6. Checking for the correct error output.
7. Enter the empty string into field.
8. Checking for the correct error output.
9. Repeat for every input (not select) field.

#Scenario 6: Errors type when special invalid input
1. Enter a value with three decimal places.
2. Checking for the correct error output.
3. Enter a value with two decimal places.
4. Checking for the correct error output.
5. Enter the space value into field.
6. Checking for the correct error output.
7. Enter the string value into field.
8. Checking for the correct error output.
9. Repeat for every input (not select) field.

10. For interest rate enter value more than 20.
11. Checking for the correct error output.
12. For commission fee enter negative values.
13. Checking for the correct error outputs.

#Scenario 7: Checking the limits for maximum and minimum values.
1. Alternately insert values increasing the order of
numbers in the field until an error appears
2. Alternately insert values lowering the order of
numbers in the field until an error appears
(Didn't manage to finish)