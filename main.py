import cons
import pandas as pd
import requests
from bs4 import BeautifulSoup
import re
from PIL import Image
from io import BytesIO
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

def check_user_input(user_input, valid_input = None):
    """"""
    if user_input == 'q()':
        exit()
    if valid_input != None:
        if user_input not in valid_input:
            return_code = 1
        else:
            return_code = 0
    else:
        return_code = 0

    return user_input, return_code

def select_topic():
    # set parameter for topic selected
    topic_selected = 0
    # while no topic has been selected
    while topic_selected == 0:
        # ask for user input
        topic = input(f'Select a Topic: {cons.topics} \n')
        # verify user input
        user_input, return_code = check_user_input(topic, cons.topics)
        # if a valid topic has been selected
        if return_code == 0:
            # update topic selected parameter
            topic_selected = 1
            # print feedback
            print(f'Topic Selected: {topic}')
    return topic

def pull_data(data):
    """"""
    # random sample a record from the data
    target_sample = data.sample(n = 1)
    # extract three other records from the data
    outside_samples = data.loc[data.index.drop(target_sample.index[0]), :].sample(n = 3)
    # randomise samples
    options = pd.concat(objs = [target_sample, outside_samples], axis = 0).sample(n = 4, replace = False)
    return target_sample, outside_samples, options

def plot_english(image_url):
    """"""
    image_page = requests.get(image_url)
    img = Image.open(BytesIO(image_page.content))
    plt.imshow(img)
    plt.show()
    return 0

def grade_answer(answer, target_sample, options, english, total):
    """"""
    # retrieve the english translation of the answer
    answer_english = options.loc[options['Nominative Singular'] == answer, 'English'].iloc[0]
    # determine if the correct answer was choosen
    result = 1 if answer_english == english else 0
    # update running total of game
    total = total + result
    # generate feedback response
    response = 'Correct' if result == 1 else f"Incorrect, the correct answer was {target_sample['Nominative Singular'].iloc[0]}"
    print(response)
    return total

def main(total = 0, run = True):
    """
    """
    while run:
        # select vocabulary topic
        topic = select_topic()
        # read in Czech vocabulary data
        data = pd.read_excel(cons.vocab_fpath, sheet_name=topic)
        # loop over each question
        for i in range(cons.questions):
            # pull data for the questions
            target_sample, outside_samples, options = pull_data(data)
            # extract english translation of sample
            english = target_sample['English'].iloc[0]
            # plot image of english word
            plot_english(target_sample['Image'].iloc[0])
            print(f'What is Czech for {english.title()}:')
            print(''.join(options['Nominative Singular'].apply(lambda x: f'- {x} \n').to_list()))
            # ask for user input
            answer = input('Answer: ')
            # grade answer
            grade_answer(answer, target_sample, options, english, total)   
        # print results
        print(f'{total} out of {cons.questions} questions correct')
        # set run to false to end programme
        run = False
    return 0


