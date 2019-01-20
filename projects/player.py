import pandas as pd
import numpy as np
from urllib.request import urlopen
import lxml.html as lh
import requests
import csv
import re
import matches

def scrap(name, id, espn_name, yrexp):
    #web scraps using the fact that espn uses the same set up for each players
    #stats just have to know each players name and unique id
    url = "http://www.espn.com/mlb/player/stats/_/id/" + id + "/" + espn_name
    page = requests.get(url)
    doc = lh.fromstring(page.content)
    tr_ele = doc.xpath('//tr')
    #start with 5 since the other rows are not important
    #help from https://towardsdatascience.com/web-scraping-html-tables-with-python-c9baba21059
    print(name)
    start = 0
    end = 0
    if(yrexp >= 4):
        start = 5 + (yrexp - 3)
        end = 5 + (yrexp + 1)
        #list = []
        #reg
        p = re.compile(reg)
        for i in tr_ele[start:end]:
            nm = i.text_content()
            #t = p.matches(nm)
            #list.append(nm)
            #list.append('+')
            #print(list)
            if t != None:
                d = pd.Series(name + nm.group())#index=['year', 'GP', 'AB', 'R', 'H', '2B', '3B', 'HR', 'RBI', 'BB', 'SO', 'SB', 'CS', 'AVG', 'OBP', 'SLG', 'OPS'])

        print(d)    
        #csvfile = csv.writer(open('/Users/johnlitscher/Documents/players.csv', 'w'), delimiter='\n') #w indicates writing to a file
        #writer = csv.writer(csvfile)
        #csvfile.writerow(name + nm)
        return
    elif(yrexp == 3 or yrexp== 2):
        for i in tr_ele[6:(yrexp + 1)]:
            nm = i.text_content()
            print(nm)
        return
  
 
        
#scrap("Trout", "30836", "mike-trout", 7)
#scrap("Mookie", "33039","mookie-betts", 4)
#scrap("Jose Ramirez", "32801", "jose-ramirez", 5)
#scrap("Manny Machado", "31097", "manny-machado", 6)
scrap("Jackie Bradley Jr", "32362", "jackie-bradley-jr", 5)
