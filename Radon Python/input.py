import csv


def importcsv(filepath):
    # filepath = '/Volumes/0U/Admin/Desktop/DATA/TEST001.csv'
    with open(filepath, "rt") as f:
        reader = csv.reader(f)
        #print(type(reader).__name__)
        headers = []
        points = []
        i = 1
        for row in reader:
            if row:
                row = [row[0].rstrip(), row[1].rstrip()]
                if not row[0]:
                    points.append([i, row[1]])
                    i += 1
                else:
                    headers.append(row)
        return headers, points


def printcsv(csv_list):
    for line in csv_list:
        print(line)

        #print your_list