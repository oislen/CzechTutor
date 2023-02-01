import cons
import pandas as pd
import requests
from bs4 import BeautifulSoup
import re
from PIL import Image
from io import BytesIO
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

def plot_english(image_url):
    """"""
    image_page = requests.get(image_url)
    img = Image.open(BytesIO(image_page.content))
    plt.imshow(img)
    plt.show()
    return 0

run = True
questions = 3
total = 0

while run:

    # display and select topic
    #topic = input(f'Select a Topic: {cons.topics} \n')
    topic = input(f"Select a Topic: ['Animals'] \n")

    print(f'Topic Selected: {topic}')

    data = pd.read_excel(cons.vocab_fpath, sheet_name=topic)

    for i in range(questions):

        # random sample a record from the data
        target_sample = data.sample(n = 1)

        # extract english translation of sample
        english = target_sample['English'].iloc[0]

        # extract three other records from the data
        outside_samples = data.loc[data.index.drop(target_sample.index[0]), :].sample(n = 3)

        # randomise samples
        options = pd.concat(objs = [target_sample, outside_samples], axis = 0).sample(n = 4, replace = False)

        plot_english(target_sample['Image'].iloc[0])

        print(f'What is Czech for {english.title()}:')
        print(''.join(options['Nominative Singular'].apply(lambda x: f'- {x} \n').to_list()))

        answer = input('Answer: ')

        if answer == 'q()':

            run = False
            break
                
        answer_english = options.loc[options['Nominative Singular'] == answer, 'English'].iloc[0]

        result = 1 if answer_english == english else 0

        total = total + result

        response = 'Correct' if result == 1 else f"Incorrect, the correct answer was {target_sample['Nominative Singular'].iloc[0]}"

        print(response)

    print(f'{total} out of {questions} questions correct')

    run = False


