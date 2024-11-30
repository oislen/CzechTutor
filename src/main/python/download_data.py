
import os
import requests
import zipfile
import io
import pandas as pd
# set destination directory
destination_directory="E:\\GitHub\\CzechTutor\\src\\main\\resources\\data"
if False:
    # set source url and 
    zip_file_url="https://www.manythings.org/anki/ces-eng.zip"
    # download and extract files
    headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}
    r = requests.get(zip_file_url, headers=headers)
    z = zipfile.ZipFile(io.BytesIO(r.content))
    z.extractall(destination_directory)
# load text data
data = pd.read_csv(os.path.join(destination_directory, "ces.txt"), sep="\t", names=["EN","CZ","REF"], header=None)
# create ID column
data['ID'] = data.index+1
# calculate average phrase length
data["LEN"] = data[['EN','CZ']].apply(lambda series: series.str.len()).mean(axis=1)
data["LVL"]= pd.qcut(x=data["LEN"] , q=3, labels=["Easy","Medium","Hard"])
# set column order
data = data[["ID","EN","CZ","REF"]]
# save data as .csv file
data.to_csv(os.path.join(destination_directory, "ces.csv"), index=False)