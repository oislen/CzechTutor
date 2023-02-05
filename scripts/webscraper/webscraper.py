import os
# load in custom functions
from scripts.webscraper.download_src import download_src
from scripts.webscraper.multiprocess import multiprocess

def main(srcs, output_dir):
    """
    """
    # if output directory does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir, exist_ok = True)
    # run function to download src
    multiprocess(download_src, [(src, output_dir) for src in srcs])
    return 0