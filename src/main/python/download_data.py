
import os
import requests
import zipfile
import io
import pandas as pd
import argparse
import logging

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

def process_data(
        data_dir,
        data_txt_fname="ces.txt",
        data_csv_fname="ces.csv"
        ):
    """
    Processes and cleans the Anki ces to english phrase data for use in the CzechTutor App

    Parameters
    ----------
    data_dir : str
        The data directory where the Anki ces to english phrase data should be stored on disk
    data_txt_fname : str
        The file name of the ces to english text data, default is "ces.txt"
    data_csv_fname : str
        The file name of the ces to english csv data, default is "ces.csv"
    
    """
    # load text data
    data = pd.read_csv(os.path.join(data_dir, data_txt_fname), sep="\t", names=["english","czech","reference"], header=None)
    # create ID column
    data['id'] = data.index+1
    # calculate average phrase length
    data["length"] = data[['english','czech']].apply(lambda series: series.str.split(' ').str.len()).mean(axis=1)
    # generate difficulty groups
    data["level"] = pd.cut(
        x=data["length"],
        bins=[1, 3, 5, 7, 11, 50],
        right=False,
        labels=["Beginner", "Easy","Medium","Hard", "Expert"],
        include_lowest=True
        )
    # set column order
    data = data[["id","english","czech","level","reference"]]
    # save data as .csv file
    data.to_csv(os.path.join(data_dir, data_csv_fname), index=False)

def commandline_interface():
    """
    Parses commandline parameters for running the data download and process pipeline
    """
    # define argument parser object
    parser = argparse.ArgumentParser(description="Execute Anki Data Download and Process Pipeline.")
    # add input arguments
    parser.add_argument("--download", action=argparse.BooleanOptionalAction, dest="download", type=bool, default=False, help="Boolean, whether to download the Anki ces to english phrase data from manythings.org",)
    parser.add_argument("--process", action=argparse.BooleanOptionalAction, dest="process", type=bool, default=False, help="Boolean, whether to process and clean the ces to english phrase data for the CzechTutor webapp",)
    # extract input arguments
    args = parser.parse_args()
    return args

if __name__ == "__main__":
    # python download_data.py --download --process
    # set up logging
    lgr = logging.getLogger()
    lgr.setLevel(logging.INFO)
    # parse commandline parameters
    logging.info("Parsing commandline parameters ...")
    args = commandline_interface()
    # download ces eng phrase
    if args.download:
        logging.info("Downloading data ...")
        download_data(data_dir, zip_file_url="https://www.manythings.org/anki/ces-eng.zip")
    # process data directory
    if args.process:
        logging.info("Processing data ...")
        process_data(data_dir)