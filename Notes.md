## Notes on updates

1. Issue: Duplicate weigh ticket numbers entered. This creates issues in generating the incorrect invoices. 

Resolution:
* Create a function with ticket number as a parameter. 
* This function queries the database and identifies if the same weigh ticket number already presents. 
