import os
import requests
from multiprocessing import Pool

def download_src(src, output_dir):
    """
    This function downloads a scraped image sources
    """
    img_data = requests.get(src).content
    image_fstem = src.split('/')[-1]
    image_fpath = os.path.join(output_dir, image_fstem)
    print(image_fpath)
    with open(image_fpath, 'wb') as handler:
        handler.write(img_data)
    return 0

def multiprocess(func, args, ncpu = os.cpu_count()):
    """
    This utility function applyies another function in parallel given a specified number of cpus
    """
    pool = Pool(ncpu)
    results = pool.starmap(func, args)
    pool.close()
    return results

def main(srcs, output_dir):
    """
    """
    # if output directory does not exist
    if not os.path.exists(output_dir):
        os.makedirs(output_dir, exist_ok = True)
    # run function to download src
    multiprocess(download_src, [(src, output_dir) for src in srcs])
    return 0