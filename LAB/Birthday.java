from datetime import date
import Medium as M
import mysql.connector
import os
#It's version 2
#Because i've created database on cloud.
today = date.today() # To get Today's Date. db=mysql.connector.connect(host="sql6.freesqldatabase.com",user="sql6582218",p
asswd="KNAZUQReNL",database="sql6582218")
cursor=db.cursor()
print('-------------------------------------------Birthday Service and Festival-----------------------
--------')
def showData():
cursor.execute('SELECT * FROM Data')
data = cursor.fetchall()
for d in data:
print(d)
#cursor.execute('SELECT * FROM Festivals')
#data = cursor.fetchall()
#for d in data:
# print(d)
def getData():
global Email_ID
global Month
global Name
global Number
global Holiday
global Date
Holiday,Date = [],[]
Email_ID,Month,Name,Number = [],[],[],[]
#To get Birthday's table data
cursor.execute('SELECT * FROM Data')
data = cursor.fetchall()
for d in data:
Email_ID += [d[1]]

Month+=[(str(d[3])).split('-')]
Name += [d[0]]
Number += [d[2]]
#print(d)
#To get Festival's table data
cursor.execute('SELECT * FROM Festivals')
data = cursor.fetchall()
for d in data:
Holiday += [d[0]]
Date += [(str(d[1])).split('-')]
def checkBirthdayToday():
getData()
for i in range(len(Month)):
for j in range(len(Month[i])):
if Month[i][j]==str(today.month) and Month[i][j-1]==str(today.day):
print("List of Person's who have birthday today!")
print(Name[i])
M.sendMail(Name[i],Email_ID[i]) #This will send Mail to the person
M.sendWTPM(Name[i][j],Number[i]) #This will send Whatsapp Message to
the person
def checkFestivalToday():
getData()
for i in range(len(Date)):
for j in range(len(Date[i])):
if str(today.month) == Date[i][j] and str(today.day) == Date[i][j-1]:
for filename in os.listdir('.'):
if filename.startswith(Holiday[i]):
Text = open(Holiday[i]+".txt")
Text = Text.read() #Read or stored content of Holiday[i] to Text
variable. Photo = open(Holiday[i]+".png")
# The for loop will send emails to all present in table of Data. for mail in Email_ID:
M.sendFM(Text,Photo.name,mail)
# The for loop will send Whatsapp Message to all present in table of
Data.

for Number in Number:
M.sendFMWT(Number,Photo.name,Text)
Photo.close() # Closed opened file
checkFestivalToday() # Festival Function Called
checkBirthdayToday() # Birthday Function Ca