def formulate_question(quest_dict):
    """
    """
    # extract data from question dictionary
    form = quest_dict['form']
    options = quest_dict['options']
    # extract english translation of sample
    english = options.loc[options['target'] == 1, 'English'].iloc[0]
    # if form is singular
    if form == 'Nominative Singular':
        question = f'What is Czech for {english.title()}:'
    # otherwise
    else:
        question = f'What is Czech for {english.title()}(s):'
    # generate question options
    valid_options = options[form].to_list()
    question_options = ''.join([f'{i + 1}. {x} \n' for i, x in enumerate(valid_options)])
    # assign to question dictionary
    quest_dict['valid_options'] = valid_options
    print(question)
    print(question_options)
    return quest_dict
