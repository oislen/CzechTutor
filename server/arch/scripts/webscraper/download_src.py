import requests
import os

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