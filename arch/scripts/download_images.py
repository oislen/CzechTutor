import cons
import pandas as pd
from scripts.webscraper.webscraper import main

# if running as main programme
if __name__ == '__main__':
    # load image urls from data
    data_objs = [pd.read_excel(cons.vocab_fpath, sheet_name = topic, usecols=['Image']).dropna() for topic in cons.topics]
    data = pd.concat(objs = data_objs, axis = 0, ignore_index = True)
    # scrape url images
    main(srcs = list(data['Image'].unique()), output_dir = cons.images_dir)