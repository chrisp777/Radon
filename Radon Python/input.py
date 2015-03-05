__author__ = 'Adi'
import csv

# Import .csv file into 2-D list
def importcsv(filepath):
    # filepath = '/Volumes/0U/Admin/Desktop/DATA/TEST001.csv' # debugging
    with open(filepath, "rt") as f: # open file from path
        reader = csv.reader(f) # read
        #print(type(reader).__name__) # debugging
        # empty lists to hold parts of csv
        headers = []
        points = []
        i = 1
        # iterate through rows of csv extracting data
        for row in reader:
            if row: # skip blank lines
                row = [row[0].rstrip(), row[1].rstrip()]  # remove all trailing whitespace
                if not row[0]: # data point rows contain only voltage values in second column
                    points.append([i, row[1]]) # add index to first column, voltage to second column, append to points list
                    i += 1
                else: # if first column not empty: header row -> append to header list
                    headers.append(row)
        return headers, points # return both lists

# print list of csv's imported
def printcsv(csv_list):
    for line in csv_list:
        print(line)
