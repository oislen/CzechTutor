
import os
import requests
import zipfile
import io
import pandas as pd

# set the destination data directory
data_dir="E:\\GitHub\\CzechTutor\\src\\main\\resources\\data"

def download_data(
        data_dir, 
        zip_file_url="https://www.manythings.org/anki/ces-eng.zip"
        ):
    """
    Downloads the Anki ces to english phrase data from manythings.org

    Parameters
    ----------
    data_dir : str
        The data directory where the Anki ces to english phrase data should be stored on disk
    zip_file_url : str
        The url to pull the Anki ces to english phrase data from 
    """
    # download and extract files
    headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36'}
    r = requests.get(zip_file_url, headers=headers)
    z = zipfile.ZipFile(io.BytesIO(r.content))
    z.extractall(data_dir)

def process_data(data_dir):
    """
    Processes and cleans the Anki ces to english phrase data for use in the CzechTutor App

    Parameters
    ----------
    data_dir : str
        The data directory where the Anki ces to english phrase data should be stored on disk
    """
    # load text data
    data = pd.read_csv(os.path.join(data_dir, "ces.txt"), sep="\t", names=["EN","CZ","REF"], header=None)
    # create ID column
    data['ID'] = data.index+1
    # calculate average phrase length
    data["LEN"] = data[['EN','CZ']].apply(lambda series: series.str.split(' ').str.len()).mean(axis=1)
    # generate difficulty groups
    data["LVL"] = pd.cut(x=data["LEN"], bins=[1, 3, 5, 7, 11, 50], right=False, labels=["Beginner", "Easy","Medium","Hard", "Expert"], include_lowest=True)
    # set column order
    data = data[["ID","EN","CZ","REF"]]
    # save data as .csv file
    data.to_csv(os.path.join(data_dir, "ces.csv"), index=False)