import pandas as pd
from unidecode import unidecode
data = pd.read_csv("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces.txt", sep="\t", header=None)
data[0] = data[0].apply(lambda x: unidecode(x))
data[1] = data[1].apply(lambda x: unidecode(x))
data[2] = data[2].apply(lambda x: unidecode(x))
data.to_csv("E:\\GitHub\\CzechTutor\\data\\ces-eng\\ces_bkp.txt", sep="\t", header=None, encoding="utf-8")