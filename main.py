import cons
import pandas as pd
import requests
from bs4 import BeautifulSoup
import re
from PIL import Image
from io import BytesIO
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import random

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
    plt.axis('off')
    plt.show()
    return 0

def select_form():
    """"""
    if random.uniform(0, 1) > 0.5:
        form = 'Nominative Singular'
    else:
        form = 'Nominative Plural'
    return form

def formulate_question(english, form, options):
    """"""
    if form == 'Nominative Singular':
        question = f'What is Czech for {english.title()}:'
    else:
        question = f'What is Czech for {english.title()}(s):'
    # generate question options
    valid_options = options[form].to_list()
    question_options = ''.join([f'- {x} \n' for x in valid_options])
    print(question)
    print(question_options)
    return valid_options

def process_user_answer(valid_options):
    """"""
    valid_answer = 0
    while valid_answer == 0:
        # ask for user input
        answer = input('Answer: ')
        # remove leading and trailling white space from answer
        answer = answer.strip()
        # verify user input
        user_input, return_code = check_user_input(answer, valid_options)
        if return_code == 1:
            print('Invalid answer selected')
        else:
            valid_answer = 1
    return answer

def grade_answer(answer, target_sample, form, options, english, total):
    """"""
    # retrieve the english translation of the answer
    answer_english = options.loc[options[form] == answer, 'English'].iloc[0]
    # determine if the correct answer was choosen
    result = 1 if answer_english == english else 0
    # update running total of game
    total = total + result
    # generate feedback response
    response = 'Correct' if result == 1 else f"Incorrect, the correct answer was {target_sample['Nominative Singular'].iloc[0]}"
    print(response)
    return total

def main(questions = cons.questions):
    """
    """
    while cons.run:
        # select vocabulary topic
        topic = select_topic()
        # set total to zero
        total = 0
        # read in Czech vocabulary data
        data = pd.read_excel(cons.vocab_fpath, sheet_name=topic)
        # loop over each question
        for i in range(questions):
            # pull data for the questions
            target_sample, outside_samples, options = pull_data(data)
            # extract english translation of sample
            english = target_sample['English'].iloc[0]
            # determine form of question
            form = select_form()
            # plot image of english word
            plot_english(target_sample['Image'].iloc[0])
            # pose question
            valid_options = formulate_question(english, form, options)
            # process user's answer
            answer = process_user_answer(valid_options)
            # grade answer
            total = grade_answer(answer, target_sample, form, options, english, total)  
        # print results
        print(f'{total} out of {questions} questions correct')
    return 0


