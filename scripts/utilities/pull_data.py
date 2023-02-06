import pandas as pd

def pull_data(quest_dict):
    """
    """
    # extract data from question dictionary
    data = quest_dict['data']
    n_options = quest_dict['n_options']
    # random sample a record from the data
    options = data.sample(n = n_options).reset_index(drop = True)
    # label the first record as the correct answer
    options['target'] = pd.Series([1] + [0] * (n_options - 1))
    # randomly suffle options
    options = options.sample(n = n_options, replace = False)
    # assign options to question dictionary
    quest_dict['options'] = options
    # extract english translation of sample
    english = options.loc[options['target'] == 1, 'English'].iloc[0]
    quest_dict['english'] = english
    return quest_dict
